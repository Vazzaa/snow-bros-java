package Entidades.Enemigos;


import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.*;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ColisionManagerEntidades;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;
import Visitors.Colisionable;

public class Kamakichi extends Enemigo {
    
    protected FabricaEntidades fabParaBomba;
    private static final int VELOCIDAD = 2;
    protected ColisionManagerEntidades colisionManager;
    protected int movimientoActual;
    protected int vida;

    private static final long INTERVALO_CAMBIO_KAMAKICHI = 2000;

    public Kamakichi(Skin skins, ModoDeJuego juego, int posX, int posY, FabricaEntidades fabParaBomba) {
        super(skins, juego, posX, posY, 5,300);
        this.colisionManager = new ColisionManagerEntidades();
        this.fabParaBomba = fabParaBomba;
        vida = 50;
        this.estadoMovimiento = new EnemigoQuieto();
        this.tiempoUltimoCambio = System.currentTimeMillis();
        miHitbox.setAlto(75);
        miHitbox.setAncho(144);
    }

    @Override
    public void atacar(Enemigo e) {
        // No aplica para este caso
    }

    @Override
    public void morir(){
        estaVivo = false;
        GestorSonidos.getInstancia().reproducirEfecto("enemy_death");
        getJuego().getNivel().getSnowBro().sumarPuntaje(puntaje);
        getJuego().getControladoraGrafica().sacarEntidad(this);
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
        vida--;
        if (vida <= 0) {
            morir();
        }
        
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
        long tiempoActual = System.currentTimeMillis();
        if (tiempoActual - tiempoUltimoCambio >= INTERVALO_CAMBIO_KAMAKICHI) {
            if (estadoMovimiento != null && estadoMovimiento.puedeCambiarEstado(this)) {
                EstadoMovimientoEnemigo siguienteEstado = estadoMovimiento.obtenerSiguienteEstado(this);
                if (siguienteEstado != null) {
                    estadoMovimiento = siguienteEstado;
                    tiempoUltimoCambio = tiempoActual;
                    return;
                }
            }
            
            movimientoActual = (int) (Math.random() * 4 + 1);
            switch(movimientoActual) {
                case 1:
                    estadoMovimiento = new EnemigoKamakichiBajando(this);
                    break;
                case 2:
                    estadoMovimiento = new EnemigoKamakichiVertical(1);
                    break;
                case 3:
                    estadoMovimiento = new EnemigoQuieto();
                    break;
                case 4:
                    dispararBombas();
                    dispararBombas();
                    break;
            }
            tiempoUltimoCambio = tiempoActual;
        }
    }

    protected void dispararBombas(){
        if (miJuego != null && miJuego.getNivel() != null) {
            Bomba enemigoBomba = fabParaBomba.getBomba(miHitbox.getPosX(), miHitbox.getPosY());
            miJuego.registrarObserver(enemigoBomba);
            miJuego.getNivel().agregarEnemigos(enemigoBomba);
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
        if (this.colisionaAABB(miHitbox, p.getHitbox())) {
            p.afectar(this);
        }
    }

    @Override
    public void moverHorizontalmente(int i) {
        setPosX(getPosX() + i);
        notificarObserver();
    }

    @Override
    public boolean estaCompletamenteCongelado() {
        return false;
    }

    @Override
    public void moverVerticalmente(int i) {
        setPosY(getPosY() + i);
        notificarObserver();
    }

    @Override
    public int getRenderAncho() {
        return this.miHitbox.getAncho();
    }

    @Override
    public int getRenderAlto() {
        return this.miHitbox.getAlto();
    }
} 