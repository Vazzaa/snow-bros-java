package Grafica;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Sonidos.GestorSonidos;



public class PanelPantallaPrincipal extends PanelVista{
    
    private JButton botonIniciar;
    private JButton botonRanking;
    private JLabel imagenFondo;

    public PanelPantallaPrincipal(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }

    public void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonIniciar();
        agregarBotonRanking();
    }
    
    protected void agregarImagenFondo() {
		imagenFondo = new JLabel();
		java.net.URL url = this.getClass().getResource("/Imagenes/pantalla-inicial.png");
        if (url == null) System.err.println("No se encontró pantalla-inicial.png en classpath");
        ImageIcon iconoImagen = new ImageIcon(url);
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        imagenFondo.setLayout(null);
        add(imagenFondo);
	}

    protected void agregarBotonIniciar(){
        botonIniciar = new JButton();
        decorarBotonInicio();
        registrarOyenteBotonInicio();
        add(botonIniciar);
    }

    protected void agregarBotonRanking(){
        botonRanking = new JButton();
        decorarBotonRanking();
        registrarOyenteBotonRanking();
        add(botonRanking);
    }

    protected void registrarOyenteBotonInicio() {
        botonIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarPantallaElegirModoJuego();
            }
        });
    }

    protected void registrarOyenteBotonRanking() {
        botonRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarPantallaRanking();
            }
        });
    }

    protected void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void decorarBotonInicio() {
		transparentarBoton(botonIniciar);
		botonIniciar.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 130 ,ConstantesVistas.PANEL_ALTO - 200, 250 , 50);
	}
	
	protected void decorarBotonRanking() {
		transparentarBoton(botonRanking);
		botonRanking.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 130 ,ConstantesVistas.PANEL_ALTO - 100, 250 , 50);
	}
}

