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

    public Kamakichi(Skin skins, ModoDeJuego juego, int posX, int posY){
        super(skins, juego, posX, posY, 5,300);
        this.colisionManager = new ColisionManagerEntidades();
        vida = 10;
        this.estadoMovimiento = new EnemigoQuieto();
        this.tiempoUltimoCambio = System.currentTimeMillis();
        this.miHitbox.setAlto(73);
        this.miHitbox.setAncho(144);
    }

    @Override
    public void atacar(Enemigo e) {
        // TODO Auto-generated method stub
        
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
        cambiarEstado();
        estadoMovimiento.moverse(this, VELOCIDAD);
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public Bomba crearBomba(){
        return null;
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
            // Si puede cambiar pero obtenerSiguienteEstado devuelve null,
            // significa que Kamakichi debe decidir su propio siguiente estado
        }
        
        // Lógica propia de Kamakichi cuando el estado permite decidir
        movimientoActual = (int) (Math.random() * 3 + 1);
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
        }
        tiempoUltimoCambio = tiempoActual;
    }
}

    protected void dispararBombas(){

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
}
