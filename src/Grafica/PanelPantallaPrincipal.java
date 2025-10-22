package Grafica;

import java.awt.*;
import java.awt.event.ActionListener;
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
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/pantalla-inicial.png"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
	    imagenFondo.setIcon(iconoImagenEscalada);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
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
                controladorVistas.accionarInicioJuego();
            }
        });
    }

    protected void registrarOyenteBotonRanking() {
        botonRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
		botonIniciar.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 100 ,ConstantesVistas.PANEL_ALTO - 150, 200 , 50);
	}
	
	protected void decorarBotonRanking() {
		transparentarBoton(botonRanking);
		botonRanking.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 130 ,ConstantesVistas.PANEL_ALTO - 90, 260 , 50);
	}
}

