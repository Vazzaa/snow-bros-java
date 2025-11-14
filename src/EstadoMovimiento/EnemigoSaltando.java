package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.Hitbox;
import Entidades.Estructuras.Plataforma;
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

        if (velocidadVerticalTotal != 0) {
            Hitbox hitboxFuturaVertical = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);
            boolean colisionariaVertical = false;
            for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
                if (colisionManager.colisionaAABB(hitboxFuturaVertical, estructura.getHitbox())) {
                    if (velocidadVerticalTotal < 0) {
                        int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                        enemigo.setPosY(techoEstructura);
                        velocidadVertical = 0;
                        colisionariaVertical = true;
                        break;
                    } else if (velocidadVerticalTotal > 0) {
                        int sueloEstructura = estructura.getHitbox().getPosY();
                        enemigo.setPosY(sueloEstructura - enemigo.getHitbox().getAlto());
                        velocidadVertical = 0;
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

    @Override
    public void afectar(Enemigo enemigo, Plataforma plataforma) {
        int pieEnemigo = enemigo.getPosY();
        int techoPlataforma = plataforma.getHitbox().getPosY() + plataforma.getHitbox().getAlto();

        if (colisionManager.colisionaAABB(enemigo.getHitbox(), plataforma.getHitbox()) && Math.abs(pieEnemigo - techoPlataforma) < 5) {
            enemigo.setPosY(techoPlataforma);
            enemigo.setVelocidadPlataforma(plataforma.getVelocidadDeArrastreX(), plataforma.getVelocidadDeArrastreY());
        }
    }

    @Override
    public EstadoMovimientoEnemigo obtenerSiguienteEstado(Enemigo enemigo) {
        if (colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras())) {
            return new EnemigoQuieto();
        }
        return null;
    }

    @Override
    public boolean puedeCambiarEstado(Enemigo enemigo) {
        return velocidadVertical <= 0 && colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
    }
}
