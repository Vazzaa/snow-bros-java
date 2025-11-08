package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.*;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;

public class Calabaza extends Enemigo{

    protected static final int ESTADO_INICIAL = 0;
    protected static final int ESTADO_ESTUNEADO = 1;

    protected int estadoInicial;
    protected FabricaEntidades mFabricaEntidades;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;

    private long tiempoFinCongelado = 0;
    private static final int DURACION_ESTUNEADO_MS = 3000;


    public Calabaza(Skin skins , ModoDeJuego juego , int posX, int posY){
        super(skins, juego, posX, posY, 0,300);
        estadoInicial = ESTADO_INICIAL;
        int estadoInicial = (int) (Math.random()*4+1);
        switch(estadoInicial){
            case 1:
                estadoMovimiento = new EnemigoVoladorCaminandoIzquierda();
                break;
            case 2:
                estadoMovimiento = new EnemigoVoladorCaminandoDerecha();
                break;
            case 3:
                estadoMovimiento = new EnemigoVoladorQuieto();
                break;
            case 4:
                estadoMovimiento = new EnemigoVoladorDiagonal();
                break;
        }
        tiempoUltimoCambio = System.currentTimeMillis();
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


    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        if ( estadoInicial > ESTADO_INICIAL) {
            estadoMovimiento = new EnemigoQuieto();
        } else {
            cambiarEstado();
        }

        verificarEstuneo();
        estadoMovimiento.moverse(this, VELOCIDAD);
    }

    @Override
    public void recibirDisparo() {
        if(estadoInicial != ESTADO_ESTUNEADO){
            estadoInicial++;
            actualizarEstadoEstuneado();
        }
    }

    private void actualizarEstadoEstuneado(){
        if (estadoInicial > ESTADO_INICIAL) {
            tiempoFinCongelado = System.currentTimeMillis() + DURACION_ESTUNEADO_MS;
        }
        else {
            tiempoFinCongelado = 0; 
        }
    }

        private void verificarEstuneo() {
        if (estadoInicial <= ESTADO_INICIAL || tiempoFinCongelado == 0 || System.currentTimeMillis() < tiempoFinCongelado) {
            return;
        }
        estadoInicial--;
        actualizarEstadoEstuneado();
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void crearFantasma(){
        
    }

    @Override
    public void cambiarEstado() {
        movimientoActual = (int) (Math.random()*4+1);
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            switch(movimientoActual){
                case 1:
                     estadoMovimiento = new EnemigoVoladorCaminandoIzquierda();
                    break;
                case 2:
                    estadoMovimiento = new EnemigoVoladorCaminandoDerecha();
                    break;
                case 3:
                    estadoMovimiento = new EnemigoVoladorQuieto();
                    break;
                case 4:
                    estadoMovimiento = new EnemigoVoladorDiagonal();
                    break;
                }
            tiempoUltimoCambio = tiempoActual;
        }
    }

    public void cambiarEstadoInmediato() {
        int nuevoEstado = (int) (Math.random()*4+1);
        switch(nuevoEstado){
            case 1:
                estadoMovimiento = new EnemigoVoladorCaminandoIzquierda();
                break;
            case 2:
                estadoMovimiento = new EnemigoVoladorCaminandoDerecha();
                break;
            case 3:
                estadoMovimiento = new EnemigoVoladorQuieto();
                break;
            case 4:
                estadoMovimiento = new EnemigoVoladorDiagonal();
                break;
        }
        tiempoUltimoCambio = System.currentTimeMillis();
    }


    @Override
    public void colisionarPowerUp(PowerUp p) {
        if (this.colisionaAABB(miHitbox, p.getHitbox())) {
            p.afectar(this);
        }
    }

    @Override
    public void colisionarEnemigo(Enemigo e) {
        
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


    public boolean esVolador() {
        return true;
    }

    @Override
    public boolean esInmortal() {
        return true;
    }

}
