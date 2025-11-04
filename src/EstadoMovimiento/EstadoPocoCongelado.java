package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;
import Fabricas.FabricaSkin;

public class EstadoPocoCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        // En lugar de cambiar el índice, le asignamos la skin de BolaDeNieve.
        dr.setSkin(dr.getJuego().getNivel().getMiFabrica().getFabricaSkin().crearSkinBolaDeNieve());
    }
}
