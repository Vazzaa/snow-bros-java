package Grafica;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPantallaGameOver extends PanelVista{

    //Atributos
    protected JButton botonVolverMenu;
    protected JLabel imagenFondo;

    //Constructor
    public PanelPantallaGameOver(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }

    protected void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarBotonVolverMenu();
    }

    protected void agregarImagenFondo(){
       imagenFondo = new JLabel();
       ImageIcon iconoImagen= new ImageIcon(this.getClass().getResource("/Imagenes/PantallaGameOver.png"));
       Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
       Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
       imagenFondo.setIcon(iconoImagenEscalada);
       imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
       add(imagenFondo);
    }

    protected void agregarBotonVolverMenu(){
        botonVolverMenu = new JButton();
        decorarBotonVolverMenu();
        registrarOyenteBotonVolverMenu();
        add(botonVolverMenu);
    }

    protected void decorarBotonVolverMenu(){
        transparentarBoton(botonVolverMenu);
        botonVolverMenu.setBounds(400, 520, 260, 50);
    }

    protected void transparentarBoton(JButton boton){
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void registrarOyenteBotonVolverMenu(){
        botonVolverMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controladorVistas.accionarPantallaPrincipal();
            }
        });
    }
}