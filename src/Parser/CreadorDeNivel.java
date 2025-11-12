package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import Excepciones.LevelLoadException;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Juego.ModoDeJuego;
import Juego.Nivel;
import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;

public class CreadorDeNivel {

    protected ModoDeJuego crearNaModo;
    protected FabricaSkin fabSkins;
    protected FabricaEntidades fabEntidades;
    protected final int ANCHO_TILE = 32;
    protected final int ALTO_TILE = 32;

    public CreadorDeNivel(FabricaSkin fabricaSkins){
        this.fabSkins = fabricaSkins;
    }
    
    public void setFrabricaEntidades(FabricaEntidades fabEntidades){
        this.fabEntidades = fabEntidades;
    }

    public Nivel leerArchivo(String rutaArchivo) {
        List<Estructura> estructuras = new CopyOnWriteArrayList<>();
        List<Enemigo> enemigos = new CopyOnWriteArrayList<>();
        List<PowerUp> powerups = new CopyOnWriteArrayList<>();
        SnowBro jugador = null;
        int numeroNivel = 1;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                try {

                    if (linea.startsWith("NIVEL:")) {
                        numeroNivel = Integer.parseInt(linea.substring(6).trim());

                    } else if (linea.startsWith("JUGADOR:")) {
                        String[] coords = linea.substring(8).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        jugador = fabEntidades.getSnowBro(x, y);

                    } else if (linea.startsWith("PLATAFORMATECHO:")) {
                        String[] coords = linea.substring(16).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        Estructura plataformatecho = fabEntidades.getPlataformaTecho(x, y, numeroNivel);
                        if (plataformatecho != null) {
                            estructuras.add(plataformatecho);
                        }

                    } else if (linea.startsWith("PLATAFORMA:")) {
                        String[] coords = linea.substring(11).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        Estructura plataforma = fabEntidades.getPlataforma(x, y, numeroNivel);
                        if (plataforma != null) {
                            estructuras.add(plataforma);
                        }

                    } else if (linea.startsWith("PLATMOVIL:")) {
                        String[] coords = linea.substring(10).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        Estructura platMovil = fabEntidades.getPlatMovil(x, y);
                        if (platMovil != null) {
                            estructuras.add(platMovil);
                        }

                    } else if (linea.startsWith("PLATQUEBRADIZA:")) {
                        String[] coords = linea.substring(15).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        Estructura platQuebradiza = fabEntidades.getPlatQuebradiza(x, y);
                        if (platQuebradiza != null) {
                            estructuras.add(platQuebradiza);
                        }

                    } else if (linea.startsWith("SUELORESBALADIZO:")) {
                        String[] coords = linea.substring(17).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        Estructura sueloResbaladizo = fabEntidades.getSueloResbaladizo(x, y);
                        if (sueloResbaladizo != null) {
                            estructuras.add(sueloResbaladizo);
                        }
                } else if (linea.startsWith("PARED:")) {
                    String[] coords = linea.substring(6).split(",");
                    int x = Integer.parseInt(coords[0].trim());
                    int y = Integer.parseInt(coords[1].trim());
                    Estructura pared = fabEntidades.getPared(x, y, numeroNivel);
                    if (pared != null) {
                        estructuras.add(pared);
                        System.out.println("DEBUG: Pared agregada - x=" + x + ", tipo=" + (x >= 700 ? "DERECHA" : "IZQUIERDA"));
                    } 
                } else if (linea.startsWith("PAREDDESTRUCTIBLE:")) {
                    String[] coords = linea.substring(18).split(",");
                    int x = Integer.parseInt(coords[0].trim());
                    int y = Integer.parseInt(coords[1].trim());
                    Estructura paredDestructible = fabEntidades.getParedDestructible(x, y);
                    if (paredDestructible != null) {
                        estructuras.add(paredDestructible);
                    }
                } else if (linea.startsWith("PINCHO:")) {
                    String[] coords = linea.substring(7).split(",");
                    int x = Integer.parseInt(coords[0].trim());
                    int y = Integer.parseInt(coords[1].trim());
                    Estructura pincho = fabEntidades.getPincho(x, y);
                    if (pincho != null) {
                        estructuras.add(pincho);
                    }
                }  else if (linea.startsWith("ESCALERA:")) {
                        String[] coords = linea.substring(9).split(",");
                        int x = Integer.parseInt(coords[0].trim());
                        int y = Integer.parseInt(coords[1].trim());
                        Estructura escalera = fabEntidades.getEscalera(x, y);
                        if (escalera != null) {
                            estructuras.add(escalera);
                        }
                        
                    } else if (linea.startsWith("ENEMIGO:")) {
                        String[] partes = linea.substring(8).split(",");
                        if (partes.length >= 3) {
                            String tipo = partes[0].trim();
                            int x = Integer.parseInt(partes[1].trim());
                            int y = Integer.parseInt(partes[2].trim());
                            Enemigo enemigo = crearEnemigo(tipo, x, y);
                            if (enemigo != null) {
                                enemigos.add(enemigo);
                            }
                        }
                        
                    } else if (linea.startsWith("POWERUP:")) {
                        String[] partes = linea.substring(8).split(",");
                        if (partes.length >= 3) {
                            String tipo = partes[0].trim();
                            int x = Integer.parseInt(partes[1].trim());
                            int y = Integer.parseInt(partes[2].trim());
                            PowerUp powerup = crearPowerUp(tipo, x, y);
                            if (powerup != null) {
                                powerups.add(powerup);
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Error parseando números en línea: " + linea);
                    throw new LevelLoadException("Error parseando coordenadas: " + e.getMessage(), e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error en formato de línea: " + linea);
                    throw new LevelLoadException("Formato incorrecto en línea: " + linea, e);
                }
            }

        } catch (IOException e) {
            throw new LevelLoadException("Error al leer el archivo de nivel: " + e.getMessage(), e);
        }

        Nivel devolver = new Nivel(numeroNivel, estructuras, enemigos, jugador, fabEntidades);

        for(PowerUp pu : powerups) {
            devolver.agregarPowerUps(pu);
        }

        return devolver;
    }
    
    private Enemigo crearEnemigo(String tipo, int x, int y) {
        switch (tipo.toLowerCase()) {
            case "demoniorojo":
                return fabEntidades.getDemonioRojo(x, y);
            case "trollamarillo":
                return fabEntidades.getTrollAmarillo(x, y);
            case "ranadefuego":
                return fabEntidades.getRanaDeFuego(x, y);
            case "calabaza":
                return fabEntidades.getCalabaza(x, y);
            case "moghera":
                return fabEntidades.getMoghera(x, y);
            case "fantasma":
                return fabEntidades.getFantasma(x, y);
            case "bomba":
                return fabEntidades.getBomba(x, y);
            case "kamakichi":
                return fabEntidades.getKamakichi(x, y);
            default:
                System.err.println("Tipo de enemigo no reconocido: " + tipo);
                return null;
        }
    }

    private PowerUp crearPowerUp(String tipo, int x, int y){
        switch(tipo.toLowerCase()){
            case "pocionroja":
                return fabEntidades.getPowerUpRojo(x, y);
            case "pocionazul":
                return fabEntidades.getPowerUpAzul(x, y);
            case "pocionverde":
                return fabEntidades.getPowerUpVerde(x, y);
            case "comida":
                return fabEntidades.getFruta(x, y);
            case "vidaextra":
                return fabEntidades.getVidaExtra(x, y);
            default:
                System.err.println("Tipo de powerup no reconocido: " + tipo);
                return null;
        }
    }
}
