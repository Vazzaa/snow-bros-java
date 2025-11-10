package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;

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
                Estructura plataformaDebajo = colisionManager.getPlataformaDebajo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
                if (plataformaDebajo != null) { enemigo.setPosY(plataformaDebajo.getHitbox().getPosY() + plataformaDebajo.getHitbox().getAlto()); }
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