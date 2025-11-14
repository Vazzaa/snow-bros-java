package Entidades.PowerUp;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;

public class Rojo extends PowerUp {
    
    protected int duracionSnowBro;

    public Rojo(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y, 300, 10);
        duracionSnowBro = 10000; // 10 segundos
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public void afectar(PowerUp p) {
        
    }

    
    public void setSkin(Skin s) {
        misAspectos = s;
    }

    @Override
    public void afectar(SnowBro snowBro) {
        snowBro.sumarPuntaje(puntaje);
        snowBro.activarBoostRojo(duracionSnowBro);
        GestorSonidos.getInstancia().reproducirEfecto("powerup");
        notificarObserver();
        eliminar();
    }

    @Override
    public void afectar(Enemigo enemigo) {
        // No aplica para este caso
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

}
