package Fabricas;
import java.util.Map;

public class Skin {
	
	// Atributos
	protected Map<Integer, String> mapeoImagenEstado;
	protected int estadoActual;
	
	// Comandos
	public Skin(Map<Integer, String> mapero_estado_imagen, int estado_actual) {
		mapeoImagenEstado = mapero_estado_imagen;
		estadoActual = estado_actual;
	}
	
	public void setEstadoActual(int ea) {
		estadoActual = ea;
	}
	
	public String getRutaImagenActual() {
		// System.out.println(estadoActual);
		// for (int estado : mapeoImagenEstado.keySet()) {
		// 	System.out.println("Estado: " + estado + " - Ruta: " + mapeoImagenEstado.get(estado));
		// }
		return mapeoImagenEstado.get(estadoActual);
	}
}
