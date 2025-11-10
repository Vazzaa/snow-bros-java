package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.Movible;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionador;


public class PlatMovilHorizontal extends Plataforma implements Movible{

    private int velocidad;
    private int direccion;
    private int rangoMovimiento;
    private int posicionInicialX;
    private boolean puntajeOtorgado;

    public PlatMovilHorizontal(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y);
        posicionInicialX = x;
        velocidad = 1;
        direccion = 1; 
        rangoMovimiento = 100; 
        puntajeOtorgado = false;
    }

    public void afectar(SnowBro s) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, s.getHitbox());
        if (s.getEstadoMovimiento().enElSuelo() && colisiona) {
            s.moverHorizontalmente(velocidad * direccion);
        }
        if (!puntajeOtorgado) {
            s.sumarPuntaje(300);
            puntajeOtorgado = true;
        }
    }

    public void afectar (Enemigo e) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (colisiona && !e.esVolador()) { 
            e.moverHorizontalmente(velocidad * direccion);
        }
    }

    public void setSkin (Skin s) {

    }

    @Override
    public void moverse() {
        miHitbox.setPosX(miHitbox.getPosX() + velocidad * direccion);
        if (miHitbox.getPosX() >= posicionInicialX + rangoMovimiento || miHitbox.getPosX() <= posicionInicialX - rangoMovimiento) {
            direccion *= -1;
        }
        notificarObserver();
    }
    
    @Override
    public boolean esMovible() {
        return true;
    }
}
