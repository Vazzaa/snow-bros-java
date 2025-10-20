package Entidades.PowerUp;

public class Azul extends PowerUp {

    protected int puntaje;
    protected float tiempoDeVida;
    protected int duracionSnowBro;

    public Azul(Skin s, float x, float y, Hitbox h) {
        super(s,x,y);
        puntaje = 300;
        tiempoDeVida = 10;
        duracionSnowBro = 10;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void afectar(SnowBro s) {
        
    }

    
    public void afectar(Enemigo e) {
        
    }

    
    public void afectar(PowerUp p) {
        
    }

    
    public void setSkin(Skin s) {
        this.skin = s;
    }

}
