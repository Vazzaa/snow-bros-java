package Entidades.PowerUp;

import Visitors.Colisionable;
import Visitors.Colisionador;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Entidad;
import Entidades.Enemigos.Enemigo;
import Juego.ModoDeJuego;

public abstract class PowerUp extends Entidad implements Colisionable {

    protected int puntaje;
    protected int tiempoDeVida;
    private boolean vidaActiva = true;
    private long tiempoCreacion;

    public PowerUp(Skin skins, ModoDeJuego juego, int posX, int posY,int puntaje, int tiempoDeVida) {
        super(skins, juego, posX, posY);
        this.puntaje = puntaje;
        this.tiempoDeVida = tiempoDeVida;
        this.tiempoCreacion = System.currentTimeMillis();
    }

    public int getPuntaje() {
        return puntaje;
    }

    public float getTiempoDeVida() {
        return tiempoDeVida;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setTiempoDeVida(int tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
    }

    public abstract void afectar(SnowBro snowBro);

    public abstract void afectar(Enemigo enemigo);

    public abstract void afectar(PowerUp powerUp);
    
    @Override
    public void aceptarColision(Colisionador c) {
        c.colisionarPowerUp(this);
    }

    public boolean esColisionable() {
        return true;
    }

    public void eliminar() {
        vidaActiva = false;
        miJuego.getControladoraGrafica().sacarEntidad(this);
    }

    public boolean estaActivo() {
        return vidaActiva;
    }

    public void verificarTiempoDeVida() {
        if (vidaActiva && (System.currentTimeMillis() - tiempoCreacion > tiempoDeVida * 1000)) {
            eliminar();
        }
    }

}
