package EstadoMovimiento;

import Entidades.Enemigos.*;

public class EstadoMedioCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(Enemigo dr) {
        dr.getSkin().setEstadoActual(3);
    }
}
