package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class PlatMovilVertical extends Plataforma {

    private int velocidad;
    private int direccion;
    private int rangoMovimiento;
    private int posicionInicialY;
    private boolean puntajeOtorgado;

    public PlatMovilVertical(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y);
        posicionInicialY = y;
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
            s.setVelocidadPlataforma(0, velocidad * direccion); // Esto ya era correcto
            s.getEstadoMovimiento().enElSuelo = true; // Forzamos el estado a "enElSuelo"
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
        miHitbox.setPosY(miHitbox.getPosY() + velocidad * direccion);
        if (miHitbox.getPosY() >= posicionInicialY + rangoMovimiento || miHitbox.getPosY() <= posicionInicialY - rangoMovimiento) {
            direccion *= -1;
        }
        notificarObserver();
    }

    @Override
    public int getVelocidadDeArrastreX() {
        return 0;
    }

    @Override
    public int getVelocidadDeArrastreY() {
        return velocidad * direccion;
    }
}
