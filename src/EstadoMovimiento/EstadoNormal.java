package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;

public class EstadoNormal implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        dr.getSkin().setEstadoActual(5);
    }
    
}
