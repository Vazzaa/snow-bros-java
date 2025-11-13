package Grafica;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Sonidos.GestorSonidos;



public class PanelPantallaElegirDominio extends PanelVista{
    
    protected JButton botonParaAtr;
    private JButton botonDominio1;
    private JButton botonDominio2;
    private JLabel imagenFondo;
    private JTextField campoNombre;

    public PanelPantallaElegirDominio(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }

    public void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonDominio1();
        agregarBotonDominio2();
        agregarBotonParaAtr();
        agregarCampoNombre();
    }
    
    protected void agregarImagenFondo() {
        imagenFondo = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/pantalla-elegirdominio.png"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
	    imagenFondo.setIcon(iconoImagenEscalada);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
		add(imagenFondo);
	}

    protected void agregarBotonDominio1(){
        botonDominio1 = new JButton();
        decorarBotonDominio1();
        registrarOyenteBotonDominio1();
        add(botonDominio1);
    }

    protected void agregarBotonDominio2(){
        botonDominio2 = new JButton();
        decorarBotonDominio2();
        registrarOyenteBotonDominio2();
        add(botonDominio2);
    }

    protected void agregarBotonParaAtr(){
        botonParaAtr = new JButton();
        decorarBotonParaAtr();
        registrarOyenteBotonParaAtr();
        add(botonParaAtr);
    }

    protected void agregarCampoNombre(){
        campoNombre = new JTextField();
        campoNombre.setBounds(273, 180, 220, 40);
        transparentarCampoTexto(campoNombre);
        add(campoNombre);
    }

    protected void registrarOyenteBotonDominio1() {
        botonDominio1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoNombre.getText().isEmpty()) {
                    campoNombre.setText("Jugador");
                }
                agregarNombre();
                System.out.println("Nombre jugador: " + campoNombre.getText());
                GestorSonidos.getInstancia().reproducirEfecto("press_start");
                controladorVistas.getControladorJuego().activarDominio1();
                controladorVistas.getControladorJuego().iniciar();
            }
        });
    }

    protected void registrarOyenteBotonDominio2() {
        botonDominio2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoNombre.getText().isEmpty()) {
                    campoNombre.setText("Jugador");
                }
                agregarNombre();
                System.out.println("Nombre jugador: " + campoNombre.getText());
                GestorSonidos.getInstancia().reproducirEfecto("press_start");
                controladorVistas.getControladorJuego().activarDominio2();
                controladorVistas.getControladorJuego().iniciar();
            }
        });
    }

    protected void registrarOyenteBotonParaAtr(){
        botonParaAtr.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarPantallaPrincipal();
            }
        });
    }

    protected void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void transparentarCampoTexto(JTextField campo) {
        campo.setBorder(null);
        Font fuenteEstandar = new Font("Monospaced", Font.BOLD, 24);
        campoNombre.setFont(fuenteEstandar);
        campoNombre.setForeground(new Color(255, 0, 0)); 
        campoNombre.setCaretColor(new Color(255, 0, 0)); 
    }

    protected void decorarBotonDominio1() {
		transparentarBoton(botonDominio1);
		botonDominio1.setBounds(100 ,ConstantesVistas.PANEL_ALTO - 190, 200 , 50);
	}
	
	protected void decorarBotonDominio2 () {
		transparentarBoton(botonDominio2);
		botonDominio2.setBounds(500 ,ConstantesVistas.PANEL_ALTO - 190, 200 , 50);
	}

    protected void decorarBotonParaAtr(){
        transparentarBoton(botonParaAtr);
        botonParaAtr.setBounds(220, 500, 350, 50);
    }

    protected void agregarNombre() {
        String nombreJugador = campoNombre.getText();
        controladorVistas.getControladorJuego().setNombreJugador(nombreJugador);
    }
}

