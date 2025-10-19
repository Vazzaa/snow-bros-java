package Entidades.Enemigos;

import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;

abstract class Enemigo implements Colisionable{

    protected int vida;
    protected EstadoEnemigo estado;

    public Enemigo(int vida){
        this.vida = vida;
        this.estado = new EstadoEnemigo(this);
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