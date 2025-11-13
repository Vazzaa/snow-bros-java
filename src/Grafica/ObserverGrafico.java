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
			System.out.println("DEBUG IMAGEN: Imagen cargada - ancho=" + getIcon().getIconWidth() + ", alto=" + getIcon().getIconHeight() + ", ruta=" + rutaImagen);
			if (getIcon().getIconWidth() == 0 || getIcon().getIconHeight() == 0) {
				System.err.println("ERROR: La imagen tiene dimensiones 0x0 - " + rutaImagen);
			}
		}
	}
	
	public void notificarFinColision(Entidad e) {
		// TO-DO
	}

	// --- MÉTODO CORREGIDO ---
	public void actualizarPosicionTamaño(){
		// 1. Obtenemos las coordenadas lógicas
		int x_logico = entidadObservada.getPosX();
		int y_logico = entidadObservada.getPosY();
		
		// 2. Las transformamos a coordenadas de pantalla
		int x_pantalla = AdaptadorPosicionPixel.transformar_x(x_logico);
		int y_pantalla = AdaptadorPosicionPixel.transformar_y(y_logico);
		
		if (this.getIcon() != null) {
			// 3. Obtenemos el tamaño (ya escalado por actualizarImagen())
			int ancho = this.getIcon().getIconWidth();
			int alto = this.getIcon().getIconHeight();

			// --- INICIO DE LA CORRECCIÓN ---
			
			// 4. Verificamos si es una entidad con renderizado especial (Moghera)
			//    consultando si definió un ancho de renderizado.
			if (entidadObservada.getRenderAncho() > 0) {
				// 5. Si es Moghera, asumimos que (x_pantalla, y_pantalla) es el CENTRO.
				//    Debemos calcular el nuevo TOP-LEFT restando la mitad del tamaño.
				x_pantalla = x_pantalla - (ancho / 2);
				y_pantalla = y_pantalla - (alto / 2);
			}
			// 6. Si es una entidad normal, (x_pantalla, y_pantalla) ya es el TOP-LEFT,
			//    así que no hacemos nada.
			
			// --- FIN DE LA CORRECCIÓN ---
			
			// 7. Aplicamos el setBounds con las coordenadas correctas.
			setBounds(x_pantalla, y_pantalla, ancho, alto);
			
		}else {
			// Fallback (asumiendo top-left)
			setBounds(x_pantalla, y_pantalla, 32, 32);
		}
	}

}