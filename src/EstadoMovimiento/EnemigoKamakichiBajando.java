package EstadoMovimiento;

import java.util.List;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Plataforma;
import Juego.Hitbox;
import Juego.ColisionManagerEntidades;
import Grafica.ConstantesVistas;

public class EnemigoKamakichiBajando implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private static final int GRAVEDAD = 4;
    private Estructura plataformaIgnorada;
    private static final int CENTRO_PANTALLA_X = ConstantesVistas.PANEL_ANCHO / 2;
    
    public EnemigoKamakichiBajando(Enemigo enemigo) {
        this.colisionManager = new ColisionManagerEntidades();
        this.plataformaIgnorada = plataformaDondePisa(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
        enemigo.setPosY(enemigo.getPosY() - 2);
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        // Mantener centrado horizontalmente
        int posXActual = enemigo.getPosX();
        int centroX = CENTRO_PANTALLA_X - (enemigo.getHitbox().getAncho() / 2);
        
        if (Math.abs(posXActual - centroX) > 2) {
            int ajusteX = (centroX > posXActual) ? 2 : -2;
            enemigo.setPosX(posXActual + ajusteX);
        }
        
        int nuevaY = enemigo.getPosY() - GRAVEDAD;
        if (nuevaY < 7650) {
            enemigo.setPosY(7650);
            enemigo.cambiarEstado(); 
            enemigo.notificarObserver();
            return;
        }
        
        Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (estructura != plataformaIgnorada && colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                int pieEnemigo = nuevaY;
                if (pieEnemigo <= techoEstructura && enemigo.getPosY() >= techoEstructura) {
                    enemigo.setPosY(techoEstructura);
                    enemigo.cambiarEstadoMovimiento(new EnemigoQuieto());
                    enemigo.notificarObserver();
                    return; 
                }
            }
        }
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

    @Override
    public boolean permiteSalto() {
        return false;
    }

    @Override
    public void afectar(Enemigo enemigo, Plataforma plataforma) {

    }

    private Estructura plataformaDondePisa(Enemigo enemigo, List<Estructura> estructuras) {
        Hitbox hitboxEntidad = enemigo.getHitbox();
        int toleranciaSuelo = 5;
        Hitbox hitboxDeteccion = new Hitbox(
            hitboxEntidad.getAncho(),
            hitboxEntidad.getAlto() + toleranciaSuelo,
            hitboxEntidad.getPosX(),
            hitboxEntidad.getPosY() - toleranciaSuelo
        );
        
        for (Estructura estructura : estructuras) {
            if (colisionManager.colisionaAABB(hitboxDeteccion, estructura.getHitbox())) {
                int pieEntidad = hitboxEntidad.getPosY();
                int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                if (Math.abs(pieEntidad - techoEstructura) <= toleranciaSuelo) {
                    return estructura;
                }
            }
        }
        return null;
    }

    @Override
    public EstadoMovimientoEnemigo obtenerSiguienteEstado(Enemigo enemigo) {
        return new EnemigoQuieto();
    }

    @Override
    public boolean puedeCambiarEstado(Enemigo enemigo) {
        return colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
    }
}

