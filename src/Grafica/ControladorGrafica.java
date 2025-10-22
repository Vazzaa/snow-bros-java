package Grafica;
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
	public Observer registrarEntidad(EntidadLogica e);
	public Observer registrarJugador(EntidadJugador e);
	public void sacarEntidad(EntidadLogica e);
	public void sacarJugador(EntidadJugador e);
	public boolean verificarColisiones(Entidad e);
<<<<<<< HEAD
}
=======
	
}
>>>>>>> e5cd332f09d85be70e117c946da8959b12f29755
