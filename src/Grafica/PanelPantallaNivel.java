package Grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;

public class PanelPantallaNivel extends PanelVista{
    private static final long serialVersionUID = 1L;
    private JPanel panelNivel;
    private JLabel imagenFondo;

    public PanelPantallaNivel(ControladorVistas c){
        controlaVistas = c;
        iniciarComponentes();
    }
    public void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(new BorderLayout());
        setDoubleBuffered(true);
        agregarPanelNivelconImagenFondo();

    }
     public Observer incorporarEntidad(EntidadLogica entidadLogic){
        ObserverGrafico observer_grafico = new ObserverGrafico(entidadLogic);
		imagenFondo.add(observer_grafico);	
		return observer_grafico;
	}

    public Observer incorporarEntidadJugador(EntidadJugador entidad_jugador) {
		ObserverJugador observer_jugador = new ObserverJugador(entidad_jugador, this);
		imagenFondo.add(observer_jugador);
		actualizar_info_jugador(entidad_jugador);
		return observer_jugador;
	}

    public Observer incorporarSilueta(EntidadLogica entidad_logica) {
        ObserverGrafico observer_silueta = new ObserverGrafico(entidad_logica);
        imagenFondo.setIcon(new ImageIcon(getClass().getClassLoader().getResource(entidad_logica.getSkin().getRutaImagenActual())));
        imagenFondo.setBounds(0,0, imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight());
        panelNivel.setPreferredSize(new Dimension(imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight()));
        return observer_silueta;
    }

    	protected void actualizar_info_jugador(EntidadJugador jugador) {
		actualizar_labels_informacion(jugador);
	}
    
    protected void actualizar_labels_informacion(EntidadJugador jugador) {
        //TODO: agregar label para la informacion del jugador
	}

    protected void agregarPanelNivelconImagenFondo() {
        imagenFondo = new JLabel();
        java.net.URL url = this.getClass().getResource("/Imagenes/Background/FondoNivel.png");
        if (url == null) System.err.println("No se encontró FondoNivel.png en classpath");
        ImageIcon iconoImagen = new ImageIcon(url);
        imagenFondo.setIcon(iconoImagen);
        imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        imagenFondo.setLayout(null);
        panelNivel = new JPanel();
        panelNivel.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        panelNivel.setLayout(null);
        panelNivel.add(imagenFondo);
        this.add(panelNivel);
    }

    
}

