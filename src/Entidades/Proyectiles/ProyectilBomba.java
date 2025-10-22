package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;

public class ProyectilBomba extends Proyectil{
    
    protected float Alcance;
    protected int Daño;
    protected int Velocidad;
    protected Skin Aspecto;
    protected Hitbox hb;

    public ProyectilBomba(Skin s, Hitbox h) {
        super(s, 5, 10, 100, 100, 12);
    }

    public void Explotar() {

    }

    public void afectar(SnowBro s) {

    }

    public void afectar(Enemigo e) {

    }

    public void setSkin(Skin s) {
        
    }
    
    @Override
    public Skin getSkins() {
        return misAspectos;
    }
}
