package Juego;

public interface ControladorJuego {
	
	public void iniciar();
	public void cambiarModoDeJuego();
	public void cambiarDireccionJugador(int n);
	public void activarDominio1();
	public void activarDominio2();
	public void lanzarProyectil();
	public boolean estaColisionando(Entidad e);
	public void moverAbajo();
	public void setNombreJugador(String nombre);
	public Nivel getNivelActual();
	public void detenerHilos();
	public boolean debeMostrarTiempo();
}
