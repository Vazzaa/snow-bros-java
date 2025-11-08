package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class Pared extends Obstaculo{

    public Pared(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(16);
        miHitbox.setAlto(64);
    }

    public void afectar(SnowBro s) {
        int snowBrox = s.getPosX();
        int paredx = getPosX();
        int paredAncho = miHitbox.getAncho();
        int centroDelSnowBroX = snowBrox + s.getHitbox().getAncho() / 2;
        int centroParedX = paredx + paredAncho / 2;

        if (centroDelSnowBroX < centroParedX) {
            int nuevaX = paredx - s.getHitbox().getAncho();
            s.setPosX(nuevaX);
        } else {
            int nuevaX = paredx + paredAncho;
            s.setPosX(nuevaX);
        }
        s.detenerMovimiento();
        s.notificarObserver();
    }

    public void afectar (Enemigo e) {

    }

    public void setSkin (Skin s) {

    }
    
    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public boolean esColisionable() {
        return true;
    }

    @Override
    public boolean esSuelo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esSuelo'");
    }

    @Override
    public boolean esMovible() {
        return false;
    }
}

