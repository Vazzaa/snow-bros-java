package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class VidaExtra extends PowerUp {

    public VidaExtra(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y, 300, 10);
        puntaje = 0;
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
    public Skin getSkin() {
        return misAspectos;
    }

}
