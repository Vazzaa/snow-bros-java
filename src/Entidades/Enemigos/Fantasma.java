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
import EstadoMovimiento.EnemigoVoladorCaminandoDerecha;
import EstadoMovimiento.EnemigoVoladorQuieto;
import EstadoMovimiento.EnemigoVoladorDiagonal;

public class Fantasma extends Enemigo{
    
    protected int estadoInicial;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;

    public Fantasma(Skin skins,ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 0,300);
        estadoMovimiento = new EnemigoVoladorCaminandoIzquierda();
        tiempoUltimoCambio = System.currentTimeMillis();
    }

    @Override
    public void atacar(Enemigo e) {
        // No aplica para este caso
    }

    @Override
    public void atacar(SnowBro s) {
        // No aplica para este caso
    }

    @Override
    public void chocar(Colisionable c) {
        // No aplica para este caso
    }

    @Override
    public EstadoEnemigo getEstado() {
        // No aplica para este caso
        return null;
    }

    @Override
    public void moverse() {
        if (!detenidoGlobalmente) {
            cambiarEstado();
            estadoMovimiento.moverse(this, VELOCIDAD);
        }
    }

    @Override
    public void recibirDisparo() {
        //no es alcanzado
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // No aplica para este caso
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
        // No aplica para este caso
    }

    @Override
    public void colisionarEnemigo(Enemigo e) {
        // No aplica para este caso
    }

    @Override
    public void colisionarEstructura(Estructura e) {
        // No aplica para este caso
    }

    @Override
    public void colisionarObstaculo(Obstaculo o) {
        // No aplica para este caso
    }

    @Override
    public void colisionarProyectil(Proyectil p) {
        //no hace nada
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

    @Override
    public boolean cuentaParaCompletarNivel() {
        return false;
    }
}
