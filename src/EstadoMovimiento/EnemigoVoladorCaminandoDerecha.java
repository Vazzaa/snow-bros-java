package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class EnemigoVoladorCaminandoDerecha implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    
    public EnemigoVoladorCaminandoDerecha() {
        colisionManager = new ColisionManagerEntidades();
    }

    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoVoladorCaminandoIzquierda();
    }
    
    public void moverse(Enemigo enemigo, int velocidad) {
        if (enemigo.getJuego() == null || enemigo.getJuego().getNivel() == null || 
            enemigo.getJuego().getNivel().getMisEstructuras() == null) {
            enemigo.setPosX(enemigo.getPosX() + velocidad);
            enemigo.notificarObserver();
            return;
        }
    
        int nuevaX = enemigo.getPosX() + velocidad;
        boolean colisionariaPared = false;
    
        // Verificar colisiones horizontales con paredes
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(
                    enemigo.getHitbox().getAncho(), 
                    enemigo.getHitbox().getAlto(), 
                    nuevaX, 
                    enemigo.getPosY()
                );
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    colisionariaPared = true;
                    enemigo.cambiarEstadoInmediato();
                    break;
                }
            }
        }

        if (!colisionariaPared) {
            enemigo.setPosX(nuevaX);
        }
        enemigo.notificarObserver();
    }

    public boolean permiteMovimiento() {
        return true;
    }

    public EstadoMovimientoEnemigo getEstadoAnterior() {
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }
}
