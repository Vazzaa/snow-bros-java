package Grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ResourceBundle.Control;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.EntidadLogica;

public class PanelPantallaNivel extends PanelVista{
    private static final long serialVersionUID = 1L;
    private JPanel panelNivel;
    private JLabel imagenFondo;

    public PanelPantallaNivel(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }
    public void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(new BorderLayout());
        setDoubleBuffered(true);
        agregarPanelNivelconImagenFondo();

    }
     public Observer incorporarEntidad(EntidadLogica e){
        ObserverGrafico observer_grafico = new ObserverGrafico(e);
		imagenFondo.add(observer_grafico);	
		return observer_grafico;
	}

    public Observer incorporarEntidadJugador(EntidadJugador entidad_jugador) {
		ObserverJugador observer_jugador = new ObserverJugador(this, entidad_jugador);
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
        java.net.URL url = this.getClass().getResource("/Imagenes/Fondo1.png");
        if (url == null) {
            System.err.println("No se encontró pantalla-inicial.png en classpath: " + url);
        }
        ImageIcon iconoImagen = new ImageIcon(url);
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        imagenFondo.setLayout(null);
        panelNivel = new JPanel();
        panelNivel.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        panelNivel.setLayout(null);
        panelNivel.add(imagenFondo);
        this.add(panelNivel);
    }

    
}

