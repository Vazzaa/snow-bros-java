package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.Azul;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilFuego;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.*;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;

public class Moghera extends Enemigo{
    
    // Fases del jefe
    protected static final int FASE_NORMAL = 0;
    protected static final int FASE_ENFURECIDO = 1;
    protected static final int FASE_CONGELADO = 2;

    protected int faseActual;
    protected int golpesParaEnfurecer = 5; 
    protected int golpesParaCongelar = 10; 
    protected int contadorGolpes = 0;

    private static int VELOCIDAD_NORMAL = 2;
    private static int VELOCIDAD_ENFURECIDO = 4;

    private long tiempoUltimoDisparo = 0;
    private long intervaloDisparoNormal = 3000; 
    private long intervaloDisparoEnfurecido = 1500; 
    
    private long tiempoUltimaDecision = 0;
    private long intervaloDecision = 1000;

    private boolean saltando = false;

    protected int direccionDisparo = 180; 
    protected FabricaEntidades creacionFuego;



    public Moghera(Skin skins, ModoDeJuego juego, int posX, int posY, FabricaEntidades fabrica) {
        super(skins, juego, posX, posY, 10, 2000); 
        this.creacionFuego = fabrica;
        this.faseActual = FASE_NORMAL;
        this.estadoMovimiento = new EnemigoCaminandoIzquierda();
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

    public void dispararFuego() {
        if (miJuego != null && miJuego.getNivel() != null && creacionFuego != null) {
            ProyectilFuego disparo = creacionFuego.getProyectilFuego(miHitbox.getPosX(), miHitbox.getPosY(), direccionDisparo);
            miJuego.registrarObserver(disparo);
            miJuego.getNivel().agregarProyectiles(disparo);
        }
    }

    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        if (detenidoGlobalmente || faseActual == FASE_CONGELADO) {
            estadoMovimiento = new EnemigoQuieto();
            estadoMovimiento.moverse(this, 0);
            return;
        }

        long tiempoActual = System.currentTimeMillis();
        long intervaloDisparo = (faseActual == FASE_NORMAL) ? intervaloDisparoNormal : intervaloDisparoEnfurecido;
        if (tiempoActual - tiempoUltimoDisparo > intervaloDisparo) {
            dispararFuego();
            tiempoUltimoDisparo = tiempoActual;
        }

        if (tiempoActual - tiempoUltimaDecision > intervaloDecision) {
            tomarDecisionDeMovimiento();
            tiempoUltimaDecision = tiempoActual;
        }

        int velocidad = (faseActual == FASE_NORMAL) ? VELOCIDAD_NORMAL : VELOCIDAD_ENFURECIDO;
        estadoMovimiento.moverse(this, velocidad);
    }

    private void tomarDecisionDeMovimiento() {
        SnowBro snowBro = miJuego.getNivel().getSnowBro();
        if (snowBro == null) return;

        int distanciaX = snowBro.getPosX() - this.getPosX();
        int distanciaY = snowBro.getPosY() - this.getPosY();

        if (distanciaY > 30 && Math.abs(distanciaX) < 100) { 
            if (estadoMovimiento instanceof EnemigoCaminandoDerecha || estadoMovimiento instanceof EnemigoCaminandoIzquierda) {
                estadoMovimiento = new EnemigoSaltando();
                return;
            }
        }

        if (distanciaX > 10) { 
            estadoMovimiento = new EnemigoCaminandoDerecha();
            direccionDisparo = 0;
        } else if (distanciaX < -10) {
            estadoMovimiento = new EnemigoCaminandoIzquierda();
            direccionDisparo = 180;
        } else {
            estadoMovimiento = new EnemigoQuieto();
        }
    }

    @Override
    public void recibirDisparo() {
        // TODO Auto-generated method stub
        
        if (faseActual == FASE_CONGELADO) {
            morir();
            return;
        }

        contadorGolpes++;
        misAspectos.setEstadoActual(2); 

        if (contadorGolpes >= golpesParaCongelar) {
            faseActual = FASE_CONGELADO;
            misAspectos.setEstadoActual(4); 
        } else if (contadorGolpes >= golpesParaEnfurecer) {
            faseActual = FASE_ENFURECIDO;
            misAspectos.setEstadoActual(3); 
        }
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void morir(){
        
        estaVivo = false;
        getJuego().getNivel().getSnowBro().sumarPuntaje(puntaje);
        getJuego().getControladoraGrafica().sacarEntidad(this);
    }

    @Override
    public void cambiarEstado() {
        cambiarEstadoInmediato();
    }
    

    public void cambiarEstadoInmediato() {
        if (estadoMovimiento != null) {
            estadoMovimiento = estadoMovimiento.getEstadoOpuesto();
            direccionDisparo = (direccionDisparo == 0) ? 180 : 0;
            tiempoUltimoCambio = System.currentTimeMillis();
        }
    }


    @Override
    public void colisionarPowerUp(PowerUp p) {

    }

    @Override
    public void colisionarEnemigo(Enemigo e) {

    }

    @Override
    public void colisionarEstructura(Estructura e) {

    }

    @Override
    public void colisionarObstaculo(Obstaculo o) {
    
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
}
