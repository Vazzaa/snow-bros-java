package Hilos;

import Juego.Nivel;

public class HiloEntidades  extends Thread {
    
    protected Nivel juego;

    public HiloEntidades(Nivel juego) {
        this.juego = juego;
    }

    @Override
    public void run() {
     while (true) {
        juego.moverEntidad(null);
        try {
            sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
}
