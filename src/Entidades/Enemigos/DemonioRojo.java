package Entidades.Enemigos;

import java.util.Timer;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.ProyectilNieve;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoMovimientoEnemigo;
import EstadoMovimiento.*;
import EstadoMovimiento.Movible;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import Visitors.Colisionador;

public class DemonioRojo extends Enemigo implements EstadoEnemigo{
    
    protected static final int ESTADO_NORMAL = 1;
    protected static final int ESTADO_POCO_NIEVE = 2;
    protected static final int ESTADO_MEDIO_NIEVE = 3;
    protected static final int ESTADO_NIEVE_COMPLETO = 4;
    
    protected int estadoNieve;
    protected Timer timerDerretimiento;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;
    public DemonioRojo(Skin skins, ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 3,300);
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
    }
}