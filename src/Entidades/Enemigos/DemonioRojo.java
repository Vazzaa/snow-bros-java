package Entidades.Enemigos;

import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.Skin;
import Visitors.Colisionable;
import Visitors.Colisionador;

public class DemonioRojo extends Enemigo implements EstadoEnemigo{

    public DemonioRojo(Skin skins, int posX, int posY){
        super(skins, posX, posY, 3,300);
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

    @Override
    public void crearPowerUp() {
        // TODO Auto-generated method stub
        
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
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    @Override
    public void aceptarColision(Colisionador c) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afectar(SnowBro snowBro) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afectar(Enemigo enemigo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afectar(PowerUp powerUp) {
        // TODO Auto-generated method stub
        
    }

}
