package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Juego.ColisionManagerEntidades;

public interface EstadoMovimientoEnemigo {
    ColisionManagerEntidades colisionManager = new ColisionManagerEntidades();
    public void moverse(Enemigo enemigo, int velocidad);
    public EstadoMovimientoEnemigo getEstadoOpuesto();
    public boolean permiteMovimiento();
    public EstadoMovimientoEnemigo getEstadoAnterior();
    public boolean permiteSalto();
}
