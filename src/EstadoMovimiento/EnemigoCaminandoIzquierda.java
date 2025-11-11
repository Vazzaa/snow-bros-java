package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;
import Juego.Nivel;
import Entidades.Estructuras.Plataforma;



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

        int velocidadHorizontalPropia = velocidad;
        // Si el enemigo está sobre una plataforma móvil, su propio movimiento horizontal se suprime.
        if (enemigo.estaEnPlataformaMovil()) {
            velocidadHorizontalPropia = 0;
        }

        int velocidadTotalX = -velocidadHorizontalPropia + enemigo.getVelocidadPlataformaX();

        int velocidadVerticalActual = 0;
        if (!colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            velocidadVerticalActual -= GRAVEDAD;
        }
        velocidadVerticalActual += enemigo.getVelocidadPlataformaY();

        int nuevaX = enemigo.getPosX() + velocidadTotalX;
        int nuevaY = enemigo.getPosY() + velocidadVerticalActual;

        boolean colisionariaHorizontal = false;

        // Comprobación de colisiones horizontales
        // La variable 'colisionaria' se renombra a 'colisionariaHorizontal' para mayor claridad
        // y se usa en el bucle de estructuras.

        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), nuevaX, enemigo.getPosY());
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    enemigo.cambiarEstado();
                    colisionariaHorizontal = true; // Actualizar la nueva bandera
                    break;
                }
            }
        }
        if (!colisionariaHorizontal) {
            enemigo.setPosX(nuevaX); // Aplicar movimiento horizontal si no hay colisión
        }

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

    @Override
    public void afectar(Enemigo enemigo, Plataforma plataforma) {
        int pieEnemigo = enemigo.getPosY();
        int techoPlataforma = plataforma.getHitbox().getPosY() + plataforma.getHitbox().getAlto();

        // Si el enemigo está encima de la plataforma (con una pequeña tolerancia)
        if (colisionManager.colisionaAABB(enemigo.getHitbox(), plataforma.getHitbox()) && Math.abs(pieEnemigo - techoPlataforma) < 5) {
            // "Pega" al enemigo a la superficie para que no la atraviese por la gravedad
            enemigo.setPosY(techoPlataforma);
            // Transfiere la velocidad de arrastre de la plataforma al enemigo
            enemigo.setVelocidadPlataforma(plataforma.getVelocidadDeArrastreX(), plataforma.getVelocidadDeArrastreY());
        }
    }

}
