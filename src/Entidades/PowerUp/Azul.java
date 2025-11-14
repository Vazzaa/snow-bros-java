package Entidades.PowerUp;


import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Sonidos.GestorSonidos;


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
        GestorSonidos.getInstancia().reproducirEfecto("powerup");
        notificarObserver();
        eliminar();
    }
    
    @Override
    public void afectar(Enemigo enemigo) {
        // No aplica para este caso
    }
    
    public void afectar(PowerUp p) {
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

}
