package Grafica;

import javax.swing.JPanel;

public abstract class PanelVista extends JPanel {
    protected ControladorVistas controladorVistas;

    protected PanelVista (ControladorVistas controladorVistas){
        this.controladorVistas = controladorVistas;
    }
}