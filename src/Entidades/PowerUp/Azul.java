package Entidades.PowerUp;

import java.util.List;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Grafica.ObserverGrafico;
import Juego.Hitbox;
import Juego.ModoDeJuego;

import javax.swing.Timer;

public class Azul extends PowerUp {

    protected int duracionSnowBro;

    public Azul(Skin s,ModoDeJuego juego, int x, int y) {
        super(s, juego , x, y, 300, 10);
        duracionSnowBro = 10000;
    }

    public int getPuntaje() {
        return puntaje;
    }
      
    public void setSkin(Skin s) {
        misAspectos = s;
    }
    
    @Override
    public void afectar(SnowBro snowBro) {
        snowBro.sumarPuntaje(puntaje);
        snowBro.activarBoostAzul(duracionSnowBro);
        notificarObserver();
        //eliminar();
    }
    
    @Override
    public void afectar(Enemigo enemigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }
    
    public void afectar(PowerUp p) {
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

}
