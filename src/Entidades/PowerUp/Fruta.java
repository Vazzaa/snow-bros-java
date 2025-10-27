package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class Fruta extends PowerUp{

    protected int puntaje;
    protected float tiempoDeVida;

    public Fruta(Skin s, ModoDeJuego juego, float x, float y, Hitbox h) {
        super(s, juego, (int) x,(int)y, 300, 10);
        puntaje = 500;
        tiempoDeVida = 10;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void afectar(SnowBro s) {
        s.sumarPuntaje(puntaje);
        notificarObserver();
        //eliminar();
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
