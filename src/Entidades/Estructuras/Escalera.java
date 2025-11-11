package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class Escalera extends Obstaculo{

    public Escalera(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(16);
        miHitbox.setAlto(16);
    }

    public void afectar(SnowBro s) {
        s.setEnContactoConEscalera(true);
    }

    public void afectar (Enemigo e) {

    }

    public void setSkin (Skin s) {

    }
    @Override
    
    public Skin getSkin() {
        return misAspectos;
    }

    public boolean bloquearMovimientoHorizontal() {
        return false; 
    }

    public boolean esSueloSolido() {
        return false;
    }
}
