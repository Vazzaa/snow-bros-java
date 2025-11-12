package EstadoMovimiento;

import Entidades.Enemigos.*;

public class EstadoCompletamenteCongelado implements EstadoEnemigo {

    @Override
    public void recibirDisparo(Enemigo enemigo) {
        enemigo.getSkin().setEstadoActual(4);
        enemigo.notificarObserver();
    }
}
