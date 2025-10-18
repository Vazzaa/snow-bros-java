package Grafica;

import Entidades.Entidad;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Juego.ControladorJuego;

public class GUI implements ControladorGrafica {

	@Override
	public void registrarControladorJuego(ControladorJuego cj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPantallaPrincipal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPantallaNivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPantallaGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Observer registrarEntidad(EntidadLogica e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observer registrarJugador(EntidadJugador e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sacarEntidad(EntidadLogica e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verificarColisiones(Entidad e) {
		// TODO Auto-generated method stub
		return false;
	}
    
}
