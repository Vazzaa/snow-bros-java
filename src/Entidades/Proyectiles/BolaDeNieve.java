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
    public void afectar(SnowBro snowBro) {
        this.velocidad = 5;
        this.direccion = snowBro.getEstadoMovimiento().direccion;
    }

    @Override
    public void afectar(Enemigo enemigo) {
        // No aplica en este caso
    }

    @Override
    public void afectar(Estructura estructura) {
        estructura.afectar(this);
    }

    public int getAlcance() {
        return alcance;
    }

    public void setVelocidad(int nuevaVelocidad) {
        this.velocidad = nuevaVelocidad;
    }

    public boolean afectaAEnemigos() {
        return true;
    }

    public boolean puedeRomperParedDestructible() {
        return true;
    }
}
