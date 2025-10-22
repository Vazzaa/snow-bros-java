package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;

public class Fruta extends PowerUp{

    protected int puntaje;
    protected float tiempoDeVida;

    public Fruta(Skin s, float x, float y, Hitbox h) {
        super(s,(int) x,(int)y, 300, 10);
        puntaje = 500;
        tiempoDeVida = 10;
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
        misAspectos = s;
    }

    @Override
    public Skin getSkins() {
        return misAspectos;
    }

}
