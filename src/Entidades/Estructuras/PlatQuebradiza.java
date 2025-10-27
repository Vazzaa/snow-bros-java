package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class PlatQuebradiza extends Plataforma{

    protected int puntaje;
    protected int Vida;

     public PlatQuebradiza(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y);
        puntaje = 300;
        Vida = 1;
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

    public void destruir() {

    }


}
