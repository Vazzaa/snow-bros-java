package Entidades.Estructuras;

import Entidades.Enemigos.Enemigo;
import Entidades.SnowBro.SnowBro;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import EstadoMovimiento.EnemigoQuieto;
import EstadoMovimiento.EnemigoSubiendoEscalera;

public class Escalera extends Obstaculo{

    public Escalera(Skin s, ModoDeJuego juego,int x, int y) {
        super(s, juego, x, y);
        miHitbox.setAncho(16);
        miHitbox.setAlto(16);
    }

    public void afectar(SnowBro s) {
        s.setEnContactoConEscalera(true);
    }

    public void afectar (Enemigo e) {
        if (e != null) {
            e.setEnContactoConEscalera(true);
            
            if (e.getTiempoDecisionEscalera() == 0 || 
                (System.currentTimeMillis() - e.getTiempoDecisionEscalera() >= Enemigo.getTiempoEsperaDecision())) {
                
                int decision = (int)(Math.random() * 2) + 1;
                
                if (decision == 2) {
                    e.setDebeSubirEscalera(true);
                    e.cambiarEstadoMovimiento(new EnemigoSubiendoEscalera(e.getEstadoMovimiento()));
                } else {
                    e.setDebeSubirEscalera(false);
                    e.cambiarEstadoMovimiento(new EnemigoQuieto(e.getEstadoMovimiento()));
                }
                
                e.setTiempoDecisionEscalera(System.currentTimeMillis());
            }
        }
    }

    public void setSkin (Skin s) {
        //Lo hace la fabrica
    }
    @Override
    
    public Skin getSkin() {
        return misAspectos;
    }

    public boolean bloquearMovimientoHorizontal() {
        return false; 
    }

    public boolean esSueloSolido() {
        return false;
    }
}
