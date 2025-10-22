package Grafica;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entidades.*;
import Juego.Entidad;
import Juego.EntidadLogica;

public class ObserverGrafico extends JLabel implements Observer {
	
	private static final long serialVersionUID = 1L;
	protected EntidadLogica entidadObservada;
	
	public ObserverGrafico(EntidadLogica e) {
		super();
		entidadObservada = e;
		actualizar();
	}
	
	public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamaño();
	}
	
	protected void actualizarImagen() {
		String rutaImagen = entidadObservada.getSkins().getRutaImagenActual();
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
	
	public void notificarFinColision(Entidad e) {
		// TO-DO
	}

	public void actualizarPosicionTamaño(){
		int x = AdaptadorPosicionPixel.transformar_x(entidadObservada.getPosX());
		int y = AdaptadorPosicionPixel.transformar_y(entidadObservada.getPosY());
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y, ancho, alto);
	}
}
