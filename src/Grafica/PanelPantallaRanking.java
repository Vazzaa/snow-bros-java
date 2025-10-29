package Grafica;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entidades.Jugador.Jugador;
import Juego.Ranking;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelPantallaRanking extends PanelVista{
    
    // Atributos
    protected JButton botonParaAtr;
    protected JPanel panelRankingClasico;
    protected JPanel panelRankingContrarreloj;
    protected JPanel panelRankingSupervivencia;
    protected Ranking rankingClasico;
    protected Ranking rankingContrarreloj;
    protected Ranking rankingSupervivencia;

    // Constructor
    public PanelPantallaRanking(ControladorVistas c){
        super(c);
        iniciarComponentes();
        rankingClasico = new Ranking();
        rankingContrarreloj = new Ranking();
        rankingSupervivencia = new Ranking();
    }

    protected void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        agregarImagenFondo();
        crearPanelesRanking();
        MostrarPanelesRanking();
        agregarPanelesRanking();
        agregarBotonParaAtr();
    }

    protected void agregarImagenFondo(){
        JLabel imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/PantallaRanking.png"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalada);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        add(imagenFondo);
    }

    protected void crearPanelesRanking(){
        panelRankingClasico = new JPanel();
        panelRankingClasico.setBounds(0, 0, 267, 540);
        panelRankingContrarreloj = new JPanel();
        panelRankingContrarreloj.setBounds(256, 0, 267, 540);
        panelRankingSupervivencia = new JPanel();
        panelRankingSupervivencia.setBounds(534, 0, 266, 540);
    }

    protected void MostrarPanelesRanking(){
        // TODO: Agregar los paneles de ranking y mostrarlos
    }

    public void agregarJugadoresRanking(int ModoDeJuego, Jugador j){
        switch (ModoDeJuego) {
            case 1:
                rankingClasico.agregarJugador(j);
                break;
            case 2:
                rankingContrarreloj.agregarJugador(j);
                break;
            case 3:
                rankingSupervivencia.agregarJugador(j);
                break;
        }
    }

    protected void agregarPanelesRanking(){
        add(panelRankingClasico);
        add(panelRankingContrarreloj);
        add(panelRankingSupervivencia);
    }   

    protected void agregarBotonParaAtr(){
        botonParaAtr = new JButton();
        decorarBotonParaAtr();
        registrarOyenteBotonParaAtr();
        add(botonParaAtr);
    }

    protected void decorarBotonParaAtr(){
        transparentarBoton(botonParaAtr);
        botonParaAtr.setBounds(220, 500, 350, 50);
    }

    protected void transparentarBoton(JButton boton){
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    protected void registrarOyenteBotonParaAtr(){
        botonParaAtr.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controladorVistas.accionarPantallaPrincipal();
            }
        });
    }
}
