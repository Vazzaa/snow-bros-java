package Entidades.Enemigos;

import EstadoMovimiento.EstadoEnemigo;
import Fabricas.Skin;
import Juego.Entidad;
import Juego.ModoDeJuego;
import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;
import Visitors.Colisionador;
import EstadoMovimiento.Movible;

public abstract class Enemigo extends Entidad implements Colisionable, Movible {

    protected int vida;
    protected int puntaje;
    protected EstadoEnemigo estadoEnemigo;
    
    
    public Enemigo(Skin skins, ModoDeJuego juego , int posX, int posY, int v, int p){
        super(skins,juego,posX,posY);
        int vida = v;
        int puntaje = p;

    }

    public void setVida(int v){
        vida = v;
    }

    public void setPuntaje(int p){
        puntaje = p;
    }

    public int getVida(){
        return vida;
    }

    public int getPuntaje(){
        return puntaje;
    }
    
    
    public abstract void atacar(Enemigo e);

    public abstract void atacar(SnowBro s);

    public abstract void moverse();

    public abstract void chocar(Colisionable c);
    
    public abstract void recibirDisparo();

    public abstract void setEstado(EstadoEnemigo estado);

    public abstract EstadoEnemigo getEstado();
    
    @Override
    public void aceptarColision(Colisionador c) {
        c.colisionar(this);
    }

} 