package Grafica;

import javax.swing.JButton;

public class PanelPantallaElegirModoDeJuego extends PanelVista{
    
    //Atributos
    protected JButton botonJugarClas;
    protected JButton botonJugarContra;
    protected JButton botonJugarSuper;

    //Constructor
    public PanelPantallaElegirModoDeJuego(ControladorVistas c,JButton botonsupervivencia,JButton botonclasico, JButton botoncontra){
        super(c);
        botonJugarSuper=botonsupervivencia;
        botonJugarContra=botoncontra;
        botonJugarClas=botonclasico;
    }
}
