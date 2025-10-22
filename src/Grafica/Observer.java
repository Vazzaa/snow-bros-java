package Grafica;

import Juego.Entidad;

public interface Observer  {

	public void actualizar();
	public void notificarFinColision(Entidad e);
	
}
