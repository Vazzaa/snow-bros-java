package Juego;

import java.util.List;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Plataforma;
import Entidades.SnowBro.SnowBro;

public class ColisionManager {
    
    // Tolerancia para detectar colisiones "cerca" (útil para detectar suelo)
    private static final int TOLERANCIA_SUELO = 5;

    public ColisionManager(){
    }

    public boolean colisionaAABB(Hitbox h1, Hitbox h2){
        boolean noColisiona = 
        (h1.getPosX() > h2.getPosX() + h2.getAncho() ||   // h1 a la derecha de h2
        h1.getPosX() + h1.getAncho() < h2.getPosX() ||    // h1 a la izquierda de h2
        h1.getPosY() > h2.getPosY() + h2.getAlto() ||    // h1 arriba de h2
        h1.getPosY() + h1.getAlto() < h2.getPosY());      // h1 abajo de h2
    
    return !noColisiona; // Si NO "no colisiona", entonces SÍ colisiona
    }

    public boolean estaEnSuelo(SnowBro s, List<Estructura> estructuras){
        Hitbox hitboxSnowBro = s.miHitbox;
        
        // Crear un hitbox ligeramente extendido hacia abajo para detectar suelo
        Hitbox hitboxDeteccion = new Hitbox(
            hitboxSnowBro.getAncho(),
            hitboxSnowBro.getAlto() + TOLERANCIA_SUELO,
            hitboxSnowBro.getPosX(),
            hitboxSnowBro.getPosY() - TOLERANCIA_SUELO
        );
        // Verificar si alguna estructura está justo debajo
        for (Estructura estructura : estructuras) {
            if (colisionaAABB(hitboxDeteccion, estructura.miHitbox)) {
                // Verificar que realmente está encima (no dentro o al lado)
                int pieSnowBro = hitboxSnowBro.getPosY();
                int techoEstructura = estructura.miHitbox.getPosY() + estructura.miHitbox.getAlto();
                // Si el pie de SnowBro está cerca del techo de la estructura
                if (Math.abs(pieSnowBro - techoEstructura) <= TOLERANCIA_SUELO) {
                    return true;
                }
            }
        }
        return false;
}

    public Estructura colisionaConPlataformaArriba(SnowBro s, List<Estructura> estructuras){
        Hitbox hitboxSnowBro = s.miHitbox;
        
        for (Estructura estructura : estructuras) {
            if (colisionaAABB(hitboxSnowBro, estructura.miHitbox)) {
                // Verificar que está golpeando desde abajo
                int cabezaSnowBro = hitboxSnowBro.getPosY() + hitboxSnowBro.getAlto();
                int sueloEstructura = estructura.miHitbox.getPosY();
                // Si la cabeza de SnowBro está cerca del suelo de la estructura
                if (cabezaSnowBro >= sueloEstructura && 
                    cabezaSnowBro <= sueloEstructura + TOLERANCIA_SUELO) {
                    return estructura;
                }
            }
        }
        return null;
    }

    public Estructura getPlaraformaDebajo(SnowBro s, List<Estructura> estructuras){
        Hitbox hitboxSnowBro = s.miHitbox;
        Estructura plataformaMasCercana = null;
        int distanciaMinima = Integer.MAX_VALUE;
        
        for (Estructura estructura : estructuras) {
            Hitbox hitboxEstructura = estructura.miHitbox;
            
            // Verificar que está debajo (el techo de la estructura está por debajo del pie de SnowBro)
            int pieSnowBro = hitboxSnowBro.getPosY();
            int techoEstructura = hitboxEstructura.getPosY() + hitboxEstructura.getAlto();
            
            if (techoEstructura <= pieSnowBro) {
                // Verificar que hay superposición horizontal
                boolean superponeHorizontal = !(
                    hitboxSnowBro.getPosX() > hitboxEstructura.getPosX() + hitboxEstructura.getAncho() ||
                    hitboxSnowBro.getPosX() + hitboxSnowBro.getAncho() < hitboxEstructura.getPosX()
                );
                
                if (superponeHorizontal) {
                    int distancia = pieSnowBro - techoEstructura;
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        plataformaMasCercana = estructura;
                    }
                }
            }
        }
        return plataformaMasCercana;
    }
}