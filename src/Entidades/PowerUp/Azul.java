package Entidades.PowerUp;

import java.util.List;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Grafica.ObserverGrafico;
import Juego.Hitbox;

public class Azul extends PowerUp {

    protected int puntaje;
    protected float tiempoDeVida;
    protected int duracionSnowBro;

    public Azul(Skin s, float x, float y, Hitbox h) {
        super(s,(int) x,(int)y, 300, 10);
        puntaje = 300;
        tiempoDeVida = 10;
        duracionSnowBro = 10;
    }

    public int getPuntaje() {
        return puntaje;
    }
      
    public void setSkin(Skin s) {
        misAspectos = s;
    }
    
    @Override
    public void afectar(SnowBro snowBro) {
        snowBro.setVelocidad(snowBro.getVelocidad() * 2);
        snowBro.sumarPuntaje(puntaje);
        notificarObserver();
        //eliminar();
    }
    
    @Override
    public void afectar(Enemigo enemigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }
    
    public void afectar(PowerUp p) {
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    @Override
    public List<ObserverGrafico> getObserversGrafico() {
        // TODO Auto-generated method stub
        return null;
    }
}
