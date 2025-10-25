package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.Movible;
import Fabricas.Skin;

public class PlatMovil extends Plataforma implements Movible {

    public PlatMovil(Skin s, int x, int y) {
        super(s, x, y);
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
}

