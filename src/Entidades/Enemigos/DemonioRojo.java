package Entidades.Enemigos;

import java.util.Timer;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilNieve;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoMovimientoEnemigo;
import EstadoMovimiento.*;
import EstadoMovimiento.Movible;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import Visitors.Colisionador;

public class DemonioRojo extends Enemigo {
    
    protected static final int ESTADO_NORMAL = 1;
    protected static final int ESTADO_POCO_NIEVE = 2;
    protected static final int ESTADO_MEDIO_NIEVE = 3;
    protected static final int ESTADO_NIEVE_COMPLETO = 4;

    protected EstadoEnemigo estadoNormal;
    protected EstadoEnemigo estadoPocoCongelado;
    protected EstadoEnemigo estadoMedioCongelado;
    protected EstadoEnemigo estadoCompletamenteCongelado;
    
    protected int estadoNieve;
    protected Timer timerDerretimiento;
    protected int movimientoActual;
    private static final int VELOCIDAD = 1;
    public DemonioRojo(Skin skins, ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 3,300);
        estadoNieve = 0;
        estadoNormal = new EstadoNormal();
        estadoPocoCongelado = new EstadoPocoCongelado();
        estadoMedioCongelado = new EstadoMedioCongelado();
        estadoCompletamenteCongelado = new EstadoCompletamenteCongelado();
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
        estadoMovimiento = new EnemigoQuieto();
        estadoNieve++;
        switch (estadoNieve) {
            case ESTADO_NORMAL:
                this.estadoNormal.recibirDisparo(this);
                break;
            case ESTADO_POCO_NIEVE:
                this.estadoPocoCongelado.recibirDisparo(this);
                break;
            case ESTADO_MEDIO_NIEVE:
                this.estadoMedioCongelado.recibirDisparo(this);
                break;
            case ESTADO_NIEVE_COMPLETO:
                this.estadoCompletamenteCongelado.recibirDisparo(this);
                break;
            default:
                break;
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

    public PowerUp morir() {
        this.getJuego().getNivelActual().getMisEnemigos().remove(this);
        this.getJuego().getControladoraGrafica().sacarEntidad(this);
        PowerUp powerUp = this.getJuego().getNivelActual().getMiFabrica().getPowerUpAzul(miHitbox.getPosX(), miHitbox.getPosY());
        this.getJuego().registrarObserver(powerUp);
        this.getJuego().getNivelActual().agregarPowerUps(powerUp);
        BolaDeNieve bola = this.getJuego().getNivelActual().getMiFabrica().getBolaDeNieve(miHitbox.getPosX(), miHitbox.getPosY(), 1);
        this.getJuego().registrarObserver(bola);
        this.getJuego().getNivelActual().agregarProyectiles(bola);
        return powerUp;
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
    public void colisionarObstaculo(Obstaculo o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarObstaculo'");
    }

    @Override
    public void colisionarProyectil(Proyectil p) {
        if (this.colisionaAABB(miHitbox, p.getHitbox())){
            recibirDisparo();
            p.getJuego().getControladoraGrafica().sacarEntidad(p);
            p.getJuego().getNivel().getMisProyectiles().remove(p);
        }
    }
}