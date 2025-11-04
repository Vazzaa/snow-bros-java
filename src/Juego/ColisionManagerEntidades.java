package Juego;

import java.util.List;

import Entidades.Estructuras.Estructura;

public class ColisionManagerEntidades {
    
    private static final int TOLERANCIA_SUELO = 5;

    public ColisionManagerEntidades(){
        
    }

    public boolean colisionaAABB(Hitbox h1, Hitbox h2){
        boolean noColisiona = 
        (h1.getPosX() > h2.getPosX() + h2.getAncho() ||
        h1.getPosX() + h1.getAncho() < h2.getPosX() ||
        h1.getPosY() > h2.getPosY() + h2.getAlto() ||
        h1.getPosY() + h1.getAlto() < h2.getPosY());
    
        return !noColisiona;
    }

    public boolean estaEnSuelo(Entidad entidad, List<Estructura> estructuras){
        Hitbox hitboxEntidad = entidad.miHitbox;
        
        Hitbox hitboxDeteccion = new Hitbox(
            hitboxEntidad.getAncho(),
            hitboxEntidad.getAlto() + TOLERANCIA_SUELO,
            hitboxEntidad.getPosX(),
            hitboxEntidad.getPosY() - TOLERANCIA_SUELO
        );
        
        for (Estructura estructura : estructuras) {
            if (colisionaAABB(hitboxDeteccion, estructura.miHitbox)) {
                int pieEntidad = hitboxEntidad.getPosY();
                int techoEstructura = estructura.miHitbox.getPosY() + estructura.miHitbox.getAlto();
                if (Math.abs(pieEntidad - techoEstructura) <= TOLERANCIA_SUELO) {
                    return true;
                }
            }
        }
        
        return false;
}


    public Estructura getPlataformaDebajo(Entidad s, List<Estructura> estructuras){
        Hitbox hitboxEntidad = s.miHitbox;
        Estructura plataformaMasCercana = null;
        int distanciaMinima = Integer.MAX_VALUE;
        
        for (Estructura estructura : estructuras) {
            Hitbox hitboxEstructura = estructura.miHitbox;
            
            int pieEntidad = hitboxEntidad.getPosY();
            int techoEstructura = hitboxEstructura.getPosY() + hitboxEstructura.getAlto();
            
            if (techoEstructura <= pieEntidad) {
                boolean superponeHorizontal = !(
                    hitboxEntidad.getPosX() > hitboxEstructura.getPosX() + hitboxEstructura.getAncho() ||
                    hitboxEntidad.getPosX() + hitboxEntidad.getAncho() < hitboxEstructura.getPosX()
                );
                
                if (superponeHorizontal) {
                    int distancia = pieEntidad - techoEstructura;
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

        