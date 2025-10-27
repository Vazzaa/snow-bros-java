package Entidades.Enemigos;

import Entidades.Proyectiles.ProyectilFuego;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;

public class Moghera extends Enemigo{

    protected FabricaEntidades creacionFuego;

    public Moghera(Skin skins,ModoDeJuego juego ,int posX, int posY){
        super(skins, juego, posX, posY, 5,300);
    }

    @Override
    public void atacar(Enemigo e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atacar(SnowBro s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chocar(Colisionable c) {
        // TODO Auto-generated method stub
        
    }

    public ProyectilFuego dispararFuego() {
        return null;
    }

    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void recibirDisparo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void morir(){
        
    }
}
