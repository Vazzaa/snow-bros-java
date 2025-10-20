package Entidades.PowerUp;

import Entidades.Skin;
import Entidades.Entidad;
import Visitors.Colisionable;
import Entidades.SnowBro.SnowBro;
import Entidades.Enemigos.Enemigo;

public abstract class PowerUp extends Entidad implements Colisionable {

    protected int puntaje;
    protected float tiempoDeVida; 

    public PowerUp(Skin skins, int posX, int posY,int puntaje, float tiempoDeVida) {
        super(skins, posX, posY);
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

    public abstract void afectar(SnowBro snowBro);

    public abstract void afectar(Enemigo enemigo);

    public abstract void afectar(PowerUp powerUp);

}
