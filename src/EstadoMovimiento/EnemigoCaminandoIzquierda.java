package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;

public class EnemigoCaminandoIzquierda implements EstadoMovimientoEnemigo {

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        enemigo.setPosX(enemigo.getPosX() - velocidad);
        //TODO: cambiar la skin a caminando izquierda
        enemigo.notificarObserver();
    }
}
