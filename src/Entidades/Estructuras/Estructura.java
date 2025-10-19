package Entidades.Estructuras;
import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;

public abstract class Estructura implements Colisionable{

    protected int Puntaje;


    public void afectar(SnowBro s);

    public void afectar (Enemigo e);
}
