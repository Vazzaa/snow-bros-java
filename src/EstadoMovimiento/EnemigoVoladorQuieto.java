package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;

public class EnemigoVoladorQuieto implements EstadoMovimientoEnemigo {
    private EstadoMovimientoEnemigo estadoAnterior;
    
    public EnemigoVoladorQuieto(EstadoMovimientoEnemigo estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }
    
    public EnemigoVoladorQuieto() {
        this.estadoAnterior = new EnemigoVoladorCaminandoDerecha();
    }
    
    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        if (estadoAnterior != null) {
            return estadoAnterior.getEstadoOpuesto();
        }
        return new EnemigoVoladorCaminandoIzquierda();
    }
    
    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        enemigo.notificarObserver();
    }
    
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return estadoAnterior;
    }

    public boolean permiteMovimiento() {
        return false;
    }

    public boolean permiteSalto() {
        return false;
    }
}