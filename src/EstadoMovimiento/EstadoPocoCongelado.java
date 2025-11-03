package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;

public class EstadoPocoCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        dr.getSkin().setEstadoActual(6);
    }
    
}
