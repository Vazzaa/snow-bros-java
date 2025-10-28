package Grafica;

import Juego.EntidadJugador;

public class ObserverJugador extends ObserverGrafico{

	private static final long serialVersionUID = 7017967195998406908L;
	private PanelPantallaNivel panelPantallaNivel;
	private EntidadJugador jugadorObservado;
	
	public ObserverJugador(PanelPantallaNivel panelPantallaNivel, EntidadJugador jugadorObservado) {
		super(jugadorObservado);
		this.panelPantallaNivel = panelPantallaNivel;
		this.jugadorObservado = jugadorObservado;
		actualizar();
	}
	
	public void actualizar() {
		super.actualizar();
		if (panelPantallaNivel != null){
			panelPantallaNivel.actualizarInfoJugador(jugadorObservado);
		}
	}
}
