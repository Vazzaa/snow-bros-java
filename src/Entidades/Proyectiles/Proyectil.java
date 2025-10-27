package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Entidad;
import Juego.ModoDeJuego;
import Entidades.Estructuras.Estructura;

public abstract class Proyectil extends Entidad{

    protected int velocidad;
    protected int daño;
    protected float alcance;

    public Proyectil(Skin skins, ModoDeJuego juego,int x, int y, int v, int d, int a){
        super(skins, juego, x, y);
        velocidad = v;
        daño = d;
        alcance = a;
    }

    public void afectar(SnowBro snowNBro){};

    public void afectar(Enemigo enemigo){};

    public void afectar(Estructura estructura){}


}