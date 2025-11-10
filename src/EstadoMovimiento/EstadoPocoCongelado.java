package EstadoMovimiento;

import Entidades.Enemigos.*;

public class EstadoPocoCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(Enemigo dr) {
        dr.setSkin(dr.getJuego().getNivel().getMiFabrica().getFabricaSkin().crearSkinBolaDeNieve());
    }
}
