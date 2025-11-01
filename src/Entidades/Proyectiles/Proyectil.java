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
    protected int alcance;

    public Proyectil(Skin skins, ModoDeJuego juego,int x, int y, int vel, int dañ, int alc){
        super(skins, juego, x, y);
        velocidad = vel;
        daño = dañ;
        alcance = alc;
    }

    public abstract void afectar(SnowBro snowNBro);

    public abstract void afectar(Enemigo enemigo);

    public abstract void afectar(Estructura estructura);


}