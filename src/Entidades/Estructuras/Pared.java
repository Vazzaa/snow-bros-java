package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;

public class Pared extends Obstaculo{

     public Pared(Skin s, int x, int y) {
        super(s, x, y);
    }

    public void afectar(SnowBro s) {

    }

    public void afectar (Enemigo e) {

    }

    public void setSkin (Skin s) {

    }
    
    @Override
    public Skin getSkins() {
        return misAspectos;
    }
}

