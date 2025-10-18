package Grafica;

import javax.swing.JButton;

public class PanelPantallaPrincipal extends PanelVista{
    
    //Atributos
    protected JButton botonEmpezarJue;
    protected JButton botonRank;

    //Constructor
    public PanelPantallaPrincipal(ControladorVistas c,JButton botonranking, JButton botonempezarjuego){
        controlaVistas=c;
        botonEmpezarJue=botonempezarjuego;
        botonRank=botonranking;
    }
}
