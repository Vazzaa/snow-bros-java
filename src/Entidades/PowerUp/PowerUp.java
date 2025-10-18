package Entidades.PowerUp;
import Entidades.Entidad;
import Visitors.Colisionable;

abstract class PowerUp extends Entidad implements Colisionable {
    protected int puntaje;
    protected float tiempoDeVida; // en segundos

    public PowerUp(int puntaje, float tiempoDeVida) {
        this.puntaje = puntaje;
        this.tiempoDeVida = tiempoDeVida;
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

    public void setTiempoDeVida(float tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
    }

    public abstract void afectar(Jugador jugador);

    public abstract void afectar(Enemigo enemigo);

    public abstract void afectar(PowerUp powerUp);

}
