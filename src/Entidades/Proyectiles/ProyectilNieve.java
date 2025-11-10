package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class ProyectilNieve extends Proyectil{

    protected int velocidadVertical = 0;  
    protected int gravedad = 1;
    
    public ProyectilNieve (Skin s, ModoDeJuego juego,int x, int y,int dir) {
        super(s, juego, x, y, 9, 1, 1000, dir);
        activarTemporizadorVida();
        velocidadVertical = 6;
    }

    public void mover() {
        if (direccion == 0) { 
            miHitbox.setPosX(miHitbox.getPosX() + velocidad);
        } else { 
            miHitbox.setPosX(miHitbox.getPosX() - velocidad);
        }

        velocidadVertical -= gravedad;
        miHitbox.setPosY(miHitbox.getPosY() + velocidadVertical);
        verificarTemporizadorVida();
        notificarObserver();

    }
    public void afectar(SnowBro s) {

    }

    public void afectar(Enemigo e) {
        e.recibirDisparo(); 
        this.eliminar();    
    }

    public void afectar(Estructura es) {
        es.afectar(this);  
    }

    public void setSkin(Skin s) {
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public int getAlcance() {
        return alcance;
    }
    
    public boolean afectaAEnemigos() {
        return true;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }
}