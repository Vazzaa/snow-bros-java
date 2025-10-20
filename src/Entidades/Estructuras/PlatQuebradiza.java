package Entidades.Estructuras;

public class PlatQuebradiza extends Plataforma{

    protected int puntaje;
    protected int Vida;

    public PlatQuebradiza(Skin s, Hitbox h) {
        super(s,h);
        puntaje = 300;
        Vida = 1;
    }

    public void afectar(SnowBro s) {

    }

    public void afectar (Enemigo e) {

    }

    public void setSkin(Skin s) {
        this.skin = s;
    }

    public int getPuntaje() {
        return puntaje;
    }


}
