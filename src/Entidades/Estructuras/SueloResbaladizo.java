package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class SueloResbaladizo extends Obstaculo {

    public SueloResbaladizo(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y);
    }

    public void afectar(SnowBro s) {

    }

    public void afectar (Enemigo e) {

    }

    public void setSkin (Skin s) {

    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    @Override
    public boolean esSuelo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esSuelo'");
    }
}

