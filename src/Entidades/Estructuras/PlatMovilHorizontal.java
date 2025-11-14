package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
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

        if (colisionaAABB(this.miHitbox, s.getHitbox()) && Math.abs(pieSnowBro - techoPlataforma) < 5) {
            s.setPosY(techoPlataforma);
            s.setVelocidadPlataformaX(velocidad * direccion);
            s.getEstadoMovimiento().enElSuelo = true;
        } 
        if (!puntajeOtorgado) {
            s.sumarPuntaje(300);
            puntajeOtorgado = true;
        }
        s.setEnContactoConEscalera(false);
    }

    public void afectar (Enemigo e) {
        e.afectar(this);
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

    @Override
    public int getVelocidadDeArrastreX() {
        return velocidad * direccion;
    }

    @Override
    public int getVelocidadDeArrastreY() {
        return 0;
    }
}
