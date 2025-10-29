package Entidades.Enemigos;

import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoMovimientoEnemigo;
import EstadoMovimiento.EnemigoCaminandoDerecha;
import EstadoMovimiento.EnemigoCaminandoIzquierda;
import EstadoMovimiento.EstadoEnemigo;
import EstadoMovimiento.Movible;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import Visitors.Colisionador;

public class DemonioRojo extends Enemigo implements EstadoEnemigo{
    protected int movimientoActual;
    private static final int VELOCIDAD = 5;
    public DemonioRojo(Skin skins, ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 3,300);
        movimientoActual = 1;
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
    public void crearPowerUp() {
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

    public PowerUp morir() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cambiarEstado() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            if (movimientoActual == 1) {
                estadoMovimiento = new EnemigoCaminandoIzquierda();
                movimientoActual = 2;
            } else {
                estadoMovimiento = new EnemigoCaminandoDerecha();
                movimientoActual = 1;
            }
            tiempoUltimoCambio = tiempoActual;
        }
    }

}
