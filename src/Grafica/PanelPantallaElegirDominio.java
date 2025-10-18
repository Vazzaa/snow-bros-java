package Grafica;

import javax.swing.JButton;

public class PanelPantallaElegirDominio extends PanelVista{

    //Atributos
    protected JButton botondom1;
    protected JButton botondom2;

    //Constructor
    public PanelPantallaElegirDominio(ControladorVistas c,JButton botondedom1, JButton botondedom2){
        controlaVistas=c;
        botondom1=botondedom1;
        botondom2=botondedom2;
    }
}
