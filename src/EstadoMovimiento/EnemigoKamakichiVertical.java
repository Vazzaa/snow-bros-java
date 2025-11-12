package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Plataforma;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;
import Grafica.ConstantesVistas;

public class EnemigoKamakichiVertical implements EstadoMovimientoEnemigo {
    private ColisionManagerEntidades colisionManager;
    private float velocidadVertical;
    private static final float FUERZA_SALTO = 8.0f;
    private static final float GRAVEDAD = 0.4f;
    private int direccionVertical; // 1 = arriba, -1 = abajo
    private static final int CENTRO_PANTALLA_X = ConstantesVistas.PANEL_ANCHO / 2; // 400
    private boolean estaEnSuelo;
    
    public EnemigoKamakichiVertical(int direccion) {
        colisionManager = new ColisionManagerEntidades();
        this.direccionVertical = direccion;
        velocidadVertical = direccion > 0 ? FUERZA_SALTO : -FUERZA_SALTO;
        estaEnSuelo = false;
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        int posXActual = enemigo.getPosX();
        int centroX = CENTRO_PANTALLA_X - (enemigo.getHitbox().getAncho() / 2);
        
        if (Math.abs(posXActual - centroX) > 2) {
            int ajusteX = (centroX > posXActual) ? 2 : -2;
            enemigo.setPosX(posXActual + ajusteX);
        }
        
        estaEnSuelo = colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
        
        int velocidadVerticalTotal = (int)velocidadVertical + enemigo.getVelocidadPlataformaY();
        int nuevaY = enemigo.getPosY() + velocidadVerticalTotal;
        
        
        if (velocidadVerticalTotal < 0) {
            Hitbox hitboxFutura = new Hitbox(enemigo.getHitbox().getAncho(), enemigo.getHitbox().getAlto(), enemigo.getPosX(), nuevaY);
            boolean colisionoConPlataforma = false;
            
            for (Estructura estructura : enemigo.getJuego().getNivel().getMisEstructuras()) {
                if (colisionManager.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                    int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                    int pieEnemigo = nuevaY;
                    if (pieEnemigo <= techoEstructura && enemigo.getPosY() > techoEstructura) {
                        enemigo.setPosY(techoEstructura);
                        velocidadVertical = 0;
                        colisionoConPlataforma = true;
                        estaEnSuelo = true;
                        break;
                    }
                }
            }
            
            if (!colisionoConPlataforma) {
                enemigo.setPosY(nuevaY);
            }
        } else if (velocidadVerticalTotal > 0) {
            enemigo.setPosY(nuevaY);
        } else {
            enemigo.setPosY(nuevaY);
        }
        
        if (estaEnSuelo && velocidadVertical <= 0) {
            velocidadVertical = 0;
        } else {
            velocidadVertical -= GRAVEDAD;
        }
        
        enemigo.notificarObserver();
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        return new EnemigoKamakichiVertical(-direccionVertical);
    }

    @Override
    public boolean permiteMovimiento() {
        return true;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        return null;
    }

    @Override
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
            estaEnSuelo = true;
            velocidadVertical = 0;
        }
    }

        @Override
    public EstadoMovimientoEnemigo obtenerSiguienteEstado(Enemigo enemigo) {
        return null; // Deja que Kamakichi decida su siguiente estado
    }

    @Override
    public boolean puedeCambiarEstado(Enemigo enemigo) {
        estaEnSuelo = colisionManager.estaEnSuelo(enemigo, enemigo.getJuego().getNivel().getMisEstructuras());
        return estaEnSuelo && velocidadVertical == 0;
    }
    
    public boolean estaEnSuelo() {
        return estaEnSuelo;
    }
    
    public void iniciarSalto() {
        direccionVertical = 1;
        velocidadVertical = FUERZA_SALTO;
        estaEnSuelo = false;
    }
}