package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;

public interface EstadoEnemigo {
    public void recibirDisparo(DemonioRojo dr);

    public void derretirse(DemonioRojo dr);
}
