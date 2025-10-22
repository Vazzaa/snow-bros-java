package Grafica;
import javax.print.DocFlavor.URL;
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
		System.out.println("Intentando cargar imagen desde: " + rutaImagen);
    	java.net.URL url = getClass().getClassLoader().getResource(rutaImagen);
    	if (url == null) {
        	System.err.println("No se pudo encontrar la imagen en: " + rutaImagen);
        	return;
    	}
    	ImageIcon icono = new ImageIcon(url);
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
