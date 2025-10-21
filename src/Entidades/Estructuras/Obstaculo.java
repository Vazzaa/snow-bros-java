package Entidades.Estructuras;

import Entidades.Hitbox;
import Entidades.Skin;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;

abstract class Obstaculo extends Estructura{

    public Obstaculo(Skin s, int x, int y) {
        super(s, x, y);
    }
    public void afectar(SnowBro s){};

    public void afectar (Enemigo e){};
}
