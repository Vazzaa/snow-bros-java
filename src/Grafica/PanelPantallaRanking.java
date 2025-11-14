package Grafica;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Entidades.Jugador.Jugador;
import Juego.Ranking;
import Sonidos.GestorSonidos;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PanelPantallaRanking extends PanelVista{
    
    // Atributos
    protected JButton botonParaAtr;
    protected JPanel panelRankingClasico;
    protected JPanel panelRankingContrarreloj;
    protected JPanel panelRankingSupervivencia;
    protected Ranking rankingClasico;
    protected Ranking rankingContrarreloj;
    protected Ranking rankingSupervivencia;

    private static final String RUTA_RANKING_CLASICO = "ranking_clasico.dat";
    private static final String RUTA_RANKING_CONTRARRELOJ = "ranking_contrarreloj.dat";
    private static final String RUTA_RANKING_SUPERVIVENCIA = "ranking_supervivencia.dat";

    // Constructor
    public PanelPantallaRanking(ControladorVistas c){
        super(c);
        rankingClasico = cargarRanking(RUTA_RANKING_CLASICO);
        rankingContrarreloj = cargarRanking(RUTA_RANKING_CONTRARRELOJ);
        rankingSupervivencia = cargarRanking(RUTA_RANKING_SUPERVIVENCIA);
        iniciarComponentes();
    }

    protected void iniciarComponentes(){
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        crearPanelesRanking();
        agregarBotonParaAtr();
        refrescarRankingsEnPantalla();
        agregarImagenFondo();
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
        panelRankingClasico.setLayout(new BoxLayout(panelRankingClasico, BoxLayout.Y_AXIS));
        panelRankingClasico.setBounds(267, 50, 267, 540);
        panelRankingClasico.setOpaque(false); 
        panelRankingClasico.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRankingContrarreloj = new JPanel();  
        panelRankingContrarreloj.setLayout(new BoxLayout(panelRankingContrarreloj, BoxLayout.Y_AXIS));
        panelRankingContrarreloj.setBounds(534, 50, 267, 540);
        panelRankingContrarreloj.setOpaque(false); 
        panelRankingContrarreloj.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRankingSupervivencia = new JPanel();
        panelRankingSupervivencia.setLayout(new BoxLayout(panelRankingSupervivencia, BoxLayout.Y_AXIS));
        panelRankingSupervivencia.setBounds(0, 50, 266, 540);
        panelRankingSupervivencia.setOpaque(false); 
        panelRankingSupervivencia.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        agregarPanelesRanking();
    }

    public void agregarJugadoresRanking(int ModoDeJuego, Jugador j) {
        if (j != null) {
            String nombre = j.getNombre();
            int puntaje = j.getPuntaje();

            switch (ModoDeJuego) {
                case 1:
                    rankingClasico.agregarJugador(nombre, puntaje);
                    guardarRanking(rankingClasico, RUTA_RANKING_CLASICO);
                    break;
                case 2:
                    rankingContrarreloj.agregarJugador(nombre, puntaje);
                    guardarRanking(rankingContrarreloj, RUTA_RANKING_CONTRARRELOJ);
                    break;
                case 3:
                    rankingSupervivencia.agregarJugador(nombre, puntaje);
                    guardarRanking(rankingSupervivencia, RUTA_RANKING_SUPERVIVENCIA);
                    break;
            }
            refrescarRankingsEnPantalla();
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
                GestorSonidos.getInstancia().reproducirEfecto("press_button");
                controladorVistas.accionarPantallaPrincipal();
            }
        });
    }

    public void refrescarRankingsEnPantalla() {
        panelRankingClasico.removeAll();
        panelRankingContrarreloj.removeAll();
        panelRankingSupervivencia.removeAll();
        
        mostrarRanking(panelRankingClasico, rankingClasico.getPuntajes());
        mostrarRanking(panelRankingContrarreloj, rankingContrarreloj.getPuntajes());
        mostrarRanking(panelRankingSupervivencia, rankingSupervivencia.getPuntajes());
        revalidate();
        repaint();
    }

    private void mostrarRanking(JPanel panel, Jugador[] puntajes) {
        if(puntajes != null){
            Font fuenteEstandar = new Font("Monospaced", Font.BOLD, 24);
            for (int i = 0; i < puntajes.length; i++) {
                Jugador j = puntajes[i];
                if (j != null) {
                String texto = (i + 1) + ". " + j.getNombre() + " - " + j.getPuntaje();
                JLabel jugadorLabel = new JLabel(texto);
                jugadorLabel.setForeground(Color.WHITE);
                jugadorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                jugadorLabel.setFont(fuenteEstandar);
                panel.add(jugadorLabel);
                }
            }
        }
    }

    private void guardarRanking(Ranking ranking, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(ranking);
        } catch (Exception e) {
            System.err.println("Error al guardar el ranking en " + rutaArchivo);
            e.printStackTrace();
        }
    }

    private Ranking cargarRanking(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
                Ranking ranking = (Ranking) ois.readObject();
                if (ranking != null) {
                    return ranking;
                }
            } catch (Exception e) {
                System.err.println("Error al cargar el ranking desde " + rutaArchivo + ". Se creará uno nuevo.");
                e.printStackTrace();
            }
        }
        return new Ranking();
    }

}
