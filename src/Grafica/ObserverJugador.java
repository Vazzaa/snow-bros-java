package Grafica;
import Entidades.EntidadJugador;

public class ObserverJugador extends ObserverGrafico{
    //Atributos de instancia
    EntidadJugador entidadObservada;
    PanelPantallaNivel panelNivel;

    //Constructor
    public ObserverJugador (EntidadJugador ent, PanelPantallaNivel pn) {
        super(ent);
        panelNivel = pn;
    }

    //Comandos

    public void actualizar() {
        
    }
}