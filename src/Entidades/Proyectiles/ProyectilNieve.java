package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class ProyectilNieve extends Proyectil{
    
    public ProyectilNieve (Skin s, ModoDeJuego juego,int x, int y,int dir) {
        super(s, juego, x, y, 5, 1, 1000, dir);
        activarTemporizadorVida();
    }

    public void afectar(SnowBro s) {

    }

    public void afectar(Enemigo e) {
        e.recibirDisparo(); 
        this.eliminar();    
    }

    public void afectar(Estructura es) {
        
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
}