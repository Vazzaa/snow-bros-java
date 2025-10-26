package Visitors;

import Entidades.Enemigos.Enemigo;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;

public interface Colisionable {
    public void aceptarColision(Colisionador c);
}
