package Entidades;
import java.util.Map;

public class Skin {
	
	// Atributos
	protected Map<Integer, String> mapeoImagenEstado;
	protected int estadoActual;
	
	// Comandos
	public Skin(Map<Integer, String> mie, int ea) {
		mapeoImagenEstado = mie;
		estadoActual = ea;
	}
	
	public void setEstadoActual(int ea) {
		estadoActual = ea;
	}
	
	public String getRutaImagenActual() {
		// TODO: ver que argumento hay que pasar.
		return null;
	}
}
