package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;

public class EstadoCompletamenteCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        dr.morir();
    }

    @Override
    public void derretirse(DemonioRojo dr) {
        dr.getSkin().setEstadoActual(6);
    }
    
}
