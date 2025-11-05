package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import EstadoMovimiento.*;

public class TrollAmarillo extends Enemigo{
    
    protected static final int ESTADO_INICIAL = 0; 
    protected static final int ESTADO_POCO_NIEVE = 1;
    protected static final int ESTADO_MEDIO_NIEVE = 2;
    protected static final int ESTADO_NIEVE_COMPLETO = 3;

    protected EstadoEnemigo estadoNormal;
    protected EstadoEnemigo estadoPocoCongelado;
    protected EstadoEnemigo estadoMedioCongelado;
    protected EstadoEnemigo estadoCompletamenteCongelado;

    private int estadoNieve;
    private static final int VELOCIDAD = 3;
    private int movimientoActual;
    private long tiempoFinCongelado = 0;
    private static final int DURACION_CONGELADO_MS = 3000;

    public TrollAmarillo(Skin skins, ModoDeJuego juego,int posX, int posY){
        super(skins, juego,posX, posY, 3,300);
        estadoNieve = ESTADO_INICIAL;
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

    public void morir() {
        estaVivo=false;
        getJuego().getNivel().getSnowBro().sumarPuntaje(puntaje);
        crearPowerUp();
        int dir = (Math.random() < 0.5) ? 0 : 180;
        BolaDeNieve bola = this.getJuego().getNivelActual().getMiFabrica().getBolaDeNieve(miHitbox.getPosX(), miHitbox.getPosY(), dir);
        this.getJuego().registrarObserver(bola);
        this.getJuego().getNivelActual().agregarProyectiles(bola);
        return;
    }
    
    protected void crearPowerUp() {
        this.getJuego().getControladoraGrafica().sacarEntidad(this);
        PowerUp powerUp = this.getJuego().getNivelActual().getMiFabrica().getFruta(miHitbox.getPosX(), miHitbox.getPosY());
        skinAleatoriaFruta(powerUp);
        this.getJuego().registrarObserver(powerUp);
        this.getJuego().getNivelActual().agregarPowerUps(powerUp);
        int crearPocionONo = (int) Math.random()*3;
        if(crearPocionONo == 2){
            PowerUp pocion = null;
            int color = (int) Math.random()*2;
            switch (color) {
                case 0:
                    pocion = this.getJuego().getNivelActual().getMiFabrica().getPowerUpAzul(miHitbox.getPosX()+5, miHitbox.getPosY());
                break;
                case 1:
                    pocion = this.getJuego().getNivelActual().getMiFabrica().getPowerUpRojo(miHitbox.getPosX()+5, miHitbox.getPosY());
                break;
                case 2:
                    pocion = this.getJuego().getNivelActual().getMiFabrica().getPowerUpVerde(miHitbox.getPosX()+5, miHitbox.getPosY());
                break;
            }
            this.getJuego().registrarObserver(pocion);
            this.getJuego().getNivelActual().agregarPowerUps(pocion);
        }
    }


    private void skinAleatoriaFruta(PowerUp p) {
        int skin = (int) (Math.random()*12+1);
        p.getSkin().setEstadoActual(skin);
    }

    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        if (detenidoGlobalmente) return;
        if (estadoNieve > ESTADO_INICIAL) {
            estadoMovimiento = new EnemigoQuieto();
        } else {
            cambiarEstado();
        }

        verificarDerretimiento();
        estadoMovimiento.moverse(this, VELOCIDAD);
    }
    

    @Override
    public void recibirDisparo() {
        if (estadoNieve >= ESTADO_NIEVE_COMPLETO) {
            morir();
        } else {
            estadoNieve += getJuego().getNivel().getSnowBro().getDañoProyectil();
            actualizarEstadoNieve();
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

    public boolean esVolador() {
        return false;
    }

    private void verificarDerretimiento() {
        if (estadoNieve <= ESTADO_INICIAL || tiempoFinCongelado == 0 || System.currentTimeMillis() < tiempoFinCongelado) {
            return;
        }
        estadoNieve -= getJuego().getNivel().getSnowBro().getDañoProyectil();
        actualizarEstadoNieve();
    }

    private void actualizarEstadoNieve() {
        if (estadoNieve > ESTADO_NIEVE_COMPLETO) {
            estadoNieve = ESTADO_NIEVE_COMPLETO;
        }

        switch (estadoNieve) {
            case ESTADO_INICIAL:
                estadoNormal.recibirDisparo(this);
                break;
            case ESTADO_POCO_NIEVE:
                estadoPocoCongelado.recibirDisparo(this);
                break;
            case ESTADO_MEDIO_NIEVE:
                estadoMedioCongelado.recibirDisparo(this);
                break;
            case ESTADO_NIEVE_COMPLETO:
                estadoCompletamenteCongelado.recibirDisparo(this);
                break;
        }
        if (estadoNieve > ESTADO_INICIAL) {
            tiempoFinCongelado = System.currentTimeMillis() + DURACION_CONGELADO_MS;
        }
        else {
            tiempoFinCongelado = 0; 
        }
    }

}
