package Parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Jugador.Jugador;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import Excepciones.LevelLoadException;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Juego.ModoDeJuego;
import Juego.Nivel;
import Entidades.Enemigos.Calabaza;
import Entidades.Enemigos.DemonioRojo;
import Entidades.Enemigos.Enemigo;
import Entidades.Enemigos.Moghera;
import Entidades.Enemigos.RanaDeFuego;
import Entidades.Enemigos.TrollAmarillo;
import Entidades.Estructuras.Escalera;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Pared;
import Entidades.Estructuras.ParedDestructible;
import Entidades.Estructuras.Pincho;
import Entidades.Estructuras.PlatMovil;
import Entidades.Estructuras.PlatQuebradiza;
import Entidades.Estructuras.Plataforma;
import Entidades.Estructuras.SueloResbaladizo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CreadorDeNivel {
    // Clases POJO para deserialización JSON con Gson
    public static class NivelData {
        public int nivel;
        public PosicionData jugador;
        public List<EntidadData> estructuras;
        public List<EntidadData> enemigos;
        public List<EntidadData> powerups;
        public List<EntidadData> obstaculos;
    }
    
    public static class PosicionData {
        public int x;
        public int y;
    }
    
    public static class EntidadData {
        public String tipo;
        public int x;
        public int y;
    }

    protected ModoDeJuego crearNaModo;
    protected FabricaSkin fabSkins;
    protected FabricaEntidades fabEntidades;
    private final int ANCHO_TILE = 32;
    private final int ALTO_TILE = 32;

    public CreadorDeNivel(FabricaSkin fabricaSkins){
        this.fabSkins = fabricaSkins;

    }
    public void setFrabricaEntidades(FabricaEntidades fabEntidades){
        this.fabEntidades = fabEntidades;
    }

       /*  public Nivel crearNivelHarcodeando(){
            List<Estructura> plataformas = new ArrayList<>();
            List<Enemigo> enemigos = new ArrayList<>();
            SnowBro jugador = fabEntidades.getSnowBro(10, 7650);

            //plataformas.add(fabEntidades.getPlatMovil(100, 400));
            //plataformas.add(fabEntidades.getPlatQuebradiza(200, 7100));
            //plataformas.add(fabEntidades.getSueloResbaladizo(0, 500));
            //plataformas.add(fabEntidades.getPared(0, 0));
            //plataformas.add(fabEntidades.getPincho(150, 480));
            //plataformas.add(fabEntidades.getEscalera(300, 450));
            for(int i=0; i<800; i+=16){
            plataformas.add(fabEntidades.getPlataforma(i, 7620));
        }


            //plat un poco mas arriba
            plataformas.add(fabEntidades.getPlataforma(112, 7670));
            plataformas.add(fabEntidades.getPlataforma(128, 7670));
            plataformas.add(fabEntidades.getPlataforma(144, 7670));
            plataformas.add(fabEntidades.getPlataforma(160, 7670));
            plataformas.add(fabEntidades.getPlataforma(176, 7670));
            plataformas.add(fabEntidades.getPlataforma(192, 7670));
            plataformas.add(fabEntidades.getPlataforma(208, 7670));
            plataformas.add(fabEntidades.getPlataforma(224, 7670));
            plataformas.add(fabEntidades.getPlataforma(240, 7670));

            

            enemigos.add(fabEntidades.getDemonioRojo(100, 7650));
            enemigos.add(fabEntidades.getDemonioRojo(176, 7700));
            enemigos.add(fabEntidades.getTrollAmarillo(200, 7650));
            enemigos.add(fabEntidades.getRanaDeFuego(300, 7650));
            enemigos.add(fabEntidades.getCalabaza(400, 7650));
            enemigos.add(fabEntidades.getMoghera(500, 7650));

            return new Nivel(1, plataformas, enemigos, jugador, fabEntidades);
        }*/

    public Nivel leerArchivo(String rutaArchivo) {
        List<Estructura> plataformas = new ArrayList<>();
        List<Enemigo> enemigos = new ArrayList<>();
        List<PowerUp> powerups = new ArrayList<>();
        SnowBro jugador = null;
        int numeroNivel = 0;
        
        try (FileReader reader = new FileReader(rutaArchivo)) {
            Gson gson = new Gson();
            NivelData nivelData = gson.fromJson(reader, NivelData.class);
            
            numeroNivel = nivelData.nivel;
            
            // Crear el jugador
            if (nivelData.jugador != null) {
                jugador = fabEntidades.getSnowBro(nivelData.jugador.x, nivelData.jugador.y);
            }
            
            // Crear estructuras
            if (nivelData.estructuras != null) {
                for (EntidadData estructura : nivelData.estructuras) {
                    Estructura nuevaEstructura = crearEstructura(estructura);
                    if (nuevaEstructura != null) {
                        plataformas.add(nuevaEstructura);
                    }
                }
            }
            
            // Crear enemigos
            if (nivelData.enemigos != null) {
                for (EntidadData enemigo : nivelData.enemigos) {
                    Enemigo nuevoEnemigo = crearEnemigo(enemigo);
                    if (nuevoEnemigo != null) {
                        enemigos.add(nuevoEnemigo);
                    }
                }
            }

            if(nivelData.powerups != null){
                for(EntidadData powerup : nivelData.powerups){
                    PowerUp nuevoPowerUp = crearPowerUp(powerup);
                    if(nuevoPowerUp != null){
                        powerups.add(nuevoPowerUp);
                    }
                }
            }
            
        } catch (IOException e) {
            throw new LevelLoadException("Error al leer el archivo JSON de nivel: " + e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            throw new LevelLoadException("Error de sintaxis en el archivo JSON: " + e.getMessage(), e);
        }
        
        Nivel devolver = new Nivel(numeroNivel, plataformas, enemigos, jugador, fabEntidades);
        for(PowerUp pu : powerups){
            devolver.agregarPowerUps(pu);
        }
        return devolver;
    }
    
    private Estructura crearEstructura(EntidadData data) {
        switch (data.tipo.toLowerCase()) {
            case "plataforma":
                return fabEntidades.getPlataforma(data.x, data.y);
            case "platmovil":
                return fabEntidades.getPlatMovil(data.x, data.y);
            case "platquebradiza":
                return fabEntidades.getPlatQuebradiza(data.x, data.y);
            case "sueloresbaladizo":
                return fabEntidades.getSueloResbaladizo(data.x, data.y);
            case "pared":
                return fabEntidades.getPared(data.x, data.y);
            case "pareddestructible":
                // TODO: Implementar cuando esté disponible en FabricaEntidades
                System.err.println("ParedDestructible no implementada aún");
                return null;
            case "pincho":
                return fabEntidades.getPincho(data.x, data.y);
            case "escalera":
                return fabEntidades.getEscalera(data.x, data.y);
            default:
                System.err.println("Tipo de estructura no reconocido: " + data.tipo);
                return null;
        }
    }
    
    private Enemigo crearEnemigo(EntidadData data) {
        switch (data.tipo.toLowerCase()) {
            case "demoniorojo":
                return fabEntidades.getDemonioRojo(data.x, data.y);
            case "trollamarillo":
                return fabEntidades.getTrollAmarillo(data.x, data.y);
            case "ranadefuego":
                return fabEntidades.getRanaDeFuego(data.x, data.y);
            case "calabaza":
                return fabEntidades.getCalabaza(data.x, data.y);
            case "moghera":
                return fabEntidades.getMoghera(data.x, data.y);
            case "fantasma":
                return fabEntidades.getFantasma(data.x, data.y);
            default:
                System.err.println("Tipo de enemigo no reconocido: " + data.tipo);
                return null;
        }
    }

    private PowerUp crearPowerUp(EntidadData data){
        switch(data.tipo.toLowerCase()){
            case "pocionroja":
                return fabEntidades.getPowerUpRojo(data.x, data.y);
            case "pocionazul":
                return fabEntidades.getPowerUpAzul(data.x, data.y);
            case "pocionverde":
                return fabEntidades.getPowerUpVerde(data.x, data.y);
            case "comida":
                return fabEntidades.getFruta(data.x, data.y);
            case "vidaextra":
                return fabEntidades.getVidaExtra(data.x, data.y);
            default:
                System.err.println("Tipo de powerup no reconocido: " + data.tipo);
                return null;
        }
    }
}
