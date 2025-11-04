package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class Fruta extends PowerUp{

    public Fruta(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y, 300, 10);
        puntaje = 500;
        tiempoDeVida = 10;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void afectar(SnowBro s) {
        s.sumarPuntaje(puntaje);
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
