package Entidades.PowerUp;


import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;


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
        eliminar();
    }
    
    @Override
    public void afectar(Enemigo enemigo) {
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }
    
    public void afectar(PowerUp p) {
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

}
