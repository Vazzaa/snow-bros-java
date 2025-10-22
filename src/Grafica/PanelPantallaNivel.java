package Grafica;

import javax.swing.ImageIcon;

import Juego.EntidadJugador;
import Juego.EntidadLogica;

public class PanelPantallaNivel extends PanelVista{
    

    public PanelPantallaNivel(ControladorVistas c){
        controlaVistas=c;
    }

    // todo esto seria para panel nivel
    public Observer incorporarEntidad(EntidadLogica entidadLogic){
        ObserverGrafico observer_grafico = new ObserverGrafico(entidadLogic);
		imagenFondo.add(observer_grafico);	
		return observer_grafico;
	}

    public Observer incorporar_entidad_jugador(EntidadJugador entidad_jugador) {
		ObserverJugador observer_jugador = new ObserverJugador(entidad_jugador, null);
		imagenFondo.add(observer_jugador);
		actualizar_info_jugador(entidad_jugador);
		return observer_jugador;
	}

    public Observer incorporarSilueta(EntidadLogica entidad_logica) {
        ObserverGrafico observer_silueta = new ObserverGrafico(entidad_logica);
        imagenFondo.setIcon(new ImageIcon(getClass().getClassLoader().getResource(entidad_logica.getSkin().getRutaImagenActual())));
        imagenFondo.setBounds(0,0, imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight());
        this.setPreferredSize(new Dimension(imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight()));
        return observer_silueta;
    }

    	protected void actualizar_info_jugador(EntidadJugador jugador) {
		actualizar_labels_informacion(jugador);
	}
    
    protected void actualizar_labels_informacion(EntidadJugador jugador) {
	}

}
