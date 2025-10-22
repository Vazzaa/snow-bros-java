package Grafica;

import javax.swing.JButton;

public class PanelPantallaGameOver extends PanelVista{

    //Atributos
    protected JButton botonVolverMenu;

    //Constructor
    public PanelPantallaGameOver(ControladorVistas c,JButton botonvolvermenu){
        super(c);
        botonVolverMenu=botonvolvermenu;
    }
}