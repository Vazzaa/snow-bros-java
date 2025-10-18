package Grafica;
import Entidades.*;
import Juego.ControladorJuego;

public interface ControladorGrafica {
	
	public void registrarControladorJuego(ControladorJuego cj);
	public void mostrarPantallaPrincipal();
	public void mostrarPantallaNivel();
	public void mostrarPantallaGameOver();
	public Observer registrarEntidad(EntidadLogica e);
	public Observer registrarJugador(EntidadJugador e);
	public void sacarEntidad(EntidadLogica e);
	public boolean verificarColisiones(Entidad e);
	
}
