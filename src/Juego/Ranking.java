package Juego;
import Entidades.Jugador.Jugador;

public class Ranking {

    protected Jugador puntajes [];
    protected int cantJugadores = 0;

    public Ranking(){
        puntajes = new Jugador [5];
    }

    public void agregarJugador(String nombre, int puntaje) {
        int posicionExistente = buscarJugador(nombre);
        
        if (posicionExistente != -1) {
            if (puntaje > puntajes[posicionExistente].getPuntaje())
                eliminarEnPosicion(posicionExistente);
            else
                return;
        }

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

    private int buscarJugador(String nombre) {
        for (int i = 0; i < cantJugadores; i++) {
            if (puntajes[i] != null && puntajes[i].getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private void eliminarEnPosicion(int pos) {
        for (int i = pos; i < cantJugadores - 1; i++) {
            puntajes[i] = puntajes[i + 1];
        }
        puntajes[cantJugadores - 1] = null;
        cantJugadores--;
    }


    public Jugador[] getPuntajes() {
        return puntajes;
    }
    public int getCantJugadores() {
        return cantJugadores;
    }

    
}



