package Grafica;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;



public class PanelPantallaElegirDominio extends PanelVista{
    
    private JButton botonDominio1;
    private JButton botonDominio2;
    private JLabel imagenFondo;

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

    protected void registrarOyenteBotonDominio1() {
        botonDominio1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaElegirModoJuego();
            }
        });
    }

    protected void registrarOyenteBotonDominio2() {
        botonDominio2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaElegirModoJuego();
            }
        });
    }

    protected void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void decorarBotonDominio1() {
		transparentarBoton(botonDominio1);
		botonDominio1.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 50 ,ConstantesVistas.PANEL_ALTO - 150, 200 , 50);
	}
	
	protected void decorarBotonDominio2 () {
		transparentarBoton(botonDominio2);
		botonDominio2.setBounds((ConstantesVistas.PANEL_ANCHO / 2) - 150 ,ConstantesVistas.PANEL_ALTO - 150, 260 , 50);
	}
}

