package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;

public class Verde extends PowerUp {

    protected int puntaje;
    protected float tiempoDeVida;
    protected int duracionSnowBro;

    public Verde(Skin s, float x, float y, Hitbox h) {
        super(s,(int) x,(int)y, 300, 10);
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
        misAspectos = s;
    }

    public void detenerEnemigos() {

    }
 
    @Override
    public Skin getSkins() {
        return misAspectos;
    }
}
