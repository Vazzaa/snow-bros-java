package Grafica;

import Juego.EntidadJugador;

public class ObserverJugador extends ObserverGrafico{

	private static final long serialVersionUID = 7017967195998406908L;
	private PanelPantallaNivel panelPantallaNivel;
	private EntidadJugador jugador_observado;
	
	public ObserverJugador(PanelPantallaNivel panelPantallaNivel, EntidadJugador jugador_observado) {
		super(jugador_observado);
		this.panelPantallaNivel = panelPantallaNivel;
		this.jugador_observado = jugador_observado;
		actualizar();
	}
	
	public void actualizar() {
		super.actualizar();
		if (panelPantallaNivel != null){
		panelPantallaNivel.actualizar_info_jugador(jugador_observado);
		}
	}
}
