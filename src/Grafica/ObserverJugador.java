package Grafica;
import Entidades.EntidadJugador;

public class ObserverJugador extends ObserverGrafico{
    //Atributos de instancia
    EntidadJugador entidadObservada;
    //PanelPantallaNivel panelNivel;
    PanelPantallaNivel panelNivel;

    //Constructor
    public ObserverJugador (EntidadJugador ent, PanelPantallaNivel panelPantalla) {
        super(ent);
        panelNivel = panelPantalla;
    }

    //Comandos

    public void actualizar() {
        
    }
}