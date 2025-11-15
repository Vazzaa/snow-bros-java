package Grafica;
import Entidades.Jugador.Jugador;
import Juego.ControladorJuego;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.EntidadLogica;

public interface ControladorGrafica {
	
	public void registrarControladorJuego(ControladorJuego cj);
	public void mostrarPantallaPrincipal();
	public void mostrarPantallaNivel();
	public void mostrarPantallaGameOver();
	public void mostrarPantallaRanking();
	public void mostrarPantallaElegirDominio();
	public void mostrarPantallaElegirModoJuego();
	public void mostrarPantallaVictoria();
	public Observer registrarEntidad(EntidadLogica e);
	public Observer registrarJugador(EntidadJugador e);
	public void sacarEntidad(EntidadLogica e);
	public void sacarJugador(EntidadJugador e);
	public boolean verificarColisiones(Entidad e);
	public void actualizarTiempo(String timepo);
	public void actualizarNivel(int numeroNivel);
	public void setImagenDeFondoNivel(String rutaImagen);
	public void agregarAlRanking(Jugador jugador);
	public void limpiarNivel();
}
