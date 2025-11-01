package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class BolaDeNieve extends Proyectil{

    public BolaDeNieve(Skin miSkins, ModoDeJuego juego, int x, int y, int vel, int dañ, int alc, int dir){
        super(miSkins, juego, x, y, vel, dañ, alc, dir);
    }

    @Override
    public void afectar(SnowBro snowNBro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }

    @Override
    public void afectar(Enemigo enemigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }

    @Override
    public void afectar(Estructura estructura) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afectar'");
    }
}
