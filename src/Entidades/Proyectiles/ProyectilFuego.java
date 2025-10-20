package Entidades.Proyectiles;

public class ProyectilFuego extends Proyectil{

    protected float Alcance;
    protected int Daño;
    protected int Velocidad;
    protected Skin Aspecto;
    protected Hitbox hb;

    public ProyectilFuego (Skin s, Hitbox h) {
        Alcance = 0;
        Daño = 0;
        Velocidad = 0;
        Aspecto = s;
        hb = h;
    }

    public void afectar(SnowBro s) {

    }

    public void afectar(Enemigo e) {

    }

    public void afectar(Estructura es) {
        
    }

    public void setSkin(Skin s) {
        
    }

}
