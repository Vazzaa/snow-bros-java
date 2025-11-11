package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionador;

public class PlatQuebradiza extends Plataforma implements Destructible {

    protected int puntaje;
    protected int Vida;
    private long tiempoParaRomperse = 0;
    private static final long DURACION_ANTES_DE_ROMPERSE = 1000;

    public PlatQuebradiza(Skin s,ModoDeJuego juego ,int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(12);
        miHitbox.setAlto(32);
        esPlataformaSuelo = false;
        puntaje = 150; 
        Vida = 1;
    }

    public void afectar(SnowBro s) {
        if (tiempoParaRomperse == 0) { 
            tiempoParaRomperse = System.currentTimeMillis() + DURACION_ANTES_DE_ROMPERSE;
            s.sumarPuntaje(puntaje);
        }
    }

    public void afectar (Enemigo e) {

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

    public void setSkin(Skin s) {
       
    }

    public int getPuntaje() {
        return puntaje;
    }
    @Override
    public void aceptarColision(Colisionador c) {
        c.colisionarEstructura(this);
    }

    public void destruir() {
        this.miJuego.getNivel().eliminarEstructura(this);
        miJuego.getControladoraGrafica().sacarEntidad(this);
    }

    @Override
    public void actualizar() {
        if (tiempoParaRomperse > 0 && System.currentTimeMillis() >= tiempoParaRomperse) {
            this.destruir();
        }
    }
}
