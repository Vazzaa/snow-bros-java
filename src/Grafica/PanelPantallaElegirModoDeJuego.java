package Grafica;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Sonidos.GestorSonidos;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPantallaElegirModoDeJuego extends PanelVista{
    
    //Atributos
    protected JButton botonJugarClasico;
    protected JButton botonJugarContrareloj;
    protected JButton botonJugarSupervivencia;
    protected JLabel imagenFondo;

    //Constructor
    public PanelPantallaElegirModoDeJuego(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }

   protected void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonClasico();
        agregarBotonContrareloj();
        agregarBotonSupervivencia();
    }

    protected void agregarImagenFondo(){
        imagenFondo = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/pantalla-elegirmodo.png"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
	    imagenFondo.setIcon(iconoImagenEscalada);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		add(imagenFondo);
    }
    
    protected void agregarBotonClasico(){
        botonJugarClasico = new JButton();
        decorarBotonClasico();
        registrarOyenteBotonClasico();
        add(botonJugarClasico);
    }

    protected void agregarBotonContrareloj(){
        botonJugarContrareloj = new JButton();
        decorarBotonContrareloj();
        registrarOyenteBotonContrareloj();
        add(botonJugarContrareloj);
    }

    protected void agregarBotonSupervivencia(){
        botonJugarSupervivencia = new JButton();
        decorarBotonSupervivencia();
        registrarOyenteBotonSupervivencia();
        add(botonJugarSupervivencia);
    }


    protected void registrarOyenteBotonClasico() {
        botonJugarClasico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarInicioJuego(ConstantesModoDeJuego.CLASICO);
            }
        });
    }

    protected void registrarOyenteBotonContrareloj() {
        botonJugarContrareloj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarInicioJuego(ConstantesModoDeJuego.CONTRARELOJ);
            }
        });
    }

    protected void registrarOyenteBotonSupervivencia() {
        botonJugarSupervivencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarInicioJuego(ConstantesModoDeJuego.SUPERVIVENCIA);
            }
        });
    }

    protected void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void decorarBotonClasico() {
		transparentarBoton(botonJugarClasico);
		botonJugarClasico.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100 ,220, 200 , 50);
	}
	
	protected void decorarBotonContrareloj() {
		transparentarBoton(botonJugarContrareloj);
		botonJugarContrareloj.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 150 ,450, 300 , 50);
	}
    
    protected void decorarBotonSupervivencia() {
		transparentarBoton(botonJugarSupervivencia);
		botonJugarSupervivencia.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 160 ,330, 350 , 50);
	}
}
