package Hilos;

import Entidades.Enemigos.Enemigo;
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
            juego.moverEstructurasMoviles();
            juego.moverProyectiles();
            juego.verificarColisiones();
            juego.actualizarPowerUps();
            juego.verificarColisionesProyectiles();
            juego.limpiarEnemigosCaidosDelMapa();
            juego.actualizarAparicionCalabaza();
            juego.actualizarAparicionVida();

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
