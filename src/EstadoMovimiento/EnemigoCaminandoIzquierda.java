package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;
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
        }

        if (!colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            enemigo.setPosY(enemigo.getPosY() - GRAVEDAD);
        } 

        int velocidadHorizontalPropia = velocidad;
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

        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura.bloquearMovimientoHorizontal()) {
                Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), nuevaX, enemigo.getPosY());
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    enemigo.cambiarEstado();
                    colisionariaHorizontal = true;
                    break;
                }
            }
        }
        if (!colisionariaHorizontal) {
            enemigo.setPosX(nuevaX);
        }

        if (velocidadVerticalActual != 0) {
            Hitbox hitboxFuturaVertical = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);
            boolean colisionariaVertical = false;
            for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
                if (colisionManager.colisionaAABB(hitboxFuturaVertical, estructura.getHitbox())) {
                    if (velocidadVerticalActual < 0) {
                        int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                        enemigo.setPosY(techoEstructura);
                        velocidadVerticalActual = 0;
                        colisionariaVertical = true;
                        break;
                    }
                }
            }
            if (!colisionariaVertical) {
                enemigo.setPosY(nuevaY);
            }
        } else {
            enemigo.setPosY(nuevaY);
        }
        enemigo.notificarObserver();
    }

    @Override
    public boolean permiteMovimiento() {
        return true;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        // No aplica para este caso
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }
    
    public boolean permiteSalto() {
        return true;
    }

    @Override
    public void afectar(Enemigo enemigo, Plataforma plataforma) {
        int pieEnemigo = enemigo.getPosY();
        int techoPlataforma = plataforma.getHitbox().getPosY() + plataforma.getHitbox().getAlto();

        if (colisionManager.colisionaAABB(enemigo.getHitbox(), plataforma.getHitbox()) && Math.abs(pieEnemigo - techoPlataforma) < 5) {
            enemigo.setPosY(techoPlataforma);
            enemigo.setVelocidadPlataforma(plataforma.getVelocidadDeArrastreX(), plataforma.getVelocidadDeArrastreY());
        }
    }

}
