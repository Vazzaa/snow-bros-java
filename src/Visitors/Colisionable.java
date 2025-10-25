package Visitors;

import Entidades.Enemigos.Enemigo;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;

public interface Colisionable {
    
    public void afectar(SnowBro snowBro);
    public void afectar(Enemigo enemigo);
    public void afectar(PowerUp powerUp);
}
