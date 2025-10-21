package Parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Jugador.Jugador;
import Entidades.SnowBro.SnowBro;
import Excepciones.LevelLoadException;
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

    public CreadorDeNivel(FabricaSkin fabricaSkins){
        this.fabSkins = fabricaSkins;

    }

    public Nivel leerArchivo(String rutaArchivo){
        List<Estructura> plataformas = new ArrayList<>();
        List<Enemigo> enemigos = new ArrayList<>();
        SnowBro jugador = null;
        int numeroNivel= 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))){
            String  linea;
            int fila = 0;
            while ((linea = reader.readLine()) != null){
                for (int col = 0; col < linea.length(); col++){
                    char caracter = linea.charAt(col);
                    int posX = col * ANCHO_TILE;
                    int posY = fila * ALTO_TILE;
                    switch (caracter) {
                        case '1':
                            numeroNivel = 1;
                            break;
                        case '2':
                            numeroNivel = 2;
                            break;
                        case 'P':
                            plataformas.add(new PlatMovil(fabSkins.crearSkinPlatMovil(), posX, posY));
                            break;
                        case 'L':
                            plataformas.add(new Plataforma(fabSkins.crearSkinPlataforma(), posX, posY));
                            break;
                        case 'Q':
                            plataformas.add(new PlatQuebradiza(fabSkins.crearSkinPlatQuebradiza(), posX, posY ));
                            break;
                        case 'R':
                            plataformas.add(new SueloResbaladizo(fabSkins.crearSkinSueloResbaladizo(), posX, posY));
                            break;
                        case 'A':
                            plataformas.add(new Pared(fabSkins.crearSkinPared(), posX, posY));
                            break;
                        case 'D':
                            plataformas.add(new ParedDestructible(fabSkins.crearSkinParedDestructible(), posX, posY));
                            break;
                        case 'I':
                            plataformas.add(new Pincho(fabSkins.crearSkinPincho(), posX, posY));
                            break;
                        case 'C':
                            plataformas.add(new Escalera(fabSkins.crearSkinEscalera(), posX, posY));
                            break;
                        case 'J':
                            jugador = new SnowBro(fabSkins.crearSkinSnowBro(), posX, posY, new Jugador("Jorge", 0));
                            break;
                        case 'E':
                            enemigos.add(new DemonioRojo(fabSkins.crearSkinDemonioRojo(), posX, posY));
                            break;
                        case 'T':
                            enemigos.add(new TrollAmarillo(fabSkins.crearSkinTrollAmarillo(), posX, posY));
                            break;
                        case 'G':
                            enemigos.add(new RanaDeFuego(fabSkins.crearSkinRanaDeFuego(), posX, posY));
                            break;
                        case 'B':
                            enemigos.add(new Calabaza(fabSkins.crearSkinCalabaza(), posX, posY));
                            break;
                        case 'M':
                            enemigos.add(new Moghera(fabSkins.crearSkinMoghera(), posX, posY));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            //TODO: crear LevelLoadException
            throw new LevelLoadException("Error al leer el archivo de nivel. ", e);
        }
        return new Nivel(numeroNivel, plataformas, enemigos, jugador);
    }
}
