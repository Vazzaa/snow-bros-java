package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Juego.ColisionManagerEntidades;

public class EnemigoSaltando implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private float velocidadVertical;
    private static final float FUERZA_SALTO = 11.0f; 
    private static final float GRAVEDAD = 0.5f;      

    public EnemigoSaltando() {
        colisionManager = new ColisionManagerEntidades();
        velocidadVertical = FUERZA_SALTO;
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        enemigo.setPosY(enemigo.getPosY() + (int)velocidadVertical);
        velocidadVertical -= GRAVEDAD;
        if (velocidadVertical <= 0 && colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            enemigo.cambiarEstadoMovimiento(new EnemigoQuieto()); 
        }
        enemigo.notificarObserver();
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return this;
    }

    @Override
    public boolean permiteMovimiento() {
        return false; // No permite cambiar de dirección mientras salta.
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return null;
    }
}