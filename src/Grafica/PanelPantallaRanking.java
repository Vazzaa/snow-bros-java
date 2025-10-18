package Grafica;

import javax.swing.JButton;

public class PanelPantallaRanking extends PanelVista{
    
    //Atributos
    protected JButton botonParaAtr;

    //Constructor
    public PanelPantallaRanking(ControladorVistas c,JButton botonparaatras){
        controlaVistas=c;
        botonParaAtr=botonparaatras;
    }
}
