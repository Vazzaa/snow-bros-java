package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Entidades.Proyectiles.*;

public class Pincho extends Obstaculo{

    public Pincho(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(32);  
        miHitbox.setAlto(32);   
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
    public void afectar(Proyectil p) {
        long tiempoActual = System.currentTimeMillis();
        long tiempoCreacion = p.getTiempoCreacion();
        if (tiempoActual - tiempoCreacion > 50) {
            p.eliminar();
        }
    }

    public void destruir (ProyectilNieve n) {
        long tiempoActual = System.currentTimeMillis();
        long tiempoCreacion = n.getTiempoCreacion();
        if (tiempoActual - tiempoCreacion > 50) {
            n.eliminar();
        }
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
    public boolean bloquearMovimientoHorizontal() {
        return false;  // Cambiar a false para que no bloquee el movimiento
    }
}
