package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelPantallaPrincipal extends PanelVista{
    
    private JButton botonIniciar;
    private JButton botonRanking;
    private JLabel imagenFondo;

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
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("images.jpg"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
	    imagenFondo.setIcon(iconoImagenEscalada);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
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

