package Fabricas;

import java.util.HashMap;
import java.util.Map;

public class FabricaDominio1 extends FabricaSkin {
    protected String rutaCarpetaImagenes;
    public FabricaDominio1() {
        super();
        this.rutaCarpetaImagenes = "Imagenes/SkinsOriginales/";
    }
    private Map<Integer,String> get_mapeo_estado_imagen(String imagen, int cantidad_estados){
        Map<Integer,String> mapeo_estado_imagen = new HashMap<Integer,String>();
        String rutaImagen = rutaCarpetaImagenes + imagen;
        for (int estado = 1; estado <= cantidad_estados; estado++){
            mapeo_estado_imagen.put(estado, rutaImagen + estado + ".png");
        }
        return mapeo_estado_imagen;
    }
    public Skin crearSkinDemonioRojo() {
        return new Skin(get_mapeo_estado_imagen("DemonioRojo", 12), 1);
    }
    public Skin crearSkinTrollAmarillo() {
        return new Skin(get_mapeo_estado_imagen("TrollAmarillo", 9), 1);
    }
    public Skin crearSkinRanaDeFuego() {
        return new Skin(get_mapeo_estado_imagen("RanaDeFuego", 14), 1);
    }
    public Skin crearSkinCalabaza() {
        return new Skin(get_mapeo_estado_imagen("Calabaza", 2), 1);
    }
    public Skin crearSkinFantasma() {
        return new Skin(get_mapeo_estado_imagen("Fantasma", 2), 1);
    }
    public Skin crearSkinMoghera(){
        return new Skin(get_mapeo_estado_imagen("Moghera", 5), 1);
    }
    public Skin crearSkinKamakichi(){
        return new Skin(get_mapeo_estado_imagen("Kamakichi", 7), 1);
    }
    public Skin crearSkinPowerUpAzul(){
        return new Skin(get_mapeo_estado_imagen("PocionAzul", 2), 1);
    }
    public Skin crearSkinPowerUpRojo(){
        return new Skin(get_mapeo_estado_imagen("PocionRojo", 2), 1);
    }
    public Skin crearSkinPowerUpVerde(){
        return new Skin(get_mapeo_estado_imagen("PocionVerde", 2), 1);
    }
    public Skin crearSkinFruta(){
        return new Skin(get_mapeo_estado_imagen("Comida", 12), 1);
    }
    public Skin crearSkinVidaExtra(){
        return new Skin(get_mapeo_estado_imagen("VidaExtra", 1), 1);
    }
    public Skin crearSkinSnowBro(){
        return new Skin(get_mapeo_estado_imagen("SnowBro", 26), 1);
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
<<<<<<< HEAD
        return new Skin(get_mapeo_estado_imagen("pared1", 0), 1);//Falta
=======
        Map<Integer,String> mapeo = new HashMap<>();
        mapeo.put(1, rutaCarpetaImagenes + "Pared1.png");
        return new Skin(mapeo, 1);
>>>>>>> d63b739f00833b43b1d06313da78c4231e049d91
    }
    public Skin crearSkinSueloResbaladizo(){
        return new Skin(get_mapeo_estado_imagen("Resbaladizo", 1), 1);//Falta
    }
    public Skin crearSkinPlataforma(){
        Map<Integer,String> mapeoparaplataforma= new HashMap<Integer,String>();
        mapeoparaplataforma.put(1,"Imagenes/SkinsOriginales/plataforma1.png");
        return new Skin(mapeoparaplataforma, 1);
    }
    public Skin crearSkinPlatQuebradiza(){
        return new Skin(get_mapeo_estado_imagen("PlatQuebradiza", 1), 1);//Falta
    }
    public Skin crearSkinPlatMovil(){
        return new Skin(get_mapeo_estado_imagen("Plataforma", 1), 1);
    }
    public Skin crearSkinProyectilBomba(){
        return new Skin(get_mapeo_estado_imagen("ProyectilBomba", 0), 1);//Falta
    }
    public Skin crearSkinProyectilFuego(){
        return new Skin(get_mapeo_estado_imagen("ProyectilFuego", 2), 1);
    }
    public Skin crearSkinProyectilNieve(){
        return new Skin(get_mapeo_estado_imagen("ProyectilNieve", 4), 1);
    }
    public Skin crearSkinBolaDeNieve(){
        return new Skin(get_mapeo_estado_imagen("BolaDeNieve", 10), 1);
    }
    public Skin crearSkinParedDestructible() {
        return new Skin(get_mapeo_estado_imagen("ParedDestructible", 0), 1);//Falta
    }
}
