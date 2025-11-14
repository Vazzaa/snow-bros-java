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
    private static final int TECHO_MUNDO= 8100;
    public static final int PISO_MUNDO= 7600;

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

        boolean colisionariaHorizontal = false;
        boolean colisionariaVertical = false;

        Hitbox hitboxFuturaY = new Hitbox(
            enemigo.getHitbox().getAncho(),
            enemigo.getHitbox().getAlto(),
            enemigo.getPosX(),
            nuevaY
        );

        Hitbox hitboxFuturaX = new Hitbox(
            enemigo.getHitbox().getAncho(),
            enemigo.getHitbox().getAlto(),
            nuevaX,
            enemigo.getPosY() 
        );

        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            
            if (estructura.bloquearMovimientoHorizontal()) {
                if (colisionManager.colisionaAABB(hitboxFuturaX, estructura.getHitbox())) {
                    colisionariaHorizontal = true;
                    dirX *= -1;
                    nuevaX = enemigo.getPosX(); 
                }
            }

            if (colisionManager.colisionaAABB(hitboxFuturaY, estructura.getHitbox())) {
                
                if (dirY > 0 && nuevaY > TECHO_MUNDO) {
                    colisionariaVertical = true;
                    dirY *= -1;
                    nuevaY = enemigo.getPosY();
                }

                else if (dirY < 0 && nuevaY < PISO_MUNDO) {
                    colisionariaVertical = true;
                    dirY *= -1; 
                    nuevaY = enemigo.getPosY(); 
                }
            }
        } 

        if (!colisionariaHorizontal) {
            enemigo.setPosX(nuevaX);
        }
        if (!colisionariaVertical) {
            enemigo.setPosY(nuevaY);
        }

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
        // No aplica para este caso
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }

    public boolean permiteSalto() {
        return false;
    }
}

