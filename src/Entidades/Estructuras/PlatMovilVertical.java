package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.Movible;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class PlatMovilVertical extends Plataforma implements Movible {

    public PlatMovilVertical(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y);
    }

    public void afectar(SnowBro s) {

    }

    public void afectar (Enemigo e) {

    }

    public void setSkin (Skin s) {

    }

    @Override
    public void moverse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moverse'");
    }

    public boolean esMovible() {
        return true;
    }
    
}
