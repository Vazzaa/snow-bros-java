package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.Movible;
import Fabricas.Skin;
import Juego.ModoDeJuego;


public class PlatMovilHorizontal extends Plataforma {

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
        int pieSnowBro = s.getPosY();
        int techoPlataforma = this.miHitbox.getPosY() + this.miHitbox.getAlto();

        // Si el jugador está encima de la plataforma (con una pequeña tolerancia)
        if (colisionaAABB(this.miHitbox, s.getHitbox()) && Math.abs(pieSnowBro - techoPlataforma) < 5) {
            // "Pega" al jugador a la superficie para que no la atraviese por la gravedad
            s.setPosY(techoPlataforma);
            // Transfiere la velocidad de la plataforma al jugador
            s.setVelocidadPlataformaX(velocidad * direccion);
            s.getEstadoMovimiento().enElSuelo = true; // Forzamos el estado a "enElSuelo"
        } 
        if (!puntajeOtorgado) {
            s.sumarPuntaje(300);
            puntajeOtorgado = true;
        }
        s.setEnContactoConEscalera(false);
    }

    public void afectar (Enemigo e) {
        int pieEnemigo = e.getPosY();
        int techoPlataforma = this.miHitbox.getPosY() + this.miHitbox.getAlto();

        if (colisionaAABB(this.miHitbox, e.getHitbox()) && Math.abs(pieEnemigo - techoPlataforma) < 5 && !e.esVolador()) {
            // "Pega" al enemigo a la superficie
            e.setPosY(techoPlataforma);
            // Transfiere la velocidad de la plataforma al enemigo
            e.setVelocidadPlataforma(velocidad * direccion, 0);
        }
    }

    public void setSkin (Skin s) {

    }

    @Override
    public void mover() {
        miHitbox.setPosX(miHitbox.getPosX() + velocidad * direccion);
        if (miHitbox.getPosX() >= posicionInicialX + rangoMovimiento || miHitbox.getPosX() <= posicionInicialX - rangoMovimiento) {
            direccion *= -1;
        }
        notificarObserver();
    }
}
