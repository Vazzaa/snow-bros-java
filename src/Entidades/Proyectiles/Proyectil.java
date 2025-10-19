package Entidades.Proyectiles;

import Entidades.Entidad;
import Entidades.Skin;
import Entidades.SnowBro.SnowBro;
import Entidades.Estructuras.Estructura;

public abstract class Proyectil extends Entidad{

    protected int velocidad;
    protected int daño;
    protected float alcance;

    public Proyectil();

    public void afectar(SnowBro snowNBro);

    public void afectar(Enemigo enemigo);

    public void afectar(Estructura estructura);

    @Override
    public float getPosX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getPosY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Skin getSkin() {
        // TODO Auto-generated method stub
        return null;
    }




}