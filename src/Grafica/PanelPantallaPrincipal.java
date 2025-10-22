package Grafica;

import javax.print.DocFlavor.URL;
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
		java.net.URL url = this.getClass().getResource("/Imagenes/Background/Fondo1.png");
        if (url == null) System.err.println("No se encontró Fondo1.png en classpath");
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

