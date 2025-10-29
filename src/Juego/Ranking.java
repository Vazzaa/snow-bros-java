package Juego;
import Entidades.Jugador.Jugador;

public class Ranking {

    protected Jugador puntajes [];
    protected int cantJugadores = 0;

    public Ranking(){
        puntajes = new Jugador [5];
    }

    // public void agregarJugador(Jugador jug){
    //     if (cantJugadores < 5 || jug.getPuntaje() > puntajes[4].getPuntaje()) {
    //         int PosAAgregar = 0;
    //         while (PosAAgregar < cantJugadores && puntajes[PosAAgregar].getPuntaje() > jug.getPuntaje()) {
    //             if (puntajes[PosAAgregar].getPuntaje() < jug.getPuntaje()) {
    //                 PosAAgregar++;
    //             }
    //         }
    //         int limite;
    //         if (cantJugadores < 5) {
    //             limite = cantJugadores;
    //         } 
    //         else {
    //             limite = 4;
    //         }
    //         for (int i = limite - 1; i >= PosAAgregar; i--) {
    //             puntajes[i + 1] = puntajes[i];
    //         }
    //         puntajes[PosAAgregar] = jug;
    //         if (cantJugadores < 5) {
    //             cantJugadores++;
    //         }
    //     }
    // }
    public void agregarJugador(Jugador jug) {
        if (cantJugadores < 5 || (puntajes[4] != null && jug.getPuntaje() > puntajes[4].getPuntaje())) {
            int posAAgregar = 0;
            while (posAAgregar < cantJugadores && puntajes[posAAgregar].getPuntaje() > jug.getPuntaje()) {
                posAAgregar++;
            }
            int limite = Math.min(cantJugadores, 4);
            for (int i = limite; i > posAAgregar; i--) {
                puntajes[i] = puntajes[i - 1];
            }
            puntajes[posAAgregar] = jug;
            if (cantJugadores < 5) {
                cantJugadores++;
            }
        }
    }


    public Jugador[] getPuntajes() {
        return puntajes;
    }
    public int getCantJugadores() {
        return cantJugadores;
    }

    
}



