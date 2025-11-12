package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Visitors.Colisionador;
import Juego.ModoDeJuego;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.Proyectiles.Proyectil;

public class Plataforma extends Estructura{

    protected boolean esPlataformaSuelo;
    protected boolean esPlataformaTecho;

    public Plataforma(Skin s, ModoDeJuego juego, int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(12);
        miHitbox.setAlto(32);
        esPlataformaSuelo = false;
    }

    public Plataforma(Skin s, ModoDeJuego juego, int x, int y, boolean esSuelo, boolean esTecho) {
        super(s, juego, x, y);
        miHitbox.setAncho(12);
        miHitbox.setAlto(32);
        esPlataformaSuelo = esSuelo;
        esPlataformaTecho = esTecho;
    }

    public Plataforma(Skin s, ModoDeJuego juego, int x, int y, boolean esSuelo) {
        super(s, juego, x, y);
        miHitbox.setAncho(12);
        miHitbox.setAlto(32);
        esPlataformaSuelo = esSuelo;
    }

    public void afectar(SnowBro s) {
        s.setEstaResbalando(false);
        s.setEnContactoConEscalera(false);
    }

    public void afectar (Enemigo e) {
        if (e != null) {
            e.afectar(this);
        }
    }

    public void afectar(Proyectil p) {
        long tiempoActual = System.currentTimeMillis();
        long tiempoCreacion = p.getTiempoCreacion();
        if (tiempoActual - tiempoCreacion > 50) {
            int pieProyectil = miHitbox.getPosY();
            int techoPlataforma = this.miHitbox.getPosY() + this.miHitbox.getAlto();
            if (pieProyectil <= techoPlataforma + 5) {
                p.eliminar();
            }
        }  
    }
    public void afectar(BolaDeNieve b) {
        //No hace nada
    }

    public void setSkin (Skin s) {

    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void aceptarColision(Colisionador c){
        c.colisionarEstructura(this);
    }

    public boolean bloquearMovimientoHorizontal() {
        return false;
    }

    @Override
    public boolean esSueloSolido() {
        return true;
    }

    public int getVelocidadDeArrastreX() {
        return 0;
    }

    public int getVelocidadDeArrastreY() {
        return 0;
    }
}
