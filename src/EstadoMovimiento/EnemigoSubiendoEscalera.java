package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;
import Entidades.Estructuras.Plataforma;

public class EnemigoSubiendoEscalera implements EstadoMovimientoEnemigo {
    private static final int VELOCIDAD_SUBIDA = 2;
    private ColisionManagerEntidades colisionManager;
    private EstadoMovimientoEnemigo estadoAnterior;
    
    public EnemigoSubiendoEscalera(EstadoMovimientoEnemigo estadoAnterior) {
        colisionManager = new ColisionManagerEntidades();
        this.estadoAnterior = estadoAnterior;
    }
    
    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        boolean estaColisionandoConEscalera = estaColisionandoConEscalera(enemigo);
        
        if (!estaColisionandoConEscalera || !enemigo.debeSubirEscalera()) {
            verificarPlataformaDebajo(enemigo);
            
            if (estadoAnterior != null) {
                enemigo.cambiarEstadoMovimiento(estadoAnterior);
            } else {
                enemigo.cambiarEstadoMovimiento(new EnemigoCaminandoDerecha());
            }
            enemigo.setEnContactoConEscalera(false);
            enemigo.setDebeSubirEscalera(false);
            return;
        }
        
        int nuevaY = enemigo.getPosY() + VELOCIDAD_SUBIDA;
        
        Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), 
                                         enemigo.getPosX(), nuevaY);
        
        boolean colisionaria = false;
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                if (estructura.bloquearMovimientoHorizontal()) {
                    colisionaria = true;
                    break;
                }
            }
        }
        
        if (!colisionaria) {
            enemigo.setPosY(nuevaY);
        } else {
            verificarPlataformaDebajo(enemigo);
            if (estadoAnterior != null) {
                enemigo.cambiarEstadoMovimiento(estadoAnterior);
            } else {
                enemigo.cambiarEstadoMovimiento(new EnemigoCaminandoDerecha());
            }
            enemigo.setEnContactoConEscalera(false);
            enemigo.setDebeSubirEscalera(false);
        }
        enemigo.notificarObserver();
    }
    
    private boolean estaColisionandoConEscalera(Enemigo enemigo) {
        for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
            if (colisionManager.colisionaAABB(enemigo.getHitbox(), estructura.getHitbox())) {
                if (!estructura.bloquearMovimientoHorizontal() && !estructura.esSueloSolido()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void verificarPlataformaDebajo(Enemigo enemigo) {
        Estructura plataformaDebajo = colisionManager.getPlataformaDebajo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
        if (plataformaDebajo != null) {
            int techoPlataforma = plataformaDebajo.getHitbox().getPosY() + plataformaDebajo.getHitbox().getAlto();
            int pieEnemigo = enemigo.getPosY();
            if (Math.abs(pieEnemigo - techoPlataforma) <= 5) {
                enemigo.setPosY(techoPlataforma);
            }
        }
    }
    
    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        if (estadoAnterior != null) {
            return estadoAnterior.getEstadoOpuesto();
        }
        return new EnemigoCaminandoIzquierda();
    }
    
    @Override
    public boolean permiteMovimiento() {
        return true;
    }
    
    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return estadoAnterior;
    }
    
    @Override
    public boolean permiteSalto() {
        return false;
    }
    
    @Override
    public void afectar(Enemigo enemigo, Plataforma plataforma) {
       //No hace nada
    }
}