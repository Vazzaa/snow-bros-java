package Juego;

import Entidades.Jugador.Jugador;
import Grafica.ObserverJugador;

public interface EntidadJugador extends EntidadLogica {
	
	public String getNombre();
	public int getPuntaje();
	public Jugador getJugador();
	public int getVida();
	public int getVelocidad();
}
