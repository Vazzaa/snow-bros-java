package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Juego.ColisionManagerEntidades;

public class EnemigoSaltando implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private int velocidadVertical;
    private boolean haSaltado;
    private static final int FUERZA_SALTO = 12;
    private static final int GRAVEDAD = 1;

    public EnemigoSaltando() {
        this.colisionManager = new ColisionManagerEntidades();
        this.velocidadVertical = FUERZA_SALTO;
        this.haSaltado = false;
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        if (!haSaltado) {
            haSaltado = true;
        } else {
            velocidadVertical -= GRAVEDAD;
        }

        enemigo.setPosY(enemigo.getPosY() + velocidadVertical);

        if (velocidadVertical <= 0 && colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            if (Math.random() > 0.5) {
                enemigo.cambiarEstadoMovimiento(new EnemigoCaminandoDerecha());
            } else {
                enemigo.cambiarEstadoMovimiento(new EnemigoCaminandoIzquierda());
            }
        }
        
        enemigo.notificarObserver();
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        // No tiene un opuesto directo, al terminar de saltar se decide el nuevo estado.
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