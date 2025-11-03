package Entidades.Enemigos;

import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.*;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;

public class Calabaza extends Enemigo implements EstadoEnemigo{

    protected FabricaEntidades mFabricaEntidades;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;

    public Calabaza(Skin skins , ModoDeJuego juego , int posX, int posY){
        super(skins, juego, posX, posY, 0,300);
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
        cambiarEstado();
        estadoMovimiento.moverse(this, VELOCIDAD);
    }

    @Override
    public void recibirDisparo() {
        // TODO Auto-generated method stub
        
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
                     estadoMovimiento = new EnemigoCaminandoIzquierda();
                    break;
                case 2:
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                    break;
                case 3:
                    estadoMovimiento = new EnemigoQuieto();
                    break;
                case 4:
                    estadoMovimiento = new EnemigoDiagonal();
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

}
