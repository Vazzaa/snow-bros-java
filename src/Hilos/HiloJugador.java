package Hilos;

import Juego.Nivel;

public class HiloJugador extends Thread {
    
    protected Nivel juego;

    public HiloJugador(Nivel juego){
        this.juego = juego;
    }

    public void run(){
        while(true){
            juego.moverSnowBro();
            try {
                sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

