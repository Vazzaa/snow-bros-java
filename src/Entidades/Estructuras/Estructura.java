package Entidades.Estructuras;
import Entidades.SnowBro.SnowBro;

abstract class Estructura {
    //Atributos de instancia
    protected int Puntaje;

    //comandos
    public void afectar(SnowBro s);
    public void afectar (Enemigo e);

}
