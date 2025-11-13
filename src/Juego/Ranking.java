package Juego;
import Entidades.Jugador.Jugador;

public class Ranking {

    protected Jugador puntajes [];
    protected int cantJugadores = 0;

    public Ranking(){
        puntajes = new Jugador [5];
    }

    public void agregarJugador(Jugador jug) {
        int posicionExistente = buscarJugador(jug.getNombre());
        
        if (posicionExistente != -1) {
            if (jug.getPuntaje() > puntajes[posicionExistente].getPuntaje())
                eliminarEnPosicion(posicionExistente);
            else
                return;
        }

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



