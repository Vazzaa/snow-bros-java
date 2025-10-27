package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Visitors.Colisionable;
import Visitors.Colisionador;

abstract class Obstaculo extends Estructura implements Colisionable{

    public Obstaculo(Skin s, int x, int y) {
        super(s, x, y);
    }
    public void afectar(SnowBro s){};

    public void afectar (Enemigo e){};

    public void aceptarColision(Colisionador c){
        c.colisionar(this);
    }
}
