package Hilos;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.PlatMovil;
import EstadoMovimiento.Movible;
import Juego.Nivel;

public class HiloEntidades  extends Thread {
    
    protected Nivel juego;

    public HiloEntidades(Nivel juego) {
        this.juego = juego;
    }

    @Override
    public void run() {
    while (true) {
        for(Enemigo enemigo : juego.getMisEnemigos()) {
            juego.moverEntidad(enemigo);
        }
        try {
            Thread.sleep(10);
            juego.moverEnemigos();
<<<<<<< HEAD
=======
            juego.moverProyectiles();
            juego.verificarColisiones();

>>>>>>> 3ca937aa58d02592a79f6358572d1fb808522317
        } catch (InterruptedException e) {
            e.printStackTrace();
            break;
        }
    }
    }
}
