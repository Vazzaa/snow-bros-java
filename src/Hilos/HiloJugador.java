package Hilos;

import Juego.Nivel;

public class HiloJugador extends Thread {
    
    protected Nivel juego;
    private volatile boolean ejecutando = true;

    public HiloJugador(Nivel juego){
        this.juego = juego;
    }

    public void run(){
        while(ejecutando){
            juego.moverSnowBro();
            juego.verificarColisiones();
            try {
                sleep(16);
            } catch (InterruptedException e) {
                ejecutando = false;
                e.printStackTrace();
            }
        }
    }

    public void detener() {
        ejecutando = false;
    }

}

