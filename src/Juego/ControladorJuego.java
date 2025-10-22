package Juego;

public interface ControladorJuego {
	
	public void iniciar();
	public void cambiarModoDeJuego();
	public void cambiarDireccionJugador(int n);
	public void lanzarProyectil();
	public boolean estaColisionando(Entidad e);
	public void moverAbajo();
}
