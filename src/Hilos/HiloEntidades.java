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
    /*
    for(Movible estrucmovible: juego.getMisEstructuras()) {
        juego.moverEntidad(estrucmovible);
    } */
        try {
            Thread.sleep(1000);
            juego.moverEnemigos();

        } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }

}
