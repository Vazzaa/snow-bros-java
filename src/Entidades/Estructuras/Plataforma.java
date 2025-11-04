package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Visitors.Colisionador;
import Juego.ModoDeJuego;

public class Plataforma extends Estructura{

    protected boolean esPlataformaSuelo;

    public Plataforma(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(12);
        miHitbox.setAlto(32);
        esPlataformaSuelo = false;
    }

    public Plataforma(Skin s, ModoDeJuego juego, int x, int y, boolean esSuelo) {
        super(s, juego, x, y);
        miHitbox.setAncho(12);
        miHitbox.setAlto(32);
        esPlataformaSuelo = esSuelo;
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

    public void aceptarColision(Colisionador c){
        c.colisionarEstructura(this);
    }

    public boolean bloquearMovimientoHorizontal() {
        return false;
    }

    public boolean esSuelo() {
        return esPlataformaSuelo;
    }
}
