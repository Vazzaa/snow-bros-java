package Entidades.Enemigos;

import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import EstadoMovimiento.EnemigoCaminandoDerecha;
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

    public RanaDeFuego(Skin skins,ModoDeJuego juego ,int posX, int posY){
        super(skins, juego ,posX, posY, 3,300);
        estadoMovimiento = new EnemigoCaminandoDerecha();
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
       /*if (estadoMovimiento == null) {
        estadoMovimiento = new EnemigoCaminandoDerecha();
       }

       long tiempoActual = System.currentTimeMillis();


       if (!estadoMovimiento.permiteMovimiento()) {

        if(!estadoMovimiento.permiteMovimiento()) {
            if(tiempoInicioQuieto == 0) {
                tiempoInicioQuieto = tiempoActual;
            }
        }

        if(tiempoActual - tiempoInicioQuieto >= TIEMPO_QUIETA) {

            if(tiempoActual - tiempoUltimoDisparo >= INTERVALO_DISPARO) {
                dispararFuego();
                tiempoUltimoDisparo = tiempoActual;
            }

            if(tiempoActual - tiempoInicioQuieto >= TIEMPO_QUIETA + TIEMPO_DISPARO) {
                EstadoMovimientoEnemigo estadoAnterior = estadoMovimiento.getEstadoAnterior();
                if(estadoAnterior != null) {
                    estadoMovimiento = estadoAnterior.getEstadoOpuesto();
                } else {
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                }
                estadoAntesDeQuieto = null;
                estabaQuieto = false;
                tiempoInicioQuieto = 0;
            } 
        }
        estadoMovimiento.moverse(this, VELOCIDAD);
       } else {
        int posXAntes = getPosX();
        cambiarEstado();
        estadoMovimiento.moverse(this, VELOCIDAD);
        int posXDespues = getPosX();

        if(posXAntes == posXDespues) {
            estadoAntesDeQuieto = estadoMovimiento;
            estadoMovimiento = new EnemigoQuieto(estadoAntesDeQuieto);
            tiempoInicioQuieto = tiempoActual;
            estabaQuieto = true;
        }
    }
    */}

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
   /* if (miJuego != null && miJuego.getNivel() != null) {
        Nivel nivel = miJuego.getNivel();
        FabricaEntidades fabrica = nivel.getMiFabrica();
        if (fabrica != null) {
            ProyectilFuego proyectil = fabrica.getProyectilFuego(getPosX(), getPosY());
            if (proyectil != null) {
                nivel.agregarProyectiles(proyectil);
                if (miJuego.getControladoraGrafica() != null) {
                    Observer obs = miJuego.getControladoraGrafica().registrarEntidad(proyectil);
                    if (obs != null) {
                        proyectil.registrarObserver(obs);
                    }
                }

            }
        }
    }
*/}
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

    
    }

}