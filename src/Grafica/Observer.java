package Grafica;
import Entidades.Entidad;

public interface Observer {

	public void actualizar();
	public void notificarFinColision(Entidad e);
	
}
