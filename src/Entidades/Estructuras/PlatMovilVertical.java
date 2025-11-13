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
    private SnowBro jugadorEncima; // Referencia al jugador que está encima

    public PlatMovilVertical(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y);
        posicionInicialY = y;
        velocidad = 1;
        direccion = 1; 
        rangoMovimiento = 100; 
        puntajeOtorgado = false;
        jugadorEncima = null;
    }

    public void afectar(SnowBro s) {
        int pieSnowBro = s.getPosY();
        int techoPlataforma = this.miHitbox.getPosY() + this.miHitbox.getAlto();
        
        boolean estaHorizontalmenteSobre = !(s.getPosX() > this.miHitbox.getPosX() + this.miHitbox.getAncho() || s.getPosX() + s.getHitbox().getAncho() < this.miHitbox.getPosX());
        
        int distanciaVertical = pieSnowBro - techoPlataforma;
        
        if (estaHorizontalmenteSobre && distanciaVertical >= -10 && distanciaVertical <= 5) {
            s.setPosY(techoPlataforma);
            jugadorEncima = s;
            s.setVelocidadPlataforma(0, velocidad * direccion);
            s.getEstadoMovimiento().enElSuelo = true;
        } else {
            if (jugadorEncima == s) {
                jugadorEncima = null;
            }
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
        if (jugadorEncima != null) {
            int pieSnowBro = jugadorEncima.getPosY();
            int techoPlataforma = this.miHitbox.getPosY() + this.miHitbox.getAlto();
            boolean estaHorizontalmenteSobre = !(jugadorEncima.getPosX() > this.miHitbox.getPosX() + this.miHitbox.getAncho() ||
                                                 jugadorEncima.getPosX() + jugadorEncima.getHitbox().getAncho() < this.miHitbox.getPosX());
            
            if (estaHorizontalmenteSobre && Math.abs(pieSnowBro - techoPlataforma) <= 10) {
                jugadorEncima.setPosY(techoPlataforma);
                jugadorEncima.setVelocidadPlataforma(0, velocidad * direccion);
            } else {
                jugadorEncima = null;
            }
        }
        
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
