package Grafica;
import Entidades.EntidadJugador;

public class ObserverJugador extends ObserverGrafico{
    //Atributos de instancia
    EntidadJugador entidadObservada;
    //PanelPantallaNivel panelNivel;
    PanelPantallaPrincipal panelPrincipal;

    //Constructor
    public ObserverJugador (EntidadJugador ent, PanelPantallaPrincipal pn) {
        super(ent);
        panelPrincipal = pn;
    }

    //Comandos

    public void actualizar() {
        
    }
}