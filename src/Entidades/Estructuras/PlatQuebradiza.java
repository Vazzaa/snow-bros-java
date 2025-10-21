package Entidades.Estructuras;
import Entidades.Hitbox;
import Entidades.Skin;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;

public class PlatQuebradiza extends Plataforma{

    protected int puntaje;
    protected int Vida;

     public PlatQuebradiza(Skin s, int x, int y) {
        super(s, x, y);
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


}
