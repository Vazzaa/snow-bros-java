package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class SueloResbaladizo extends Obstaculo {

    public SueloResbaladizo(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(16);
        miHitbox.setAlto(32);
    }

    public void afectar(SnowBro s) {
        s.setEstaResbalando(true);
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
        return true;
    }

    @Override
    public boolean bloquearMovimientoHorizontal() {
        return false;
    }

    @Override
    public boolean esMovible() {
        return false;
    }

    @Override
    public boolean esSueloResbaladizo() {
        return true;
    }
}

