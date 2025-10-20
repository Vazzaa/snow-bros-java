package Entidades.Enemigos;

import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;

public abstract class Enemigo implements Colisionable{

    protected int vida;
    protected EstadoEnemigo estado;
    protected int posX, posY;

    public Enemigo(int vida, int posX, int posY){
        this.vida = vida;
        this.estado = new EstadoEnemigo(this);
        this.posX = posX;
        this.posY = posY;
    }  
    public abstract void atacar(Enemigo e);

    public abstract void atacar(SnowBro s);

    public abstract void moverse();

    public abstract void chocar(Colisionable c);
    
    public abstract void recibirDisparo();

    public abstract void crearPowerUp();

    public abstract void setEstado(EstadoEnemigo estado);

    public abstract EstadoEnemigo getEstado();

} 