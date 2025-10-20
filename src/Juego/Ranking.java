package Juego;
import Entidades.Jugador.Jugador;

public class Ranking {

    protected Jugador puntajes [];
    protected int cantJugadores = 0;

    public Ranking(){
        puntajes = new Jugador [5];
    }

    public void agregarJugador(Jugador jug){
        if (cantJugadores < 5 || jug.getPuntaje() > puntajes[4].getPuntaje()) {
            int i = 0;
            int PosAAgregar = cantJugadores;
            for (int i = 0; i < cantJugadores; i++) {
                if (puntajes[i].getPuntaje() < jug.getPuntaje()) {
                    PosAAgregar = i;
                }
            }
            int limite;
            if (cantJugadores < 5) {
                limite = cantJugadores;
            } 
            else {
                limite = 4;
            }
            for (int i = limite - 1; i >= posicionAInsertar; i--) {
                puntajes[i + 1] = puntajes[i];
            }
            puntajes[posicionAInsertar] = jug;
            if (cantJugadores < 5) {
                cantJugadores++;
            }
        }
    }
}



