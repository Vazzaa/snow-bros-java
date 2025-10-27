package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class ParedDestructible extends Pared {

    protected int Vida;
    protected int puntaje;

     public ParedDestructible(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y);
        Vida = 1;
        puntaje = 150;
    }

    public void Destruir() {

    }

    public void afectar(SnowBro s) {

    }

    public void afectar (Enemigo e) {

    }

    public void setSkin(Skin s) {

    }

    public int getPuntaje() {
        return puntaje;
    }

}
