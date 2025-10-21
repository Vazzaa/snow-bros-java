package Entidades.Estructuras;

import Entidades.Hitbox;
import Entidades.Skin;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;

abstract class Obstaculo extends Estructura{

    public Obstaculo(Skin s, Hitbox h) {
        super(s,(int)h.getPosX(),(int)h.getPosY());
    }

    public void afectar(SnowBro s){};

    public void afectar (Enemigo e){};
}
