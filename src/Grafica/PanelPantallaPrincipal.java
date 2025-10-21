package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Entidades.EntidadJugador;
import Entidades.EntidadLogica;

public class PanelPantallaPrincipal extends PanelVista{
    
    private JButton botonIniciar;
    private JButton botonRanking;
    private JLabel imagenFondo;
    private JPanel panelPrincipal;

    public PanelPantallaPrincipal(ControladorVistas c){
        controlaVistas = c;
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
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Background/Fondo1.png"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
	    imagenFondo.setIcon(iconoImagenEscalada);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        imagenFondo.setLayout(null);
		add(imagenFondo);
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
        panelPrincipal.setPreferredSize(new Dimension(imagenFondo.getIcon().getIconWidth(), imagenFondo.getIcon().getIconHeight()));
        return observer_silueta;
    }

    	protected void actualizar_info_jugador(EntidadJugador jugador) {
		actualizar_labels_informacion(jugador);
	}
    
    protected void actualizar_labels_informacion(EntidadJugador jugador) {
	}

    protected void agregarBotonIniciar(){
        botonIniciar = new JButton();
        registrarOyenteBotonInicio();
        add(botonIniciar);
    }

    protected void agregarBotonRanking(){
        botonRanking = new JButton();
        registrarOyenteBotonRanking();
        add(botonRanking);
    }

    protected void registrarOyenteBotonInicio() {
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlaVistas.accionarInicioJuego();
			}
		});
	}

    protected void registrarOyenteBotonRanking() {
		botonRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlaVistas.accionarPantallaRanking();
			}
		});
	}

}

