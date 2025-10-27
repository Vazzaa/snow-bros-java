package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class ProyectilNieve extends Proyectil{
    
    protected float Alcance;    
    protected int Daño;
    protected int Velocidad;
    protected Skin Aspecto;
    protected Hitbox hb;

    public ProyectilNieve (Skin s, ModoDeJuego juego, Hitbox h) {
       super(s, juego, 5, 10, 100, 100, 12);
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