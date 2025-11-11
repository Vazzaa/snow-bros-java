package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import EstadoMovimiento.EnemigoVoladorCaminandoIzquierda;
import EstadoMovimiento.EnemigoQuieto;
import EstadoMovimiento.EnemigoVoladorCaminandoDerecha;
import EstadoMovimiento.EnemigoVoladorQuieto;
import EstadoMovimiento.EnemigoVoladorDiagonal;

public class Fantasma extends Enemigo{
    
    protected static final int ESTADO_INICIAL = 0;
    protected static final int ESTADO_ESTUNEADO = 1;

    protected int estadoInicial;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;

    private long tiempoFinCongelado = 0;
    private static final int DURACION_ESTUNEADO_MS = 3000;

    public Fantasma(Skin skins,ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 0,300);
        estadoMovimiento = new EnemigoVoladorCaminandoIzquierda();
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
        if (detenidoGlobalmente) return;
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
        if (estadoMovimiento != null) {
            estadoMovimiento = estadoMovimiento.getEstadoOpuesto();
            tiempoUltimoCambio = System.currentTimeMillis();
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

    @Override
    public void moverHorizontalmente(int i) {
            //no hace nada a este porque vuela
    }

    @Override
    public void morir() {
        //no hace nada
    }

    @Override
    public boolean estaCompletamenteCongelado() {
        return false;
    }

    @Override
    public void moverVerticalmente(int i) {
        //no hace nada a este porque vuela
    }
}
