package Parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Jugador.Jugador;
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

public class CreadorDeNivel {

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

public Nivel crearNivelHarcodeando(){
    List<Estructura> plataformas = new ArrayList<>();
    List<Enemigo> enemigos = new ArrayList<>();
    SnowBro jugador = fabEntidades.getSnowBro(10, 7650);

    //plataformas.add(fabEntidades.getPlatMovil(100, 400));
    //plataformas.add(fabEntidades.getPlatQuebradiza(200, 7100));
    //plataformas.add(fabEntidades.getSueloResbaladizo(0, 500));
    //plataformas.add(fabEntidades.getPared(0, 0));
    //plataformas.add(fabEntidades.getPincho(150, 480));
    //plataformas.add(fabEntidades.getEscalera(300, 450));
    /*Estructuraataforma = fabEntidades.getPlataforma(300, 7680);
    plataformas.add(plataforma);
    System.out.println("PLATAFORMA CREADA - X: " + plataforma.getPosX() + ", Y: " + plataforma.getPosY());
    System.out.println("PLATAFORMA SKIN: " + plataforma.getSkin().getRutaImagenActual());*/

    enemigos.add(fabEntidades.getDemonioRojo(100, 7650));
    enemigos.add(fabEntidades.getTrollAmarillo(200, 7650));
    enemigos.add(fabEntidades.getRanaDeFuego(300, 7650));
    enemigos.add(fabEntidades.getCalabaza(400, 7650));
    enemigos.add(fabEntidades.getMoghera(500, 7650));

    return new Nivel(1, plataformas, enemigos, jugador, fabEntidades);
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
                            plataformas.add(fabEntidades.getPlatMovil(posX, posY));
                            break;
                        case 'L':
                            //plataformas.add(fabEntidades.getPlataforma(posX, posY));//Flata
                            break;
                        case 'Q':
                            plataformas.add(fabEntidades.getPlatQuebradiza(posX, posY));
                            break;
                        case 'R':
                            plataformas.add(fabEntidades.getSueloResbaladizo(posX, posY));
                            break;
                        case 'A':
                            plataformas.add(fabEntidades.getPared(posX, posY));
                            break;
                        case 'D':
                            //plataformas.add(fabEntidades.getParedDestructible(posX, posY));//Falta
                            break;
                        case 'I':
                            plataformas.add(fabEntidades.getPincho(posX, posY));
                            break;
                        case 'C':
                            plataformas.add(fabEntidades.getEscalera(posX, posY));
                            break;
                        case 'J':
                            jugador = fabEntidades.getSnowBro(posX, posY);
                            break;
                        case 'E':
                            enemigos.add(fabEntidades.getDemonioRojo(posX, posY));
                            break;
                        case 'T':
                            enemigos.add(fabEntidades.getTrollAmarillo(posX, posY));
                            break;
                        case 'G':
                            enemigos.add(fabEntidades.getRanaDeFuego(posX, posY));
                            break;
                        case 'B':
                            enemigos.add(fabEntidades.getCalabaza(posX, posY));
                            break;
                        case 'M':
                            enemigos.add(fabEntidades.getMoghera(posX, posY));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            //TODO: crear LevelLoadException
            throw new LevelLoadException("Error al leer el archivo de nivel. ", e);
        }
        return new Nivel(numeroNivel, plataformas, enemigos, jugador, fabEntidades);
    }
}
