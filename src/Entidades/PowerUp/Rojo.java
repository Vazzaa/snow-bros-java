package Entidades.PowerUp;

import Entidades.Hitbox;
import Entidades.Skin;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;

public class Rojo extends PowerUp {
    
    protected int puntaje;
    protected float tiempoDeVida;
    protected int duracionSnowBro;

    public Rojo(Skin s, float x, float y, Hitbox h) {
        super(s,(int) x,(int)y, 300, 10);
        puntaje = 300;
        tiempoDeVida = 10;
        duracionSnowBro = 10;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    public void afectar(PowerUp p) {
        
    }

    
    public void setSkin(Skin s) {
        misAspectos = s;
    }

    @Override
    public void afectar(SnowBro snowBro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }

    @Override
    public void afectar(Enemigo enemigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }


}
