package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;

public class EnemigoBajandoPlataforma implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private static final int GRAVEDAD = 2;
    private Estructura plataformaIgnorada;

    public EnemigoBajandoPlataforma(Estructura plataformaActual) {
        this.colisionManager = new ColisionManagerEntidades();
        this.plataformaIgnorada = plataformaActual;
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        enemigo.setPosY(enemigo.getPosY() - GRAVEDAD);
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura != plataformaIgnorada && colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
                if (Math.random() > 0.5) {
                    enemigo.cambiarEstadoMovimiento(new EnemigoCaminandoDerecha());
                } else {
                    enemigo.cambiarEstadoMovimiento(new EnemigoCaminandoIzquierda());
                }
                return;
            }
        }
        enemigo.notificarObserver();
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return this;
    }

    @Override
    public boolean permiteMovimiento() {
        return false;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return null;
    }
}
