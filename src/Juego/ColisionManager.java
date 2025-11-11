package Juego;

import java.util.List;

import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;

public class ColisionManager {
    
    private static final int TOLERANCIA_SUELO = 5;

    public ColisionManager(){
        
    }

    public boolean colisionaAABB(Hitbox h1, Hitbox h2){
        boolean noColisiona = 
        (h1.getPosX() > h2.getPosX() + h2.getAncho() ||
        h1.getPosX() + h1.getAncho() < h2.getPosX() ||
        h1.getPosY() > h2.getPosY() + h2.getAlto() ||
        h1.getPosY() + h1.getAlto() < h2.getPosY());
    
        return !noColisiona;
    }

    public boolean estaEnSuelo(SnowBro s, List<Estructura> estructuras){
        Hitbox hitboxSnowBro = s.miHitbox;
        
        Hitbox hitboxDeteccion = new Hitbox(
            hitboxSnowBro.getAncho(),
            hitboxSnowBro.getAlto() + TOLERANCIA_SUELO,
            hitboxSnowBro.getPosX(),
            hitboxSnowBro.getPosY() - TOLERANCIA_SUELO
        );
        
        for (Estructura estructura : estructuras) {
            if (colisionaAABB(hitboxDeteccion, estructura.miHitbox)) {
                if (estructura.esSueloSolido() && colisionaAABB(hitboxDeteccion, estructura.miHitbox)) {
                    int pieSnowBro = hitboxSnowBro.getPosY();
                    int techoEstructura = estructura.miHitbox.getPosY() + estructura.miHitbox.getAlto();
                    if (Math.abs(pieSnowBro - techoEstructura) <= TOLERANCIA_SUELO) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public Estructura getPlataformaDebajo(SnowBro s, List<Estructura> estructuras){
        Hitbox hitboxSnowBro = s.miHitbox;
        Estructura plataformaMasCercana = null;
        int distanciaMinima = Integer.MAX_VALUE;
        
        for (Estructura estructura : estructuras) {
            Hitbox hitboxEstructura = estructura.miHitbox;
            
            int pieSnowBro = hitboxSnowBro.getPosY();
            int techoEstructura = hitboxEstructura.getPosY() + hitboxEstructura.getAlto();
            
            if (techoEstructura <= pieSnowBro) {
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