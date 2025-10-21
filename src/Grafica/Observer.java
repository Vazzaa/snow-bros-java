package Grafica;

import Entidades.Entidad;
import javax.swing.JLabel;

public interface Observer  {

	public void actualizar();
	public void notificarFinColision(Entidad e);
	
}
