package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class VidaExtra extends PowerUp {

    public VidaExtra(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y, 300, 10);
        tiempoDeVida = 10000;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void afectar(SnowBro snowBro) {
        snowBro.sumarPuntaje(puntaje);
        snowBro.setVida(snowBro.getVida() + 1);
        notificarObserver();
        eliminar();
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
