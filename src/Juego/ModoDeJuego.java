package Juego;
import Grafica.*;

public abstract class ModoDeJuego implements ControladorJuego {
    
	// Atributos
	protected Ranking rank;
	protected ControladorGrafica controlaGrafica;
	protected Nivel miNivel;
	
	// Comandos
	public ModoDeJuego() {
		// TODO
		// Creería que al constructor se le tendrían que pasar parámetros para inicializar rank, controlaGrafica y miNivel.
	}
	
	public void cambiarModoDeJuego(int modo) {
		// TODO
		// ¿Y ese entero que haría? No hay ningún atributo de tipo int. En la interfaz ControladorJuego, este método no recibe ningún parámetro.
	}
	
	public void cambiarDireccionJugador() {
		// TODO
		// En la interfaz ControladorJuego, cambiarDireccionJugador() recibe un entero n.
		
		// No hay ningún método en ControladorGrafica que controle la dirección de un jugador:
	 	// registrarJugador() recibe un jugador y lo registra, y verificarColisiones() verifica las colisiones, no los movimientos.
	}
}
