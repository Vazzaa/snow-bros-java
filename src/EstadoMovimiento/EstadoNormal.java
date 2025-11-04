package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;

public class EstadoNormal implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        dr.getSkin().setEstadoActual(5);
    }

    @Override
    public void derretirse(DemonioRojo dr) {
        // No hace nada porque ya está en estado normal
    }
    
}
