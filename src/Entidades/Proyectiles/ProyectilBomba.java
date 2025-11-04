package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Hitbox;
import Juego.ModoDeJuego;

public class ProyectilBomba extends Proyectil{
    
    protected int alcance;
    protected int daño;
    protected int velocidad;
    protected Skin aspecto;
    protected Hitbox hb;

    public ProyectilBomba(Skin s, ModoDeJuego juego, int x, int y, int vel, int dañ, int alc, int dir) {
        super(s, juego, x, y, vel, dañ, alc, dir);
    }

    public void Explotar() {

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

    @Override
    public int getAlcance() {
        return alcance;
    }

    @Override
    public boolean afectaAEnemigos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectaAEnemigos'");
    }
}
