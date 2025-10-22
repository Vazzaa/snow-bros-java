package Entidades.PowerUp;

import Visitors.Colisionable;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Entidad;
import Entidades.Enemigos.Enemigo;

public abstract class PowerUp extends Entidad implements Colisionable {

    protected int puntaje;
    protected int tiempoDeVida; 

    public PowerUp(Skin skins, int posX, int posY,int puntaje, int tiempoDeVida) {
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

    public void setTiempoDeVida(int tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
    }

    public abstract void afectar(SnowBro snowBro);

    public abstract void afectar(Enemigo enemigo);

    public abstract void afectar(PowerUp powerUp);

}
