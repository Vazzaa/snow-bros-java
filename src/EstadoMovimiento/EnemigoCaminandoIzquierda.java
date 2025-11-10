package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class EnemigoCaminandoIzquierda implements EstadoMovimientoEnemigo {
    private static final int GRAVEDAD = 1;
    private ColisionManagerEntidades colisionManager;
    
    public EnemigoCaminandoIzquierda() {
        colisionManager = new ColisionManagerEntidades();
    }

    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoCaminandoDerecha();
    }

    public void moverse(Enemigo enemigo, int velocidad) {
        if (enemigo.getJuego() == null || enemigo.getJuego().getNivel() == null || enemigo.getJuego().getNivel().getMisEstructuras() == null) {
            enemigo.setPosX(enemigo.getPosX() - velocidad);
            enemigo.notificarObserver();
            return;
        //TODO: cambiar la skin a caminando izquierda
        }

        if (!colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            enemigo.setPosY(enemigo.getPosY() - GRAVEDAD);
        } 

        int nuevaX = enemigo.getPosX() - velocidad;
        boolean colisionaria = false;

        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), nuevaX, enemigo.getPosY());
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    colisionaria = true;
                    enemigo.cambiarEstado();
                    break;
                }
            }
        }
        if (!colisionaria) {
            enemigo.setPosX(nuevaX);
        }
        enemigo.notificarObserver();
    }

    @Override
    public boolean permiteMovimiento() {
        return true;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }
    
    public boolean permiteSalto() {
        return true;
    }
}
