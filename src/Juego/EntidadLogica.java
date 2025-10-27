package Juego;

import Fabricas.Skin;
import java.util.List;
import Grafica.ObserverGrafico;

public interface EntidadLogica {

	public Skin getSkin();
	public int getPosX();
	public int getPosY();
	public List<ObserverGrafico> getObserversGrafico();
	
}
