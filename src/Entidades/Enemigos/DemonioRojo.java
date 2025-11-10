package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilNieve;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.*;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;
import Visitors.Colisionable;


public class DemonioRojo extends Enemigo {
    
    protected static final int ESTADO_INICIAL = 0;
    protected static final int ESTADO_POCO_NIEVE = 1; 
    protected static final int ESTADO_MEDIO_NIEVE = 2;
    protected static final int ESTADO_NIEVE_COMPLETO = 3;

    protected EstadoEnemigo estadoNormal;
    protected EstadoEnemigo estadoPocoCongelado;
    protected EstadoEnemigo estadoMedioCongelado;
    protected EstadoEnemigo estadoCompletamenteCongelado;
    
    protected int estadoNieve;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;
    protected int velocidadVerticalDeslizamiento = 0;
    protected int gravedadDeslizamiento = 1;
    
    // Atributos para el temporizador de derretimiento
    private long tiempoFinCongelado = 0;
    private static final int DURACION_CONGELADO_MS = 3000;

    public DemonioRojo(Skin skins, ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 3,300);
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
    protected void crearPowerUp() {
        this.getJuego().getControladoraGrafica().sacarEntidad(this);
        PowerUp powerUp = this.getJuego().getNivelActual().getMiFabrica().getFruta(miHitbox.getPosX(), miHitbox.getPosY());
        skinAleatoriaFruta(powerUp);
        this.getJuego().registrarObserver(powerUp);
        this.getJuego().getNivelActual().agregarPowerUps(powerUp);
        int crearPocionONo = (int) (Math.random()*3+1);
        System.out.println("numero tocado"+crearPocionONo);
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

    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        if (estaSiendoEmpujado() && estaCompletamenteCongelado()) {
            deslizarse();
            return;
        }
        if (detenidoGlobalmente) return;
        if (estadoNieve > ESTADO_INICIAL) {
            estadoMovimiento = new EnemigoQuieto();
        } else {
            cambiarEstado();
        }

        verificarDerretimiento();
        estadoMovimiento.moverse(this, VELOCIDAD);
    }

    public void deslizarse() {
        if (getJuego() == null || getJuego().getNivel() == null) {
            return;
        }
        
        ColisionManagerEntidades colisionManager = new ColisionManagerEntidades();
        int nuevaX = getPosX() + velocidadDeslizamiento;
        for (Estructura estructura : getJuego().getNivel().getMisEstructuras()) {
            if (estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(getHitbox().getAncho(), getHitbox().getAlto(), nuevaX, getPosY());
                if (colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    destruirBolaDeNieve(); // Llamamos al nuevo método
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
        if (!colisionManager.estaEnSuelo(this, getJuego().getNivel().getMisEstructuras())) {
            velocidadVerticalDeslizamiento -= gravedadDeslizamiento;
            int nuevaY = getPosY() + velocidadVerticalDeslizamiento;
            boolean colisionaVertical = false;
            for (Estructura estructura : getJuego().getNivel().getMisEstructuras()) {
                Hitbox hitboxFutura = new Hitbox(getHitbox().getAncho(), getHitbox().getAlto(), nuevaX, nuevaY);
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
            velocidadVerticalDeslizamiento = 0;
            Estructura plataformaDebajo = colisionManager.getPlataformaDebajo(this, getJuego().getNivel().getMisEstructuras());
            if (plataformaDebajo != null) {
                int techoPlataforma = plataformaDebajo.getHitbox().getPosY() + plataformaDebajo.getHitbox().getAlto();
                setPosY(techoPlataforma);
            }
        }
        setPosX(nuevaX);
        notificarObserver();
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void morir() {
        estaVivo=false;
        GestorSonidos.getInstancia().reproducirEfecto("enemy_death");
        crearPowerUp();
        getJuego().getNivel().getSnowBro().sumarPuntaje(this.puntaje); // El puntaje se suma en el método morir del enemigo arrollado
        return;
    }

    private void skinAleatoriaFruta(PowerUp p) {
        int skin = (int) (Math.random()*12+1);
        p.getSkin().setEstadoActual(skin);
    }

    @Override
    public void cambiarEstado() {
        movimientoActual = (int) (Math.random()*3+1);
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            switch(movimientoActual){
                case 1:
                     estadoMovimiento = new EnemigoCaminandoIzquierda();
                    break;
                case 2:
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                    break;
                case 3:
                    estadoMovimiento = new EnemigoQuieto();
                    break;
                }
            tiempoUltimoCambio = tiempoActual;
        }
    }

    public void cambiarEstadoInmediato() {
        if (estadoMovimiento != null) {
            estadoMovimiento = estadoMovimiento.getEstadoOpuesto();
            tiempoUltimoCambio = System.currentTimeMillis();
        }
    }


    public void colisionarEstructura(Estructura e) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (!colisiona) return;
        afectar(e);
        return;
    }

     public void afectar(Estructura e) {
        e.afectar(this);
    }

    public void afectar(ProyectilNieve n) {
        //creo q no hace nada
    }

    @Override
    public void colisionarPowerUp(PowerUp p) {
        //no hace nada
    }

    @Override
    public void colisionarEnemigo(Enemigo e) {
        //no hace nada
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

    public boolean esVolador() {
        return false;
    }
    @Override
    public void recibirDisparo() {
        if (estadoNieve >= ESTADO_NIEVE_COMPLETO) {
            return;
        } else {
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
        crearPowerUp(); // Creamos el power-up al destruirse
        getJuego().getNivel().getSnowBro().sumarPuntaje(this.puntaje); // Sumamos puntos por destruir la bola
        // Opcional: podrías añadir un sonido o efecto visual de la bola rompiéndose aquí.
    }
}