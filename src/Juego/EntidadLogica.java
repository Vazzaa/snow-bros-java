package Juego;

import Fabricas.Skin;
import Grafica.ObserverGrafico;

public interface EntidadLogica {

	public Skin getSkin();
	public int getPosX();
	public int getPosY();
	public ObserverGrafico getObserverGrafico();
	
}
