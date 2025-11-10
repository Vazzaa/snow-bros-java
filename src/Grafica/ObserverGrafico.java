package Grafica;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Juego.Entidad;
import Juego.EntidadLogica;

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
		// System.out.println("Intentando cargar imagen desde: " + rutaImagen);
		if (rutaImagen != null && rutaImagen.contains("pared")) {
			System.out.println("DEBUG IMAGEN: Intentando cargar " + rutaImagen + " para " + entidadObservada.getClass().getSimpleName());
		}
    	java.net.URL url = getClass().getClassLoader().getResource(rutaImagen);
		if (rutaImagen == null) {
			System.err.println("Error: La ruta de la imagen es null para la entidad: " + entidadObservada.getClass().getSimpleName());
			return;
		}
		if (url == null) {
        	System.err.println("No se pudo encontrar la imagen en: " + rutaImagen);
        	return;
    	}
    	ImageIcon icono = new ImageIcon(url);
		if (rutaImagen != null && rutaImagen.contains("pared")) {
			System.out.println("DEBUG IMAGEN: Imagen cargada - ancho=" + icono.getIconWidth() + ", alto=" + icono.getIconHeight() + ", ruta=" + rutaImagen);
			if (icono.getIconWidth() == 0 || icono.getIconHeight() == 0) {
				System.err.println("ERROR: La imagen tiene dimensiones 0x0 - " + rutaImagen);
			}
		}
		setIcon(icono);
	}
	
	public void notificarFinColision(Entidad e) {
		// TO-DO
	}

	public void actualizarPosicionTamaño(){
		int x = AdaptadorPosicionPixel.transformar_x(entidadObservada.getPosX());
		int y = AdaptadorPosicionPixel.transformar_y(entidadObservada.getPosY());
		
		// Debug temporal para paredes
		if (entidadObservada.getSkin().getRutaImagenActual() != null && 
			entidadObservada.getSkin().getRutaImagenActual().contains("pared")) {
			System.out.println("DEBUG POSICION: Pared en mundo x=" + entidadObservada.getPosX() + 
							 ", pantalla x=" + x + ", y pantalla=" + y);
		}
		
		if (this.getIcon() != null) {
			int ancho = this.getIcon().getIconWidth();
			int alto = this.getIcon().getIconHeight();
			setBounds(x, y, ancho, alto);
		}else {
			setBounds(x, y, 32, 32);
		}
	}
}
