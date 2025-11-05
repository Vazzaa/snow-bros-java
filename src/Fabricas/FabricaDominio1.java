package Fabricas;

import java.util.HashMap;
import java.util.Map;

public class FabricaDominio1 extends FabricaSkin {

    protected String rutaCarpetaImagenes;

    public FabricaDominio1() {
        super();
        this.rutaCarpetaImagenes = "Imagenes/SkinsOriginales/";
    }

    private Map<Integer,String> getMapeoEstadoImagen(String imagen, int cantidadEstados){
        Map<Integer,String> mapeoEstadoImagen = new HashMap<Integer,String>();
        String rutaImagen = rutaCarpetaImagenes + imagen;
        for (int estado = 1; estado <= cantidadEstados; estado++){
            mapeoEstadoImagen.put(estado, rutaImagen + estado + ".png");
        }
        return mapeoEstadoImagen;
    }

    public Skin crearSkinDemonioRojo() {
        return new Skin(getMapeoEstadoImagen("DemonioRojo", 12), 1);
    }

    public Skin crearSkinTrollAmarillo() {
        return new Skin(getMapeoEstadoImagen("TrollAmarillo", 9), 1);
    }

    public Skin crearSkinRanaDeFuego() {
        return new Skin(getMapeoEstadoImagen("RanaDeFuego", 14), 1);
    }

    public Skin crearSkinCalabaza() {
        return new Skin(getMapeoEstadoImagen("Calabaza", 2), 1);
    }

    public Skin crearSkinFantasma() {
        return new Skin(getMapeoEstadoImagen("Fantasma", 2), 1);
    }

    public Skin crearSkinMoghera(){
        return new Skin(getMapeoEstadoImagen("Moghera", 5), 4);
    }

    public Skin crearSkinKamakichi(){
        return new Skin(getMapeoEstadoImagen("Kamakichi", 7), 1);
    }

    public Skin crearSkinPowerUpAzul(){
        return new Skin(getMapeoEstadoImagen("PocionAzul", 2), 1);
    }

    public Skin crearSkinPowerUpRojo(){
        return new Skin(getMapeoEstadoImagen("PocionRojo", 2), 1);
    }

    public Skin crearSkinPowerUpVerde(){
        return new Skin(getMapeoEstadoImagen("PocionVerde", 2), 1);
    }

    public Skin crearSkinFruta(){
        return new Skin(getMapeoEstadoImagen("Comida", 12), 1);
    }

    public Skin crearSkinVidaExtra(){
        return new Skin(getMapeoEstadoImagen("VidaExtra", 1), 1);
    }

    public Skin crearSkinSnowBro(){
        return new Skin(getMapeoEstadoImagen("SnowBro", 26), 1);
    }

    public Skin crearSkinPincho(){
        Map<Integer,String> mapeo = new HashMap<>();
        mapeo.put(1, rutaCarpetaImagenes + "Pincho.png");
        return new Skin(mapeo, 1);
    }

    public Skin crearSkinEscalera(){
        Map<Integer,String> mapeo = new HashMap<>();
        mapeo.put(1, rutaCarpetaImagenes + "escalera.png");
        return new Skin(mapeo, 1);
    }

    public Skin crearSkinPared(){
        Map<Integer,String> mapeo = new HashMap<>();
        mapeo.put(1, rutaCarpetaImagenes + "Pared1.png");
        return new Skin(mapeo, 1);
    }

    public Skin crearSkinSueloResbaladizo(){
        return new Skin(getMapeoEstadoImagen("Resbaladizo", 1), 1);//Falta
    }

    public Skin crearSkinPlataforma(){
        Map<Integer,String> mapeoparaplataforma= new HashMap<Integer,String>();
        mapeoparaplataforma.put(1,"Imagenes/SkinsOriginales/plataforma1.png");
        return new Skin(mapeoparaplataforma, 1);
    }

    public Skin crearSkinPlatQuebradiza(){
        return new Skin(getMapeoEstadoImagen("PlatQuebradiza", 1), 1);//Falta
    }

    public Skin crearSkinPlatMovil(){
        return new Skin(getMapeoEstadoImagen("Plataforma", 1), 1);
    }

    public Skin crearSkinProyectilBomba(){
        return new Skin(getMapeoEstadoImagen("ProyectilBomba", 0), 1);//Falta
    }

    public Skin crearSkinProyectilFuego(){
        return new Skin(getMapeoEstadoImagen("ProyectilFuego", 2), 1);
    }

    public Skin crearSkinProyectilNieve(){
        return new Skin(getMapeoEstadoImagen("ProyectilNieve", 4), 1);
    }

    public Skin crearSkinBolaDeNieve(){
        return new Skin(getMapeoEstadoImagen("BolaDeNieve", 10), 1);
    }

    public Skin crearSkinParedDestructible(){
        return new Skin(getMapeoEstadoImagen("ParedDestructible", 0), 1);//Falta
    }
}
