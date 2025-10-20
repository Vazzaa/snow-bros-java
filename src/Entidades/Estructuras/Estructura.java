package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;
import Entidades.Skin;
import Entidades.Entidad;

public abstract class Estructura extends Entidad implements Colisionable{


    public Estructura(Skin skins, int x, int y){
        super(skins, x, y);
    }

    public void afectar(SnowBro s);

    public void afectar (Enemigo e);
}
