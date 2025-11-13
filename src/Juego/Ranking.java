package Juego;
import Entidades.Jugador.Jugador;

public class Ranking {

    protected Jugador puntajes [];
    protected int cantJugadores = 0;

    public Ranking(){
        puntajes = new Jugador [5];
    }

    public void agregarJugador(String nombre, int puntaje) {
        Jugador nuevoJugador = new Jugador(nombre, puntaje);

        if (cantJugadores < 5 || (puntajes[4] != null && puntaje > puntajes[4].getPuntaje())) {
            int posAAgregar = 0;
            while (posAAgregar < cantJugadores && puntajes[posAAgregar].getPuntaje() > puntaje) {
                posAAgregar++;
            }
            int limite = Math.min(cantJugadores, 4);
            for (int i = limite; i > posAAgregar; i--) {
                puntajes[i] = puntajes[i - 1];
            }
            puntajes[posAAgregar] = nuevoJugador;
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



