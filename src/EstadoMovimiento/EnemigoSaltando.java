package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Juego.ColisionManager;
import Juego.ColisionManagerEntidades;

public class EnemigoSaltando implements EstadoMovimientoEnemigo {
    ColisionManagerEntidades controladorColisiones; 
    boolean enElSuelo;

    public EnemigoSaltando(){
        controladorColisiones = new ColisionManagerEntidades();
        enElSuelo = true;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoCaminandoDerecha();
    }
    public void moverse(Enemigo enemigo, int velocidad) {
        if (enElSuelo(enemigo)) {
    		velocidad = 12;
            enElSuelo = false;
            // System.out.println("SALTANDO - Velocidad vertical: " + velocidadVertical);
    	};
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
    
     private boolean enElSuelo(Enemigo e) {
        if (e.getJuego().getNivel() == null || e.getJuego().getNivel().getMisEstructuras() == null) {
            return false;
        }
    	if (controladorColisiones.estaEnSuelo(e, e.getJuego().getNivel().getMisEstructuras())) {
            return true;
        }
        return false;
    }


}
