package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Entidad;
import Visitors.Colisionable;
import Visitors.Colisionador;
import Juego.ModoDeJuego;

public abstract class Estructura extends Entidad implements Colisionable{

    public Estructura(Skin skins, ModoDeJuego juego, int x, int y){
        super(skins, juego, x, y);
    }

    public void afectar(SnowBro s){};

    public void afectar (Enemigo e){};

    @Override
    public void aceptarColision(Colisionador c){
        c.colisionarEstructura(this);
    }
}