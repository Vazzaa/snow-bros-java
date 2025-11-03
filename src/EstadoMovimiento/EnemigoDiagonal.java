package EstadoMovimiento;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Juego.ColisionManagerEntidades;
import Juego.Hitbox;

public class EnemigoDiagonal implements EstadoMovimientoEnemigo{
    private ColisionManagerEntidades colisionManager;
    private int dirX;
    private int dirY;
    private long lastChangeTime;
    private static final long CHANGE_INTERVAL = 1000;

    public EnemigoDiagonal(){
        colisionManager= new ColisionManagerEntidades();
    }

    private void cambiarDireccion(){
        int random = (int) (Math.random() * 4 + 1);
        switch (random) {
            case 1:
                dirX = 1; // Abajo-derecha
                dirY = 1;
                break;
            case 2:
                dirX = 1; // Arriba-derecha
                dirY = -1;
                break;
            case 3:
                dirX = -1; // Abajo-izquierda
                dirY = 1;
                break;
            case 4:
                dirX = -1; // Arriba-izquierda
                dirY = -1;
                break;
        }
        lastChangeTime = System.currentTimeMillis();
    }

    @Override
    public void moverse(Enemigo enemigo, int velocidad) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastChangeTime >= CHANGE_INTERVAL) {
            cambiarDireccion();
        }
        int nuevaX = enemigo.getPosX() + (dirX * velocidad);
        int nuevaY = enemigo.getPosY() + (dirY * velocidad);
        enemigo.setPosX(nuevaX);
        enemigo.setPosY(nuevaY);
        enemigo.notificarObserver();
        return;
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoOpuesto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoOpuesto'");
    }

    @Override
    public boolean permiteMovimiento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'permiteMovimiento'");
    }

    @Override
    public EstadoMovimientoEnemigo getEstadoAnterior() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstadoAnterior'");
    }
    
}
