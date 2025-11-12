package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Plataforma;
import Juego.Hitbox;
import Juego.ColisionManagerEntidades;

public class EnemigoBajandoPlataforma implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private static final int GRAVEDAD = 2;
    private Estructura plataformaIgnorada;

    public EnemigoBajandoPlataforma(Enemigo enemigo) {
        this.colisionManager = new ColisionManagerEntidades();
        // El estado es responsable de obtener la plataforma a ignorar, usando el manager.
        this.plataformaIgnorada = colisionManager.getPlataformaDebajo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        // 1. Calcular la nueva posición potencial
        int nuevaY = enemigo.getPosY() - GRAVEDAD;

        // Comprobación para no caer por debajo del suelo del nivel (Y=7610)
        if (nuevaY < 7650) {
            enemigo.setPosY(7650); // Lo posicionamos en el suelo
            enemigo.cambiarEstado(); // Le decimos que decida su próximo movimiento
            enemigo.notificarObserver();
            return; // Salimos para no seguir procesando la caída
        }

        Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);

        // 2. Comprobar si aterriza en una nueva plataforma
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            // Solo nos interesan las plataformas que no sean la que estamos atravesando
            if (estructura != plataformaIgnorada) {
                // Comprobamos si la hitbox futura colisiona con la superficie de la estructura
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                    // Si el pie del enemigo está a punto de aterrizar sobre la estructura
                    if (enemigo.getPosY() >= techoEstructura && nuevaY < techoEstructura) {
                        enemigo.setPosY(techoEstructura); // Ajustamos al suelo
                        // Le decimos al enemigo que decida su próximo estado.
                        enemigo.cambiarEstado();
                        enemigo.notificarObserver();
                        return; // Dejamos de movernos en este frame, ya aterrizamos.
                    }
                }
            }
        }

        // 3. Si no se encontró colisión, aplicar la gravedad
        enemigo.setPosY(nuevaY);
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
