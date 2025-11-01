package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import Visitors.Colisionador;

public abstract class Obstaculo extends Estructura implements Colisionable{

    public Obstaculo(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y);
    }
    public void afectar(SnowBro s){};

    public void afectar (Enemigo e){};

    public void aceptarColision(Colisionador c){
        c.colisionarObstaculo(this);
    }
    public boolean bloquearMovimientoHorizontal() {
        return true;
    }
}
