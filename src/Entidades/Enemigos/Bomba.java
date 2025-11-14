package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EnemigoCaminandoDerecha;
import EstadoMovimiento.EnemigoCaminandoIzquierda;
import EstadoMovimiento.EnemigoQuieto;
import EstadoMovimiento.EstadoCompletamenteCongelado;
import EstadoMovimiento.EstadoMedioCongelado;
import EstadoMovimiento.EstadoNormal;
import EstadoMovimiento.EstadoEnemigo;
import EstadoMovimiento.EstadoPocoCongelado;
import Fabricas.Skin;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;
import Visitors.Colisionable;

public class Bomba extends Enemigo{

    private enum EstadoBomba {
        NORMAL,
        ENCENDIDA,
        EXPLOTANDO,
        FINALIZADA
    }

    protected static final int ESTADO_INICIAL = 0;
    protected static final int ESTADO_POCO_NIEVE = 1;
    protected static final int ESTADO_MEDIO_NIEVE = 2;
    protected static final int ESTADO_NIEVE_COMPLETO = 3;

    protected EstadoEnemigo estadoNormal;
    protected EstadoEnemigo estadoPocoCongelado;
    protected EstadoEnemigo estadoMedioCongelado;
    protected EstadoEnemigo estadoCompletamenteCongelado;

    private EstadoBomba estadoActual;
    private long tiempoCambioEstado;

    private static final long DURACION_NORMAL = 6500;
    private static final long DURACION_ENCENDIDA = 3000;
    private static final long DURACION_EXPLOSION = 750;
    private static final long DURACION_CONGELADO_MS = 3000; // 8 segundos para derretirse

    private int estadoNieve;
    private long tiempoFinCongelado = 0;

    protected int velocidadVerticalDeslizamiento = 0;
    protected int gravedadDeslizamiento = 1;
    private static final int VELOCIDAD = 1;
    protected int movimientoActual;

    public Bomba(Skin skins, ModoDeJuego juego, int posX, int posY) {
        super(skins, juego, posX, posY, 1, 100);
        this.estadoActual = EstadoBomba.NORMAL;
        this.tiempoCambioEstado = System.currentTimeMillis();
        this.estadoNieve = ESTADO_INICIAL;
        this.estadoMovimiento = new EnemigoQuieto();
        estadoNormal = new EstadoNormal();
        estadoPocoCongelado = new EstadoPocoCongelado();
        estadoMedioCongelado = new EstadoMedioCongelado();
        estadoCompletamenteCongelado = new EstadoCompletamenteCongelado();
    }

    @Override
    public void moverse() {
        if (estadoActual != EstadoBomba.FINALIZADA) {

            if (estaSiendoEmpujado() && estaCompletamenteCongelado()) {
                deslizarse();
            }

            else if (detenidoGlobalmente) {
                notificarObserver();
            }

            else if (estadoNieve > ESTADO_INICIAL) {
                estadoMovimiento = new EnemigoQuieto();
                verificarDerretimiento();
                notificarObserver();
            }

            else {
                long tiempoActual = System.currentTimeMillis();
                switch (estadoActual) {
                    case NORMAL:
                        if (tiempoActual - tiempoCambioEstado >= DURACION_NORMAL) {
                            estadoActual = EstadoBomba.ENCENDIDA;
                            tiempoCambioEstado = tiempoActual;
                        }
                        cambiarEstado();
                        break;

                    case ENCENDIDA:
                        if (tiempoActual - tiempoCambioEstado >= DURACION_ENCENDIDA) {
                            explotarYMorir();
                            return;
                        }
                        cambiarEstado();
                        break;

                    case EXPLOTANDO:
                        estadoMovimiento = new EnemigoQuieto();
                        if (tiempoActual - tiempoCambioEstado >= DURACION_EXPLOSION) {
                            morir();
                        }
                        break;

                    case FINALIZADA:
                        break;
                }

                if (estadoMovimiento != null) {
                    estadoMovimiento.moverse(this, VELOCIDAD);
                }

                notificarObserver();
            }
        }
    }

    public void deslizarse() {
        if (getJuego() != null && getJuego().getNivel() != null) {

            ColisionManagerEntidades colisionManager = new ColisionManagerEntidades();
            int nuevaX = getPosX() + velocidadDeslizamiento;

            for (Estructura estructura : getJuego().getNivel().getMisEstructuras()) {
                if (estructura.bloquearMovimientoHorizontal() || estructura.destruyeBolaDeNieve()) {
                    Hitbox hitboxFutura = new Hitbox(getHitbox().getAncho(), getHitbox().getAlto(), nuevaX, getPosY());
                    if (colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                        morir();
                        return;
                    }
                }
            }

            for (Enemigo otroEnemigo : getJuego().getNivel().getMisEnemigos()) {
                if (otroEnemigo != this && otroEnemigo.estaVivo() && !otroEnemigo.estaCompletamenteCongelado()) {
                    if (colisionaAABB(getHitbox(), otroEnemigo.getHitbox())) {
                        otroEnemigo.morir();
                        return;
                    }
                }
            }

            if (!colisionManager.estaEnSuelo(this, getJuego().getNivel().getMisEstructuras())) {
                velocidadVerticalDeslizamiento -= gravedadDeslizamiento;
                setPosY(getPosY() + velocidadVerticalDeslizamiento);
            } else {
                velocidadVerticalDeslizamiento = 0;
            }

            setPosX(nuevaX);
            notificarObserver();
        }
    }

    private void explotarYMorir() {
        if (estadoActual != EstadoBomba.EXPLOTANDO || estadoActual != EstadoBomba.FINALIZADA) {
            estadoActual = EstadoBomba.EXPLOTANDO;
            tiempoCambioEstado = System.currentTimeMillis();
            misAspectos.setEstadoActual(5);
            GestorSonidos.getInstancia().reproducirEfecto("explosion");
            notificarObserver();
        }
    }

    @Override
    public void morir() {
        estadoActual = EstadoBomba.FINALIZADA;
        explotarYMorir();
        this.estaVivo = false;
        getJuego().getControladoraGrafica().sacarEntidad(this);
    }

    @Override
    public void recibirDisparo() {
        if (estadoNieve < ESTADO_NIEVE_COMPLETO && estadoActual != EstadoBomba.EXPLOTANDO) {
            if (estadoActual == EstadoBomba.ENCENDIDA) {
                estadoActual = EstadoBomba.NORMAL;
                tiempoCambioEstado = System.currentTimeMillis();
            }
            estadoNieve += getJuego().getNivel().getSnowBro().getDañoProyectil();
            actualizarEstadoNieve();
        }
    }

    private void verificarDerretimiento() {
        if (estadoNieve <= ESTADO_INICIAL || tiempoFinCongelado == 0 || System.currentTimeMillis() < tiempoFinCongelado) {
            return;
        }
        estadoNieve -= getJuego().getNivel().getSnowBro().getDañoProyectil();
        actualizarEstadoNieve();
    }

    private void actualizarEstadoNieve() {
        if(getJuego().getNivel().getSnowBro().getDañoProyectil()==2 && estadoNieve==2){
            estadoNieve = 1;
        }
        if (estadoNieve > ESTADO_NIEVE_COMPLETO) {
            estadoNieve = ESTADO_NIEVE_COMPLETO;
        }

        switch (estadoNieve) {
            case ESTADO_INICIAL:
                estadoNormal.recibirDisparo(this);
                break;
            case ESTADO_POCO_NIEVE:
                estadoPocoCongelado.recibirDisparo(this);
                break;
            case ESTADO_MEDIO_NIEVE:
                estadoMedioCongelado.recibirDisparo(this);
                break;
            case ESTADO_NIEVE_COMPLETO:
                estadoCompletamenteCongelado.recibirDisparo(this);
                break;
        }
        if (estadoNieve > ESTADO_INICIAL) {
            tiempoFinCongelado = System.currentTimeMillis() + DURACION_CONGELADO_MS;
        }
        else {
            tiempoFinCongelado = 0; 
        }
    }

    @Override
    public boolean estaCompletamenteCongelado() {
        return estadoNieve >= ESTADO_NIEVE_COMPLETO;
    }

    @Override
    public void cambiarEstado() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            movimientoActual = (int) (Math.random() * 3 + 1);
            switch (movimientoActual) {
                case 1:
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                    misAspectos.setEstadoActual(estadoActual == EstadoBomba.NORMAL ? 2 : 4);
                    break;
                case 2:
                    estadoMovimiento = new EnemigoCaminandoIzquierda();
                    misAspectos.setEstadoActual(estadoActual == EstadoBomba.NORMAL ? 1 : 3);
                    break;
                case 3:
                    estadoMovimiento = new EnemigoQuieto();
                    break;
            }
            tiempoUltimoCambio = tiempoActual;
        }
    }

    public void afectar(Estructura e) {
        e.afectar(this);
    }

    @Override
    public void atacar(SnowBro s) { }

    @Override
    public void atacar(Enemigo e) { }

    @Override
    public void chocar(Colisionable c) { }

    @Override
    public void setEstado(EstadoEnemigo estado) { }

    @Override
    public EstadoEnemigo getEstado() {
        return null;
    }

    @Override
    public void cambiarEstadoInmediato() { }

    @Override
    public void moverHorizontalmente(int i) {
        setPosX(getPosX() + i);
        notificarObserver();
    }

    @Override
    public void moverVerticalmente(int i) { 
        setPosY(getPosY()+i);
        notificarObserver();
    }

    @Override
    public void colisionarPowerUp(PowerUp p) { }

    @Override
    public void colisionarEnemigo(Enemigo e) { }

    @Override
    public void colisionarEstructura(Estructura e) { 
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (colisiona) {
            afectar(e);
        }
    }

    @Override
    public void colisionarObstaculo(Obstaculo o) { }
    
    @Override
    public void colisionarProyectil(Proyectil p) {
        if (this.colisionaAABB(miHitbox, p.getHitbox())) {
            p.afectar(this);
        }
    }
}
