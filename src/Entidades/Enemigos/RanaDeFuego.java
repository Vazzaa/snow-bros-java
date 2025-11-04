package Entidades.Enemigos;

import javax.swing.Timer;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import EstadoMovimiento.EnemigoCaminandoDerecha;
import EstadoMovimiento.EnemigoCaminandoIzquierda;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilFuego;
import Grafica.Observer;
import Juego.Nivel;
import EstadoMovimiento.EstadoMovimientoEnemigo;
import EstadoMovimiento.EnemigoQuieto;

public class RanaDeFuego extends Enemigo implements EstadoEnemigo {
        
    protected FabricaEntidades fabParaFuego;
    private static final int VELOCIDAD = 1;
    private long tiempoUltimoDisparo = 0;
    private static final long INTERVALO_DISPARO = 2000;
    private static final long TIEMPO_QUIETA = 1000;
    private static final long TIEMPO_DISPARO = 500;
    private long tiempoInicioQuieto = 0;
    private EstadoMovimientoEnemigo estadoAntesDeQuieto = null;
    private boolean estabaQuieto = false;
    public int direccion;
    public int movimientoActual;

    public RanaDeFuego(Skin skins,ModoDeJuego juego ,int posX, int posY, FabricaEntidades fabricaFuego){
        super(skins, juego ,posX, posY, 3,300);
        estadoMovimiento = new EnemigoCaminandoDerecha();
        fabParaFuego = fabricaFuego;
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
    //    if (estadoMovimiento == null) {
    //     estadoMovimiento = new EnemigoCaminandoDerecha();
    //     direccion=0;
    //    }

    //    long tiempoActual = System.currentTimeMillis();


    //    if (!estadoMovimiento.permiteMovimiento()) {

    //     if(!estadoMovimiento.permiteMovimiento()) {
    //         if(tiempoInicioQuieto == 0) {
    //             tiempoInicioQuieto = tiempoActual;
    //         }
    //     }

    //     if(tiempoActual - tiempoInicioQuieto >= TIEMPO_QUIETA) {

    //         if(tiempoActual - tiempoUltimoDisparo >= INTERVALO_DISPARO) {
    //             dispararFuego();
    //             tiempoUltimoDisparo = tiempoActual;
    //         }

    //         if(tiempoActual - tiempoInicioQuieto >= TIEMPO_QUIETA + TIEMPO_DISPARO) {
    //             EstadoMovimientoEnemigo estadoAnterior = estadoMovimiento.getEstadoAnterior();
    //             if(estadoAnterior != null) {
    //                 estadoMovimiento = estadoAnterior.getEstadoOpuesto();
    //                 actualizarDireccion();
    //             } else {
    //                 estadoMovimiento = new EnemigoCaminandoDerecha();
    //                 direccion=0;
    //             }
    //             estadoAntesDeQuieto = null;
    //             estabaQuieto = false;
    //             tiempoInicioQuieto = 0;
    //         } 
    //     }
    //     estadoMovimiento.moverse(this, VELOCIDAD);
    //    } else {
    //     int posXAntes = getPosX();
    //     cambiarEstado();
    //     estadoMovimiento.moverse(this, VELOCIDAD);
    //     int posXDespues = getPosX();

    //     if(posXAntes == posXDespues) {
    //         estadoAntesDeQuieto = estadoMovimiento;
    //         estadoMovimiento = new EnemigoQuieto(estadoAntesDeQuieto);
    //         tiempoInicioQuieto = tiempoActual;
    //         estabaQuieto = true;
    //         }
    //     }
    }

    private void actualizarDireccion(){
        if(direccion==0){
            direccion=180;
        }else{
            direccion=0;
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
            miJuego.registrarObserver(disparo);
            miJuego.getNivel().agregarProyectiles(disparo);
            Timer timer = new Timer(disparo.getAlcance(), e -> {
            miJuego.getControladoraGrafica().sacarEntidad(disparo); 
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    public void recibirDisparo() {
        // TODO Auto-generated method stub        
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
                    break;
                case 2:
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                    direccion=0;
                    break;
                case 3:
                    estadoAntesDeQuieto = estadoMovimiento;
                    estadoMovimiento = new EnemigoQuieto();
                    tiempoInicioQuieto = tiempoActual;
                    if (tiempoActual - tiempoUltimoDisparo >= INTERVALO_DISPARO) {
                        tiempoUltimoDisparo = tiempoActual;
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
        
    }

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recibirDisparo'");
    }

    public boolean esVolador() {
        return false;
    }
}