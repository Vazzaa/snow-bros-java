package Hilos;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.PlatMovil;
import EstadoMovimiento.Movible;
import Juego.Nivel;

public class HiloEntidades  extends Thread {
    
    protected Nivel juego;
    private volatile boolean ejecutando = true;

    public HiloEntidades(Nivel juego) {
        this.juego = juego;
    }

    @Override
    public void run() {
    while (ejecutando) {
        for(Enemigo enemigo : juego.getMisEnemigos()) {
            juego.moverEntidad(enemigo);
        }
        try {
            Thread.sleep(16);
            juego.moverEnemigos();
            juego.moverProyectiles();
            juego.verificarColisiones();
            juego.actualizarPowerUps();
            juego.verificarColisionesProyectiles();

            if(juego.getJuego() != null) {
                juego.getJuego().verificarNivelCompletado();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            ejecutando = false;
            break;
        }
    }
    }

    public void detener() {
        ejecutando = false;
    }
}
