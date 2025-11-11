package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.Hitbox;
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
        int velocidadVerticalTotal = (int)velocidadVertical + enemigo.getVelocidadPlataformaY();
        int nuevaY = enemigo.getPosY() + velocidadVerticalTotal;

        // Comprobación de colisiones verticales para enemigos
        if (velocidadVerticalTotal != 0) {
            Hitbox hitboxFuturaVertical = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);
            boolean colisionariaVertical = false;
            for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
                if (colisionManager.colisionaAABB(hitboxFuturaVertical, estructura.getHitbox())) {
                    if (velocidadVerticalTotal < 0) { // Moviéndose hacia abajo
                        int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                        enemigo.setPosY(techoEstructura); // Ajustar a la superficie
                        velocidadVertical = 0; // Detener movimiento vertical
                        colisionariaVertical = true;
                        break;
                    } else if (velocidadVerticalTotal > 0) { // Moviéndose hacia arriba
                        int sueloEstructura = estructura.getHitbox().getPosY();
                        enemigo.setPosY(sueloEstructura - enemigo.getHitbox().getAlto()); // Ajustar al techo
                        velocidadVertical = 0; // Detener movimiento vertical
                        colisionariaVertical = true;
                        break;
                    }
                }
            }
            if (!colisionariaVertical) {
                enemigo.setPosY(nuevaY); // Aplicar movimiento vertical si no hay colisión
            }
        } else {
            enemigo.setPosY(nuevaY); // Aplicar movimiento vertical incluso si es solo gravedad
        }
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
        return false;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return null;
    }

    public boolean permiteSalto() {
        return false; 
    }
}