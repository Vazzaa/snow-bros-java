package Grafica;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entidades.*;

public class ObserverGrafico extends JLabel implements Observer {
	
	// Atributos
	private static final long serialVersionUID = 1L;
	protected EntidadLogica entidad_observada;
	
	// Comandos
	public ObserverGrafico(EntidadLogica e) {
		super();
		entidad_observada = e;
		actualizar();
	}
	
	public void actualizar() {
		actualizarImagen();
		// TODO: actualizar_posicion_tamano();
		// ¿Es lo mismo que notificarFinColision()?
	}
	
	protected void actualizarImagen() {
		String ruta_imagen = entidad_observada.getSkin().getRutaImagenActual();
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta_imagen));
		setIcon(icono);
	}
	
	public void notificarFinColision(Entidad e) {
		// TODO
	}
}
