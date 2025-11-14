package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Juego.EntidadJugador;
import Juego.EntidadLogica;

public class PanelPantallaNivel extends PanelVista{
    private static final long serialVersionUID = 1L;
    private JPanel panelNivel;
    private JPanel panelInformacion;
    private JLabel imagenFondo;
    private JLabel labelNivel;
    private JLabel labelTextNivel;
    private JLabel labelVida;
    private JLabel labelPuntaje;
    private JLabel labelTextPuntaje;
    private JLabel labelTextVida;
    private JLabel labelTiempo;
    private JLabel labelTextTiempo;

    public PanelPantallaNivel(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }

    public void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(new BorderLayout());
        setDoubleBuffered(true);
        agregarPanelInformacion();
        agregarPanelNivelconImagenFondo();

    }

    public Observer incorporarEntidad(EntidadLogica e){
        ObserverGrafico observer_grafico = new ObserverGrafico(e);
		imagenFondo.add(observer_grafico);	
		return observer_grafico;
	}

    public void removerEntidad(ObserverGrafico og){
        imagenFondo.remove(og);
    }

    public Observer incorporarEntidadJugador(EntidadJugador entidad_jugador) {
		ObserverJugador observerJugador = new ObserverJugador(this, entidad_jugador);
		imagenFondo.add(observerJugador);
		actualizarInfoJugador(entidad_jugador);
		return observerJugador;
	}

    public Observer incorporarSilueta(EntidadLogica entidad_logica) {
        ObserverGrafico observerSilueta = new ObserverGrafico(entidad_logica);
        imagenFondo.setIcon(new ImageIcon(getClass().getClassLoader().getResource(entidad_logica.getSkin().getRutaImagenActual())));
        imagenFondo.setBounds(0,0, imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight());
        panelNivel.setPreferredSize(new Dimension(imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight()));
        return observerSilueta;
    }

    protected void actualizarInfoJugador(EntidadJugador jugador) {
		actualizarLabelsInformacion(jugador);
	}
    
    protected void actualizarLabelsInformacion(EntidadJugador jugador) {
        labelTextPuntaje.setText(String.valueOf(jugador.getPuntaje()));
        labelTextVida.setText(String.valueOf(jugador.getVida()));
	}

    public void actualizarTiempo(String tiempo) {
        labelTextTiempo.setText(tiempo);
    }

    public void actualizarNivel(int numeroNivel) {
        labelTextNivel.setText(String.valueOf(numeroNivel));
    }  

    public void reiniciarPanel() {
        labelTextPuntaje.setText("0");
        labelTextVida.setText("3");
        labelTextTiempo.setText("--:--");
        labelTextNivel.setText("1");
    }

    public void setImagenDeFondo(String rutaImagen) {
        try {
            java.net.URL url = this.getClass().getResource(rutaImagen);
            if (url == null) {
                System.err.println("Error: No se pudo encontrar la imagen de fondo en: " + rutaImagen);
                return;
            }
            ImageIcon iconoImagen = new ImageIcon(url);
            Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
            Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
            imagenFondo.setIcon(iconoImagenEscalada);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen de fondo: " + rutaImagen);
            e.printStackTrace();
        }
    }
    
    protected void agregarPanelNivelconImagenFondo() {
        imagenFondo = new JLabel();
        java.net.URL url = this.getClass().getResource("/Imagenes/Background/Fondo1.png");
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

    protected void agregarPanelInformacion() {
        panelInformacion = new JPanel();
        panelInformacion.setBackground(Color.BLACK);
        labelPuntaje = new JLabel("Puntaje: ");
        labelPuntaje.setForeground(Color.WHITE);

        labelTextPuntaje = new JLabel("0");
        labelTextPuntaje.setForeground(Color.WHITE);

        labelVida = new JLabel("Vida: ");
        labelVida.setForeground(Color.WHITE);

        labelTextVida = new JLabel("3");
        labelTextVida.setForeground(Color.WHITE);

        labelTiempo = new JLabel("Tiempo:");
        labelTiempo.setForeground(Color.WHITE);

        labelTextTiempo = new JLabel("--:--");
        labelTextTiempo.setForeground(Color.WHITE);

        labelNivel = new JLabel("Nivel:");
        labelNivel.setForeground(Color.WHITE);

        labelTextNivel = new JLabel("1");
        labelTextNivel.setForeground(Color.WHITE);

        Font fuenteEstandar = new Font("Monospaced", Font.BOLD, 24);

        labelPuntaje.setFont(fuenteEstandar);
        labelPuntaje.setForeground(new Color(255, 0, 0)); 

        labelTextPuntaje.setFont(fuenteEstandar);
        labelTextPuntaje.setForeground(new Color(255, 0, 0));

        labelVida.setFont(fuenteEstandar);
        labelVida.setForeground(new Color(255, 0, 0));

        labelTextVida.setFont(fuenteEstandar);
        labelTextVida.setForeground(new Color(255, 0, 0));

        labelTiempo.setFont(fuenteEstandar);
        labelTiempo.setForeground(new Color(255, 0, 0));
        
        labelTextTiempo.setFont(fuenteEstandar);
        labelTextTiempo.setForeground(new Color(255, 0, 0));

        labelNivel.setFont(fuenteEstandar);
        labelNivel.setForeground(new Color(255, 0, 0));
    
        labelTextNivel.setFont(fuenteEstandar);
        labelTextNivel.setForeground(new Color(255, 0, 0));
        
        panelInformacion.add(labelPuntaje);
        panelInformacion.add(labelTextPuntaje);
        panelInformacion.add(Box.createHorizontalStrut(100));
        panelInformacion.add(labelTiempo);
        panelInformacion.add(labelTextTiempo);
        panelInformacion.add(Box.createHorizontalStrut(100)); 
        panelInformacion.add(labelVida);
        panelInformacion.add(labelTextVida);
        panelInformacion.add(Box.createHorizontalStrut(100));
        panelInformacion.add(labelNivel);
        panelInformacion.add(labelTextNivel);


        this.add(panelInformacion, BorderLayout.NORTH);
    }
    public void limpiarPanel() {
        imagenFondo.removeAll();
        imagenFondo.revalidate();
        imagenFondo.repaint();
    }
}
