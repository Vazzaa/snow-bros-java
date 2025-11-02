package Entidades.Enemigos;

import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import EstadoMovimiento.EnemigoCaminandoIzquierda;
import EstadoMovimiento.EnemigoCaminandoDerecha;

public class TrollAmarillo extends Enemigo implements EstadoEnemigo{
    
    private static final int VELOCIDAD = 3;
    private double movimientoActual;

    public TrollAmarillo(Skin skins, ModoDeJuego juego,int posX, int posY){
        super(skins, juego,posX, posY, 3,300);
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

    public PowerUp morir() {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public void cambiarEstado() {
        movimientoActual = (int) (Math.random()*2);
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            if (movimientoActual == 1) {
                estadoMovimiento = new EnemigoCaminandoIzquierda();
            } else {
                estadoMovimiento = new EnemigoCaminandoDerecha();
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
