package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;

public class Verde extends PowerUp {

    protected int duracion;

    public Verde(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y, 300, 10);
        duracion = 10000;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void afectar(SnowBro snowBro) {
        snowBro.sumarPuntaje(puntaje);
        snowBro.activarBoostVerde(duracion);
        GestorSonidos.getInstancia().reproducirEfecto("powerup");
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
