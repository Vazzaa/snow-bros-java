package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilFuego;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EnemigoCaminandoIzquierda;
import EstadoMovimiento.EnemigoCaminandoDerecha;
import EstadoMovimiento.EnemigoQuieto;
import EstadoMovimiento.EnemigoSaltando;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.Skin;
import Juego.ColisionManagerEntidades;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;
import Visitors.Colisionable;
import Fabricas.FabricaEntidades;

public class Moghera extends Enemigo {

    private static final int VELOCIDAD = 2;
    protected ColisionManagerEntidades colisionManager;
    protected int movimientoActual;
    protected int vida;
    protected FabricaEntidades fabParaFuego;
    protected int direccion = 0;

    public Moghera(Skin skins, ModoDeJuego juego, int posX, int posY, FabricaEntidades fabParaFuego) {
        super(skins, juego, posX, posY, 10, 500);
        this.colisionManager = new ColisionManagerEntidades();
        this.vida = 50;
        this.fabParaFuego = fabParaFuego;
        this.estadoMovimiento = new EnemigoQuieto();
        this.miHitbox.setAlto(90);
        this.miHitbox.setAncho(64);
    }

    @Override
    public void atacar(Enemigo e) {
        // Moghera no ataca a otros enemigos
    }

    @Override
    public void morir() {
        estaVivo = false;
        GestorSonidos.getInstancia().reproducirEfecto("enemy_death");
        getJuego().getNivel().getSnowBro().sumarPuntaje(puntaje);
        getJuego().getControladoraGrafica().sacarEntidad(this);
    }

    @Override
    public void atacar(SnowBro s) {
        if(this.colisionaAABB(miHitbox, s.getHitbox())){
            s.disminuirVida();
        }
    }

    @Override
    public void chocar(Colisionable c) {
        // Lógica de choque general
    }

    @Override
    public EstadoEnemigo getEstado() {
        return null; // Moghera no usa el sistema de estados de congelamiento
    }

    @Override
    public void moverse() {
        if (!detenidoGlobalmente) {
            cambiarEstado();
            if (estadoMovimiento != null) {
                estadoMovimiento.moverse(this, VELOCIDAD);
            }
        }
    }

    @Override
    public void recibirDisparo() {
        vida--;
        if (vida <= 0) {
            morir();
        }
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // Moghera no se congela
    }

    @Override
    public void cambiarEstado() {
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO) {
            movimientoActual = (int) (Math.random() * 5 + 1);
            switch (movimientoActual) {
                case 1:
                    estadoMovimiento = new EnemigoSaltando();
                    misAspectos.setEstadoActual(5);
                    break;
                case 2:
                    estadoMovimiento = new EnemigoQuieto();
                    misAspectos.setEstadoActual(4);
                    break;
                case 3:
                    dispararFuego();
                    misAspectos.setEstadoActual(8);
                    break;
                case 4:
                    direccion = 180;
                    estadoMovimiento = new EnemigoCaminandoIzquierda();
                    misAspectos.setEstadoActual(6);
                    break;
                case 5:
                    direccion = 0;
                    estadoMovimiento = new EnemigoCaminandoDerecha();
                    misAspectos.setEstadoActual(7);
                    break;
            }
            tiempoUltimoCambio = tiempoActual;
        }
    }

    protected void dispararFuego() {
        if (miJuego != null && miJuego.getNivel() != null) {
            ProyectilFuego disparo = fabParaFuego.getProyectilFuego(miHitbox.getPosX(), miHitbox.getPosY(), direccion);
            if (direccion == 0) { 
                disparo.getSkin().setEstadoActual(2);
            } else { 
                disparo.getSkin().setEstadoActual(1);
            }
            miJuego.registrarObserver(disparo);
            miJuego.getNivel().agregarProyectiles(disparo);
            GestorSonidos.getInstancia().reproducirEfecto("enemy_fire");
        }
    }

    @Override
    public void cambiarEstadoInmediato() {
        if (estadoMovimiento != null) {
            estadoMovimiento = estadoMovimiento.getEstadoOpuesto();
            tiempoUltimoCambio = System.currentTimeMillis();
        }
    }

    @Override
    public boolean estaCompletamenteCongelado() {
        return false; // Moghera no se congela
    }

    @Override
    public void moverHorizontalmente(int i) {
        setPosX(getPosX() + i);
        notificarObserver();
    }

    @Override
    public void moverVerticalmente(int i) {
        setPosY(getPosY() + i);
        notificarObserver();
    }

    @Override
    public void colisionarProyectil(Proyectil p) {
        if (this.colisionaAABB(miHitbox, p.getHitbox())) {
            p.afectar(this);
        }
    }

    // No aplican para este caso
    @Override
    public void colisionarPowerUp(PowerUp p) {}

    @Override
    public void colisionarEnemigo(Enemigo e) {}

    @Override
    public void colisionarObstaculo(Obstaculo o) {}

    @Override
    public void colisionarEstructura(Estructura e) {}

    @Override
    public int getRenderAncho() {
        return this.miHitbox.getAncho();
    }

    @Override
    public int getRenderAlto() {
        return this.miHitbox.getAlto();
    }
}