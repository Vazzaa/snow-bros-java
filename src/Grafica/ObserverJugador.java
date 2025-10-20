package Grafica;
import Entidades.EntidadJugador;

public class ObserverJugador {
    //Atributos de instancia
    EntidadJugador entidadObservada;
    PanelPantallaNivel panelNivel;

    //Constructor
    public ObserverJugador (EntidadJugador ent, PanelPantallaNivel pn) {
        entidadObservada = ent;
        panelNivel = pn;
    }

    //Comandos

    public void actualizar() {
        
    }
}