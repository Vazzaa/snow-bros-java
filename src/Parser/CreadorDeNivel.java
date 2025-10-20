package Parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Jugador.Jugador;
import Entidades.SnowBro.SnowBro;
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

public class CreadorDeNivel {

    protected ModoDeJuego crearNaModo;
    protected FabricaSkin fabSkins;
    private final int ANCHO_TILE = 32;
    private final int ALTO_TILE = 32;

    public CreadorDeNivel(){

    }

    public Nivel leerArchivo(String rutaArchivo){
        List<Estructura> plataformas = new ArrayList<>();
        List<Enemigo> enemigos = new ArrayList<>();
        SnowBro jugador = null;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))){
            String  linea;
            int fila = 0;
            while ((linea = reader.readLine()) != null){
                for (int col = 0; col < linea.length(); col++){
                    char caracter = linea.charAt(col);
                    int posX = col * ANCHO_TILE;
                    int posY = fila * ALTO_TILE;

                    switch (caracter) {
                        case 'P':
                            plataformas.add(new PlatMovil(posX, posY, fabSkins.crearSkinPlatMovil()));
                            break;
                        case 'Q':
                            plataformas.add(new PlatQuebradiza(posX, posY, fabSkins.crearSkinPlatQuebradiza()));
                            break;
                        case 'R':
                            plataformas.add(new SueloResbaladizo(posX, posY, fabSkins.crearSkinSueloResbaladizo()));
                            break;
                        case 'A':
                            plataformas.add(new Pared(posX, posY, fabSkins.crearSkinPared()));
                            break;
                        case 'D':
                            plataformas.add(new ParedDestructible(posX, posY, fabSkins.crearSkinParedDestructible()));
                            break;
                        case 'I':
                            plataformas.add(new Pincho(posX, posY, fabSkins.crearSkinPincho()));
                            break;
                        case 'C':
                            plataformas.add(new Escalera(posX, posY, fabSkins.crearSkinEscalera()));
                            break;
                        case 'J':
                            jugador = new SnowBro(posX, posY, fabSkins.crearSkinSnowBro());
                            break;
                        case 'E':
                            enemigos.add(new DemonioRojo(posX, posY, fabSkins.crearSkinDemonioRojo()));
                            break;
                        case 'T':
                            enemigos.add(new TrollAmarillo(posX, posY, fabSkins.crearSkinTrollAmarillo()));
                            break;
                        case 'G':
                            enemigos.add(new RanaDeFuego(posX, posY, fabSkins.crearSkinRanaDeFuego()));
                            break;
                        case 'B':
                            enemigos.add(new Calabaza(posX, posY, fabSkins.crearSkinCalabaza()));
                            break;
                        case 'M':
                            enemigos.add(new Moghera(posX, posY, fabSkins.crearSkinMoghera()));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            //TODO: crear LevelLoadException
            throw new LevelLoadException("Error al leer el archivo de nivel: " + e.getMessage());
        }
        return new Nivel(plataformas, enemigos, jugador);
    }
    
}
