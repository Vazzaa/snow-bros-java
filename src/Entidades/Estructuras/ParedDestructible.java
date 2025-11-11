package Entidades.Estructuras;
import Entidades.Enemigos.Enemigo;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.Proyectiles.Proyectil;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;

public class ParedDestructible extends Pared implements Destructible {

    protected int Vida;
    protected int puntaje;

    public ParedDestructible(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y);
        Vida = 1;
        puntaje = 150;
    }

    public void destruir() {
        this.miJuego.getNivel().eliminarEstructura(this);
        miJuego.getControladoraGrafica().sacarEntidad(this);
    }

    public void afectar(SnowBro s) {
        s.sumarPuntaje(puntaje);
    }

    public void afectar (Enemigo e) {
        //No afecta a enemigos
    }

    public void afectar(Proyectil p) {
        long tiempoActual = System.currentTimeMillis();
        long tiempoCreacion = p.getTiempoCreacion();
        if (tiempoActual - tiempoCreacion > 50) {
            int pieProyectil = miHitbox.getPosY();
            int techoPared = this.miHitbox.getPosY() + this.miHitbox.getAlto();
            if (pieProyectil <= techoPared + 5) {
                Vida -= 1;
                if (Vida <= 0) {
                    this.destruir();
                }
                p.eliminar();
            }
        }  
    }
    public void afectar(BolaDeNieve b) {
        this.destruir();
        b.eliminar();
    }

    public void setSkin(Skin s) {

    }

    public int getPuntaje() {
        return puntaje;
    }

}
