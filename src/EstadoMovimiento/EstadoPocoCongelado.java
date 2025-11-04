package EstadoMovimiento;

import Entidades.Enemigos.*;
import Fabricas.FabricaSkin;

public class EstadoPocoCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(Enemigo dr) {
        dr.setSkin(dr.getJuego().getNivel().getMiFabrica().getFabricaSkin().crearSkinBolaDeNieve());
    }
}
