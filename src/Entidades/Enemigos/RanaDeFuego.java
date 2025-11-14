package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import EstadoMovimiento.EstadoMedioCongelado;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import EstadoMovimiento.EnemigoCaminandoDerecha;
import EstadoMovimiento.EnemigoCaminandoIzquierda;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilFuego;
import Sonidos.GestorSonidos;
import EstadoMovimiento.EstadoMovimientoEnemigo;
import EstadoMovimiento.EstadoNormal;
import EstadoMovimiento.EstadoPocoCongelado;
import EstadoMovimiento.EnemigoQuieto;
import EstadoMovimiento.EstadoCompletamenteCongelado;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class RanaDeFuego extends Enemigo {
        
    protected static final int ESTADO_INICIAL = 0;
    protected static final int ESTADO_POCO_NIEVE = 1; 
    protected static final int ESTADO_MEDIO_NIEVE = 2;
    protected static final int ESTADO_NIEVE_COMPLETO = 3;

    protected EstadoEnemigo estadoNormal;
    protected EstadoEnemigo estadoPocoCongelado;
    protected EstadoEnemigo estadoMedioCongelado;
    protected EstadoEnemigo estadoCompletamenteCongelado;

    protected FabricaEntidades fabParaFuego;
    private static final int VELOCIDAD = 1;
    private long tiempoUltimoDisparo = 0;
    private static final long INTERVALO_DISPARO = 2000;
    protected static final long TIEMPO_QUIETA = 1000;
    protected static final long TIEMPO_DISPARO = 500;
    protected long tiempoInicioQuieto = 0;
    protected EstadoMovimientoEnemigo estadoAntesDeQuieto = null;
    protected boolean estabaQuieto = false;
    protected int direccion;
    protected int movimientoActual;
    protected int estadoNieve;
    protected int velocidadVerticalDeslizamiento = 0;
    protected int gravedadDeslizamiento = 1;

    private long tiempoFinCongelado = 0;
    private static final int DURACION_CONGELADO_MS = 3000;

    public RanaDeFuego(Skin skins,ModoDeJuego juego ,int posX, int posY, FabricaEntidades fabricaFuego){
        super(skins, juego ,posX, posY, 3,300);
        estadoMovimiento = new EnemigoCaminandoDerecha();
        fabParaFuego = fabricaFuego;
        estadoNieve = ESTADO_INICIAL;
        estadoNormal = new EstadoNormal();
        estadoPocoCongelado = new EstadoPocoCongelado();
        estadoMedioCongelado = new EstadoMedioCongelado();
        estadoCompletamenteCongelado = new EstadoCompletamenteCongelado();
    }

    @Override
    public void atacar(Enemigo e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atacar(SnowBro s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chocar(Colisionable c) {
        // TODO Auto-generated method stub
        
    }

    public void morir() {
        estaVivo=false;
        GestorSonidos.getInstancia().reproducirEfecto("enemy_death");
        crearPowerUp();
    }
    
    protected void crearPowerUp() {
        this.getJuego().getControladoraGrafica().sacarEntidad(this);
        PowerUp powerUp = this.getJuego().getNivelActual().getMiFabrica().getFruta(miHitbox.getPosX(), miHitbox.getPosY());
        skinAleatoriaFruta(powerUp);
        this.getJuego().registrarObserver(powerUp);
        this.getJuego().getNivelActual().agregarPowerUps(powerUp);
        int crearPocionONo = (int) (Math.random()*3+1);
        if(crearPocionONo == 2){
            PowerUp pocion = null;
            int color = (int) (Math.random()*3+1);
            switch (color) {
                case 1:
                    pocion = this.getJuego().getNivelActual().getMiFabrica().getPowerUpAzul(miHitbox.getPosX()+5, miHitbox.getPosY());
                break;
                case 2:
                    pocion = this.getJuego().getNivelActual().getMiFabrica().getPowerUpRojo(miHitbox.getPosX()+5, miHitbox.getPosY());
                break;
                case 3:
                    pocion = this.getJuego().getNivelActual().getMiFabrica().getPowerUpVerde(miHitbox.getPosX()+5, miHitbox.getPosY());
                break;
            }
            this.getJuego().registrarObserver(pocion);
            this.getJuego().getNivelActual().agregarPowerUps(pocion);
        }
    }
    private void skinAleatoriaFruta(PowerUp p) {
        int skin = (int) (Math.random()*12+1);
        p.getSkin().setEstadoActual(skin);
    }

    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        if (estaSiendoEmpujado() && estaCompletamenteCongelado()) {
            deslizarse();

        } else if (!detenidoGlobalmente) {
            if (estadoNieve > ESTADO_INICIAL) {
                estadoMovimiento = new EnemigoQuieto();
            } else {
                cambiarEstado();
            }
            
            verificarDerretimiento();
            estadoMovimiento.moverse(this, VELOCIDAD);
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
                        destruirBolaDeNieve();
                        return;
                    }
                }
            }

            for (Enemigo otroEnemigo : getJuego().getNivel().getMisEnemigos()) {
                if (otroEnemigo != this && otroEnemigo.estaVivo() && !otroEnemigo.estaCompletamenteCongelado()) {
                    if (colisionaAABB(getHitbox(), otroEnemigo.getHitbox())) {
                        otroEnemigo.morir(); // El otro enemigo muere
                        return; // Salimos para no seguir procesando el deslizamiento
                    }
                }
            }

            setPosX(nuevaX);
            
            // Verificar si hay una plataforma sólida directamente debajo en la nueva posición
            boolean hayPlataformaDebajo = false;
            Hitbox hitboxActual = new Hitbox(getHitbox().getAncho(), getHitbox().getAlto(), getPosX(), getPosY());
            Hitbox hitboxDeteccion = new Hitbox(
                hitboxActual.getAncho(),
                hitboxActual.getAlto() + 5, // TOLERANCIA_SUELO
                hitboxActual.getPosX(),
                hitboxActual.getPosY() - 5
            );
            
            for (Estructura estructura : getJuego().getNivel().getMisEstructuras()) {
                if (colisionManager.colisionaAABB(hitboxDeteccion, estructura.getHitbox())) {
                    if (estructura.esSueloSolido()) {
                        int pieEntidad = hitboxActual.getPosY();
                        int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                        if (Math.abs(pieEntidad - techoEstructura) <= 5) {
                            hayPlataformaDebajo = true;
                            // Ajustar posición Y para mantenerla pegada a la plataforma
                            setPosY(techoEstructura);
                            break;
                        }
                    }
                }
            }
            
            if (!hayPlataformaDebajo) {
                // No hay plataforma sólida debajo, aplicar gravedad
                velocidadVerticalDeslizamiento -= gravedadDeslizamiento;
                int nuevaY = getPosY() + velocidadVerticalDeslizamiento;
                boolean colisionaVertical = false;
                for (Estructura estructura : getJuego().getNivel().getMisEstructuras()) {
                    if (!estructura.esSueloSolido()) {
                        continue;
                    }
                    Hitbox hitboxFutura = new Hitbox(getHitbox().getAncho(), getHitbox().getAlto(), getPosX(), nuevaY);
                    if (colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                        int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                        int pieEnemigo = nuevaY;
                        if (pieEnemigo >= techoEstructura - 5 && pieEnemigo <= techoEstructura + 10) {
                            setPosY(techoEstructura);
                            velocidadVerticalDeslizamiento = 0;
                            colisionaVertical = true;
                            break;
                        }
                    }
                }
                
                if (!colisionaVertical) {
                    setPosY(nuevaY);
                }

            } else {
                // Hay plataforma debajo, mantener velocidad vertical en 0
                velocidadVerticalDeslizamiento = 0;
            }
            
            notificarObserver();
        }
        
    }
        

    public void cambiarEstadoInmediato() {
        if(!estadoMovimiento.permiteMovimiento()) {
            EstadoMovimientoEnemigo estadoAnterior = estadoMovimiento.getEstadoAnterior();
            if(estadoAnterior != null) {
                estadoMovimiento = estadoAnterior.getEstadoOpuesto();
            } else {
                estadoMovimiento = new EnemigoCaminandoDerecha();
            }
            estadoAntesDeQuieto = null;
            estabaQuieto = false;
            tiempoInicioQuieto = 0;
        } else if (estadoMovimiento != null) {
            estadoMovimiento = estadoMovimiento.getEstadoOpuesto();
            tiempoUltimoCambio = System.currentTimeMillis();
        }

    }

    public void dispararFuego() {        
        if (miJuego != null && miJuego.getNivel() != null) {
            ProyectilFuego disparo= fabParaFuego.getProyectilFuego(miHitbox.getPosX(), miHitbox.getPosY(), direccion);
            if (direccion == 0) {
                disparo.getSkin().setEstadoActual(2);
            } else { 
                disparo.getSkin().setEstadoActual(1);
            }
            miJuego.registrarObserver(disparo);
            miJuego.getNivel().agregarProyectiles(disparo);
            GestorSonidos.getInstancia().reproducirEfecto("enemy_fire");
        }
    }

    public void recibirDisparo() {
        if (estadoNieve < ESTADO_NIEVE_COMPLETO) {
            estadoNieve += getJuego().getNivel().getSnowBro().getDañoProyectil();
            actualizarEstadoNieve();
        }
    }

    public void setEstado(EstadoEnemigo estado) {
        // TODO Auto-generated method stub
        
    }

   public Skin getSkin() {
        return misAspectos;
    }

    public void cambiarEstado() {
        movimientoActual = (int) (Math.random()*3+1);
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            switch(movimientoActual){
                case 1:
                     estadoMovimiento = new EnemigoCaminandoIzquierda();
                     direccion=180;
                     misAspectos.setEstadoActual(2);
                    break;
                case 2:
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                    direccion=0;
                    misAspectos.setEstadoActual(1);
                    break;
                case 3:
                    estadoAntesDeQuieto = estadoMovimiento;
                    estadoMovimiento = new EnemigoQuieto();
                    tiempoInicioQuieto = tiempoActual;
                    misAspectos.setEstadoActual(4);
                    if (tiempoActual - tiempoUltimoDisparo >= INTERVALO_DISPARO) {
                        tiempoUltimoDisparo = tiempoActual;
                        if(direccion == 0)
                            misAspectos.setEstadoActual(7);
                        else
                            misAspectos.setEstadoActual(3);
                        dispararFuego();
                    }
                    break;
                }
            tiempoUltimoCambio = tiempoActual;
        }
    }

    @Override
    public void colisionarPowerUp(PowerUp p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarPowerUp'");
    }

    @Override
    public void colisionarEnemigo(Enemigo e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarEnemigo'");
    }

    @Override
    public void colisionarEstructura(Estructura e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarEstructura'");
    }

    @Override
    public void colisionarObstaculo(Obstaculo o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarObstaculo'");
    }

    @Override
    public void colisionarProyectil(Proyectil p) {
        if (this.colisionaAABB(miHitbox, p.getHitbox())) {
            p.afectar(this);
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
    public void moverHorizontalmente(int i) {
        setPosX(getPosX() + i);
        notificarObserver();
    }

    public boolean estaCompletamenteCongelado() {
        return estadoNieve >= ESTADO_NIEVE_COMPLETO;
    }

    @Override
    public void moverVerticalmente(int i) {
        setPosY(getPosY()+i);
        notificarObserver();
    }

    public void destruirBolaDeNieve() {
        estaVivo = false;
        crearPowerUp();
        getJuego().getNivel().getSnowBro().sumarPuntaje(this.puntaje);
        GestorSonidos.getInstancia().reproducirEfecto("enemy_death");
    }

}