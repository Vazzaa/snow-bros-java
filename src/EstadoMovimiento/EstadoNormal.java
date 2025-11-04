package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Fabricas.FabricaSkin;

public class EstadoNormal implements EstadoEnemigo {

    @Override
    public void recibirDisparo(Enemigo dr) {
        dr.setSkin(dr.getSkinOriginal());
    }
}
