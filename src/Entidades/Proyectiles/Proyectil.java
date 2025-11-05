package Entidades.Proyectiles;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.Entidad;
import Juego.ModoDeJuego;
import Visitors.Colisionable;
import Visitors.Colisionador;
import Entidades.Estructuras.Estructura;

public abstract class Proyectil extends Entidad implements Colisionable{

    protected int velocidad;
    protected int daño;
    protected int alcance;
    protected int direccion; // 0 para derecha, 180 para izquierda

    private long tiempoFinVida = 0;
    private boolean vidaActiva = true;

    public Proyectil(Skin skins, ModoDeJuego juego,int x, int y, int vel, int dañ, int alc, int dir){
        super(skins, juego, x, y);
        velocidad = vel;
        daño = dañ;
        alcance = alc;
        direccion = dir;
    }

    public void mover() {
        if (direccion == 0) { // Derecha
            miHitbox.setPosX(miHitbox.getPosX() + velocidad);
        } else { // Izquierda
            miHitbox.setPosX(miHitbox.getPosX() - velocidad);
        }

        // Futura lógica de colisiones y alcance puede ir aquí.
        verificarTemporizadorVida();
        notificarObserver();
    }

    public void activarTemporizadorVida() {
        tiempoFinVida = System.currentTimeMillis() + alcance;
        vidaActiva = true;
    }

    public void verificarTemporizadorVida() {
        if(vidaActiva && System.currentTimeMillis() >= tiempoFinVida) {
            eliminar();
        }
    }

    public void eliminar() {
        vidaActiva = false;
        miJuego.getControladoraGrafica().sacarEntidad(this);
    }

    public abstract void afectar(SnowBro snowNBro);

    public abstract void afectar(Enemigo enemigo);

    public abstract void afectar(Estructura estructura);

    public abstract int getAlcance();

    public void aceptarColision(Colisionador c) {
        c.colisionarProyectil(this);
    }

    public abstract boolean afectaAEnemigos();

    public boolean estaActivo() {
        return vidaActiva;
    }

    public int getDaño() {
        return daño;
    }
}