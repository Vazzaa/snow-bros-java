package Grafica;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Juego.Entidad;
import Juego.EntidadLogica;
import Juego.Hitbox;

public class ObserverGrafico extends JLabel implements Observer {
	
	private static final long serialVersionUID = 1L;
	protected EntidadLogica entidadObservada;
	
	public ObserverGrafico(EntidadLogica entidadLogic) {
		super();
		entidadObservada = entidadLogic;
		setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED, 1));
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
		// --- Lógica de X (sin cambios) ---
		int x_logico_izq = entidadObservada.getPosX();
		int x_screen_izq = AdaptadorPosicionPixel.transformar_x(x_logico_izq);
		
		
		// --- Nueva Lógica de Y (Centrado) ---

		// 1. Obtener el Hitbox y el Icono
		Hitbox hitbox = entidadObservada.getHitbox();
		Icon icono = this.getIcon();

		if (icono == null) {
			// Fallback si no hay imagen
			int y_screen_bottom = AdaptadorPosicionPixel.transformar_y(entidadObservada.getPosY());
			setBounds(x_screen_izq, y_screen_bottom - 32, 32, 32); // Asume 32x32
			return;
		}

		// 2. Obtener dimensiones
		int ancho_imagen = icono.getIconWidth();
		int alto_imagen = icono.getIconHeight();
		int alto_hitbox = hitbox.getAlto();

		// 3. Calcular coordenadas Y del Hitbox en la pantalla
		//    (posY es el borde inferior)
		int y_screen_bottom_hitbox = AdaptadorPosicionPixel.transformar_y(entidadObservada.getPosY());
		int y_screen_top_hitbox = y_screen_bottom_hitbox - alto_hitbox;
		
		// 4. Calcular el centro Y del Hitbox
		int y_screen_centro_hitbox = y_screen_top_hitbox + (alto_hitbox / 2);
		
		// 5. Calcular el nuevo borde superior de la IMAGEN para que su centro
		//    se alinee con el centro del Hitbox
		int y_screen_top_imagen = y_screen_centro_hitbox - (alto_imagen / 2);

		
		// --- Lógica de X (Centrado, opcional pero recomendado) ---
		int ancho_hitbox = hitbox.getAncho();
		int x_screen_centro_hitbox = x_screen_izq + (ancho_hitbox / 2);
		int x_screen_izq_imagen = x_screen_centro_hitbox - (ancho_imagen / 2);


		// 6. Aplicar setBounds con las coordenadas de la IMAGEN
		setBounds(x_screen_izq_imagen, y_screen_top_imagen, ancho_imagen, alto_imagen);


		// --- DEBUG ---
		if (entidadObservada.getSkin().getRutaImagenActual() != null && 
			entidadObservada.getSkin().getRutaImagenActual().contains("Kamakichi")) {
			System.out.println("KAMAKICHI DEBUG:");
			System.out.println("  Hitbox (screen): y_top=" + y_screen_top_hitbox + ", y_bottom=" + y_screen_bottom_hitbox);
			System.out.println("  Imagen (screen): y_top=" + y_screen_top_imagen + ", y_bottom=" + (y_screen_top_imagen + alto_imagen));
		}
	}
}
