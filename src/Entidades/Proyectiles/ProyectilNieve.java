package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class ProyectilNieve extends Proyectil{
    
    public ProyectilNieve (Skin s, ModoDeJuego juego,int x, int y, int vel, int dañ, int alc) {
       super(s, juego, x, y, vel, dañ, alc);
    }

    public void afectar(SnowBro s) {

    }

    public void afectar(Enemigo e) {

    }

    public void afectar(Estructura es) {
        
    }

    public void setSkin(Skin s) {
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

}