package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;


public class EnemigoCaminandoDerecha implements EstadoMovimientoEnemigo {
    private static final int GRAVEDAD = 1;
    private ColisionManagerEntidades colisionManager;
    
    public EnemigoCaminandoDerecha() {
        colisionManager = new ColisionManagerEntidades();
    }

    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoCaminandoIzquierda();
    }
    
    public void moverse(Enemigo enemigo, int velocidad) {
        if (enemigo.getJuego() == null || enemigo.getJuego().getNivel() == null || enemigo.getJuego().getNivel().getMisEstructuras() == null) {
            enemigo.setPosX(enemigo.getPosX() + velocidad);
            enemigo.notificarObserver();
            return;
        //TODO: cambiar la skin a caminando izquierda
        }

        if (!colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            enemigo.setPosY(enemigo.getPosY() - GRAVEDAD);
        } 

        int nuevaX = enemigo.getPosX() + velocidad;
        boolean colisionaria = false;

        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if(estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), nuevaX, enemigo.getPosY());
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    colisionaria = true;
                    enemigo.cambiarEstadoInmediato();
                    break;
                }
            }
        }
        if (!colisionaria) {
            enemigo.setPosX(nuevaX);
        }
        Estructura plataformaDebajo = colisionManager.getPlataformaDebajo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
        if (plataformaDebajo != null && colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            int pieEnemigo = enemigo.getHitbox().getPosY();
            int techoPlataforma = plataformaDebajo.getHitbox().getPosY() + plataformaDebajo.getHitbox().getAlto();
            if (pieEnemigo > techoPlataforma) {
                enemigo.setPosY(techoPlataforma);
            }
        }
        enemigo.notificarObserver();
    }
}
