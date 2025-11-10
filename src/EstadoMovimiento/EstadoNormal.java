package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;

public class EstadoNormal implements EstadoEnemigo {

    @Override
    public void recibirDisparo(Enemigo dr) {
        dr.setSkin(dr.getSkinOriginal());
    }
}
