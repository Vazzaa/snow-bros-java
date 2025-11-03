package Visitors;

import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;

public interface Colisionador {

    public void colisionarPowerUp(PowerUp p);
    public void colisionarEnemigo(Enemigo e);
    public void colisionarEstructura(Estructura e);
    public void colisionarObstaculo(Obstaculo o);
    public void colisionarProyectil(Proyectil p);

}
