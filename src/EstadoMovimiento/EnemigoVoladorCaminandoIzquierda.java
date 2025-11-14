package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class EnemigoVoladorCaminandoIzquierda implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    
    public EnemigoVoladorCaminandoIzquierda() {
        colisionManager = new ColisionManagerEntidades();
    }

    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoVoladorCaminandoDerecha();
    }

    public void moverse(Enemigo enemigo, int velocidad) {
        if (enemigo.getJuego() == null || enemigo.getJuego().getNivel() == null || 
            enemigo.getJuego().getNivel().getMisEstructuras() == null) {
            enemigo.setPosX(enemigo.getPosX() - velocidad);
            enemigo.notificarObserver();
            return;
        }
    
        int nuevaX = enemigo.getPosX() - velocidad;
        boolean colisionariaPared = false;
    
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), nuevaX, enemigo.getPosY());
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
        // No aplica para este caso
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }

    public boolean permiteSalto() {
        return false;
    }
}