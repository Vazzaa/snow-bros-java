package Entidades.Estructuras;

public class ParedDestructible extends Pared {

    protected int Vida;
    protected int puntaje;

    public ParedDestructible(Skin s, Hitbox h) {
        super(s,h);
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
