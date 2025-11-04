package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class EnemigoVoladorDiagonal implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private int dirX;
    private int dirY;
    private long lastChangeTime;
    private static final long CHANGE_INTERVAL = 1000;

    public EnemigoVoladorDiagonal() {
        colisionManager = new ColisionManagerEntidades();
        cambiarDireccion();
    }

    private void cambiarDireccion() {
        int random = (int) (Math.random() * 4 + 1);
        switch (random) {
            case 1:
                dirX = 1; // Abajo-derecha
                dirY = 1;
                break;
            case 2:
                dirX = 1; // Arriba-derecha
                dirY = -1;
                break;
            case 3:
                dirX = -1; // Abajo-izquierda
                dirY = 1;
                break;
            case 4:
                dirX = -1; // Arriba-izquierda
                dirY = -1;
                break;
        }
        lastChangeTime = System.currentTimeMillis();
    }

    public void moverse(Enemigo enemigo, int velocidad) {
        if (enemigo.getJuego() == null || enemigo.getJuego().getNivel() == null || 
            enemigo.getJuego().getNivel().getMisEstructuras() == null) {
            int nuevaX = enemigo.getPosX() + (dirX * velocidad);
            int nuevaY = enemigo.getPosY() + (dirY * velocidad);
            enemigo.setPosX(nuevaX);
            enemigo.setPosY(nuevaY);
            enemigo.notificarObserver();
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastChangeTime >= CHANGE_INTERVAL) {
            cambiarDireccion();
        }

        int nuevaX = enemigo.getPosX() + (dirX * velocidad);
        int nuevaY = enemigo.getPosY() + (dirY * velocidad);

        boolean colisionariaPared = false;
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
                    dirX *= -1;
                    nuevaX = enemigo.getPosX();
                    break;
                }
            }
        }

        // Aplicar movimiento
        if (!colisionariaPared) {
            enemigo.setPosX(nuevaX);
        }
        enemigo.setPosY(nuevaY);
        enemigo.notificarObserver();
    }

    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoVoladorCaminandoDerecha();
    }

    @Override
    public boolean permiteMovimiento() {
        return true;
    }

    public EstadoMovimientoEnemigo getEstadoAnterior() {
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }
}

