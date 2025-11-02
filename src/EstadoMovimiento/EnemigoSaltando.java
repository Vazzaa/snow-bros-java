package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;

public class EnemigoSaltando implements EstadoMovimientoEnemigo {
    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoCaminandoDerecha();
    }
    public void moverse(Enemigo enemigo, int velocidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moverse'");
    }
    @Override
    public boolean permiteMovimiento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'permiteMovimiento'");
    }
    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }



}
