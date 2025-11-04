package EstadoMovimiento;

import Entidades.Enemigos.DemonioRojo;
import Fabricas.FabricaSkin;

public class EstadoNormal implements EstadoEnemigo {

    @Override
    public void recibirDisparo(DemonioRojo dr) {
        // Le devolvemos su skin original de DemonioRojo.
        dr.setSkin(dr.getJuego().getNivel().getMiFabrica().getFabricaSkin().crearSkinDemonioRojo());
    }
}
