package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;

public class EstadoActivo implements EstadoEnemigo {
    @Override
    public void recibirDisparo(Enemigo enemigo) {
        // No aplica para este caso
        throw new UnsupportedOperationException("Unimplemented method 'recibirDisparo'");
    }
    
}
