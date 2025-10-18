package Entidades.Estructuras;
import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;

abstract class Estructura implements Colisionable{
     //Atributos de instancia
    protected int Puntaje;

    //comandos
    public void afectar(SnowBro s);
    public void afectar (Enemigo e);
}
