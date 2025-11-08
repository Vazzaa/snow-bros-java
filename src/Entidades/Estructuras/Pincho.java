package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Entidades.Proyectiles.ProyectilNieve;

public class Pincho extends Obstaculo{

    public Pincho(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y);
    }
    
    public void afectar(SnowBro s) {
        s.disminuirVida();
        s.setPosX(10);
        s.setPosY(7650);
        s.resetVelocidad();
        s.notificarObserver();
        System.out.println("Pincho: SnowBro afectado");
        if (s.getVida() <= 0) {
            s.morir();
        }
        }

    public void afectar (Enemigo e) {

    }

    public void destruir (ProyectilNieve n) {

    }

    public void setSkin(Skin s) {
        
    }
    
    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public boolean esColisionable() {
        return true;
    }

    @Override
    public boolean esSuelo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esSuelo'");
    }

    @Override
    public boolean esMovible() {
        return false;
    }
}
