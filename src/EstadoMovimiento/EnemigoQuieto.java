package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class EnemigoQuieto implements EstadoMovimientoEnemigo {
    private static final int GRAVEDAD = 1;
    private ColisionManagerEntidades colisionManager;
    private EstadoMovimientoEnemigo estadoAnterior; 
    
    public EnemigoQuieto(EstadoMovimientoEnemigo estadoAnterior) {
        colisionManager = new ColisionManagerEntidades();
        this.estadoAnterior = estadoAnterior;
    }
    
    public EnemigoQuieto() {
        colisionManager = new ColisionManagerEntidades();
        this.estadoAnterior = new EnemigoCaminandoDerecha(); 
    }
    
    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        if (estadoAnterior != null) {
            return estadoAnterior.getEstadoOpuesto();
        }
        return new EnemigoCaminandoIzquierda();
    }
    
    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        if (enemigo.getJuego() != null && enemigo.getJuego().getNivel() != null && 
            enemigo.getJuego().getNivel().getMisEstructuras() != null && !enemigo.esVolador()) { 

            if (!colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
                enemigo.setPosY(enemigo.getPosY() - GRAVEDAD);
            } else { 
                // Si está en el suelo, la gravedad ya no lo empuja.
                // La posición se ajustará con la velocidad de la plataforma si aplica.
            }

            int velocidadVerticalActual = 0;
            if (!colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
                velocidadVerticalActual -= GRAVEDAD;
            }
            velocidadVerticalActual += enemigo.getVelocidadPlataformaY();

            int nuevaY = enemigo.getPosY() + velocidadVerticalActual;

            // Comprobación de colisiones verticales para enemigos
            if (velocidadVerticalActual != 0) {
                Hitbox hitboxFuturaVertical = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);
                boolean colisionariaVertical = false;
                for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
                    if (colisionManager.colisionaAABB(hitboxFuturaVertical, estructura.getHitbox())) {
                        if (velocidadVerticalActual < 0) { // Moviéndose hacia abajo
                            int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                            enemigo.setPosY(techoEstructura); // Ajustar a la superficie
                            velocidadVerticalActual = 0; // Detener movimiento vertical
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
        }
        
        enemigo.notificarObserver();
    }
    
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return estadoAnterior;
    }

    public boolean permiteMovimiento() {
        return false;
    }
    
    public boolean permiteSalto() {
        return false;
    }
}