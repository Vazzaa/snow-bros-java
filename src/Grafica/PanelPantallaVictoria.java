package Grafica;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Sonidos.GestorSonidos;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPantallaVictoria extends PanelVista{

    //Atributos
    protected JLabel labelGifAnimado;
    protected JButton botonVolverMenu;
    protected JLabel imagenFondo;

    //Constructor
    public PanelPantallaVictoria(ControladorVistas c){
        super(c);
        iniciarComponentes();
    }

    protected void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        agregarGifVictoria();
        agregarBotonVolverMenu();
    }

    protected void agregarImagenFondo(){
        imagenFondo = new JLabel();
        
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/PantallaVictoria.png"));
        
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        add(imagenFondo); 
    }

    protected void agregarGifVictoria() {
        labelGifAnimado = new JLabel();
        
        String rutaGif = "/Imagenes/MogheraBailando.gif"; 
        java.net.URL urlGif = this.getClass().getResource(rutaGif);

        if (urlGif != null) {
            ImageIcon iconoGif = new ImageIcon(urlGif);
            
            int gifAncho = iconoGif.getIconWidth();
            int gifAlto = iconoGif.getIconHeight();

            int posX = (ConstantesVistas.PANEL_ANCHO - gifAncho) / 2;
            
            int posY = (ConstantesVistas.PANEL_ALTO - gifAlto) / 2;

            labelGifAnimado.setIcon(iconoGif);
            labelGifAnimado.setBounds(posX, posY, gifAncho, gifAlto);
            System.out.println("Has ganado. ");

        } else {
            System.err.println("Error: No se pudo encontrar el GIF de victoria en: " + rutaGif);
            labelGifAnimado.setBounds(0, (ConstantesVistas.PANEL_ALTO / 2) - 25, ConstantesVistas.PANEL_ANCHO, 50);
            labelGifAnimado.setHorizontalAlignment(JLabel.CENTER);
        }
        
        labelGifAnimado.setOpaque(true);
        add(labelGifAnimado);
    }



    protected void agregarBotonVolverMenu(){
        botonVolverMenu = new JButton();
        decorarBotonVolverMenu();
        registrarOyenteBotonVolverMenu();
        add(botonVolverMenu);
    }

    protected void decorarBotonVolverMenu(){
        transparentarBoton(botonVolverMenu);
        botonVolverMenu.setBounds(200,490,390,50);
    }

    protected void transparentarBoton(JButton boton){
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void registrarOyenteBotonVolverMenu(){
        botonVolverMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GestorSonidos.getInstancia().detenerMusica();
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarPantallaPrincipal();
                GestorSonidos.getInstancia().reproducirMusica("src/Sonidos/Background/02_Yukidama-Ondo_(Stage1_3).wav");
            }
        });
    }
}
