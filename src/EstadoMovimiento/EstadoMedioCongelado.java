package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;

public class EstadoMedioCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        dr.getSkin().setEstadoActual(7);
    }

    @Override
    public void derretirse(DemonioRojo dr) {
        dr.getSkin().setEstadoActual(5);
    }
    
}
