package Entidades.PowerUp;

public class VidaExtra extends PowerUp {

    protected int puntaje;
    protected float tiempoDeVida;
    protected int duracionSnowBro;

    public VidaExtra(Skin s, float x, float y, Hitbox h) {
        super(s,x,y);
        puntaje = 0;
        tiempoDeVida = 10;
        duracionSnowBro = 0;
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
