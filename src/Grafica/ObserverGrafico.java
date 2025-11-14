package Grafica;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Juego.Entidad;
import Juego.EntidadLogica;
import java.awt.Image; 

public class ObserverGrafico extends JLabel implements Observer {
	
	private static final long serialVersionUID = 1L;
	protected EntidadLogica entidadObservada;
	
	public ObserverGrafico(EntidadLogica entidadLogic) {
		super();
		entidadObservada = entidadLogic;
		actualizar();
	}
	
	public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamaño();
	}
	
	protected void actualizarImagen() {
		String rutaImagen = entidadObservada.getSkin().getRutaImagenActual();
		
		if (rutaImagen == null) {
			System.err.println("Error: La ruta de la imagen es null para la entidad: " + entidadObservada.getClass().getSimpleName());
			return;
		}
    	java.net.URL url = getClass().getClassLoader().getResource(rutaImagen);
		if (url == null) {
        	System.err.println("No se pudo encontrar la imagen en: " + rutaImagen);
        	return;
    	}
    	ImageIcon icono = new ImageIcon(url);

		int renderAncho = entidadObservada.getRenderAncho();
		int renderAlto = entidadObservada.getRenderAlto();

		if (renderAncho > 0 && renderAlto > 0) {
			Image img = icono.getImage();
			Image scaledImg = img.getScaledInstance(renderAncho, renderAlto, Image.SCALE_FAST);
			ImageIcon scaledIcono = new ImageIcon(scaledImg);
			setIcon(scaledIcono);
		} else {
			setIcon(icono);
		}

		if (rutaImagen != null && rutaImagen.contains("pared")) {
			if (getIcon().getIconWidth() == 0 || getIcon().getIconHeight() == 0) {
				System.err.println("ERROR: La imagen tiene dimensiones 0x0 - " + rutaImagen);
			}
		}
	}
	
	public void notificarFinColision(Entidad e) {
		// TO-DO
	}

	public void actualizarPosicionTamaño(){
		int x_logico = entidadObservada.getPosX();
		int y_logico = entidadObservada.getPosY();
		
		int x_pantalla = AdaptadorPosicionPixel.transformar_x(x_logico);
		int y_pantalla = AdaptadorPosicionPixel.transformar_y(y_logico);
		
		if (this.getIcon() != null) {
			int ancho = this.getIcon().getIconWidth();
			int alto = this.getIcon().getIconHeight();

			if (entidadObservada.getRenderAncho() > 0) {
				y_pantalla = y_pantalla - (alto / 2);
			}

			setBounds(x_pantalla, y_pantalla, ancho, alto);
			
		} else {
			setBounds(x_pantalla, y_pantalla, 32, 32);
		}
	}

}