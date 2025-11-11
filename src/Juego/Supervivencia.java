package Juego;

import Grafica.ControladorGrafica;
import Entidades.Enemigos.Enemigo;
import Entidades.Jugador.Jugador;

import java.util.Random;

public class Supervivencia extends ModoDeJuego {

    private static final int PUNTUACION_OBJETIVO = 50000;
    private static final int ENEMIGOS_POR_OLEADA = 3;
    private static final long TIEMPO_ENTRE_OLEADAS = 10000;

    protected boolean aparecioCalabaza;
    protected boolean aparecioMoghera;

    protected long tiempoInicio;
    protected long tiempoRestante;

    protected int numeroOleada;
    protected Random random;

    public Supervivencia(ControladorGrafica controladorGrafica) {
        super(controladorGrafica);
        numeroOleada = 1;
        random = new Random();
    }

    @Override
    public void iniciar() {
        jugador = new Jugador(nombreJugador, 0);
        
        cargarNivel(1, 0);
        
        controlaGrafica.mostrarPantallaNivel();
        iniciarHilos();

        aparecioMoghera = false;

        tiempoInicio = System.currentTimeMillis();
        tiempoRestante = TIEMPO_ENTRE_OLEADAS;
        
        System.out.println("Modo Supervivencia iniciado");
        System.out.println("Objetivo: Alcanzar " + PUNTUACION_OBJETIVO + " puntos");
    }

    @Override
    public void verificarNivelCompletado() {
        if (nivelActual != null) {
            int puntajeActual = nivelActual.getSnowBro().getPuntaje();

            if (puntajeActual >= PUNTUACION_OBJETIVO) {
                System.out.println("Objetivo alcanzado.");
                avanzarSiguienteNivel();
            }
            else {
                actualizarTiempo();
            }
        }
    }

    private void crearOleadaEnemigos() {
        numeroOleada++;
        System.out.println("Oleada " + numeroOleada + ". Apareciendo " + ENEMIGOS_POR_OLEADA + " enemigos...");
        
        for (int i = 0; i < ENEMIGOS_POR_OLEADA; i++) {
            crearEnemigoAleatorio();
        }
    }

    private void actualizarTiempo() {
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
        tiempoRestante = TIEMPO_ENTRE_OLEADAS - tiempoTranscurrido;
        
        if (tiempoRestante <= 0) {
            tiempoRestante = 0;
            crearOleadaEnemigos();

            tiempoInicio = System.currentTimeMillis();
            tiempoRestante = TIEMPO_ENTRE_OLEADAS;
        }
    }

    private void crearEnemigoAleatorio() {
        if (nivelActual != null && miFabricaEntidades != null) {
            
            // Posiciones aleatorias dentro del nivel
            // Ajusta estos valores según el tamaño de tu nivel
            int x = 100 + random.nextInt(600); // Entre 100 y 700
            int y = 7600 + random.nextInt(200); // Entre 7600 y 7800
            
            String[] tiposEnemigos = {"demonioRojo", "calabaza", "trollAmarillo", "ranaDeFuego", "moghera"};
            String tipoEnemigo = tiposEnemigos[random.nextInt(tiposEnemigos.length)];
            
            Enemigo nuevoEnemigo = null;
        
            switch (tipoEnemigo) {
                case "demonioRojo":
                    nuevoEnemigo = miFabricaEntidades.getDemonioRojo(x, y);
                    break;
                case "calabaza":
                    if (!aparecioCalabaza) {
                        nuevoEnemigo = miFabricaEntidades.getCalabaza(x, y);
                        aparecioCalabaza = true;
                    }
                    break;
                case "trollAmarillo":
                    nuevoEnemigo = miFabricaEntidades.getTrollAmarillo(x, y);
                    break;
                case "ranaDeFuego":
                    nuevoEnemigo = miFabricaEntidades.getRanaDeFuego(x, y);
                    break;
                case "moghera":
                    if (!aparecioMoghera) {
                        // nuevoEnemigo = miFabricaEntidades.getMoghera(x, y);
                        // pls nerf moghera :(
                        aparecioMoghera = true;
                    }
                    break;
            }
        
            if (nuevoEnemigo != null) {
                nivelActual.agregarEnemigos(nuevoEnemigo);
                registrarObserver(nuevoEnemigo);
                System.out.println("Enemigo creado: " + tipoEnemigo + " en (" + x + ", " + y + ")");
            }
        }
    }

    protected void avanzarSiguienteNivel() {
        int puntajeActual = nivelActual.getSnowBro().getPuntaje();
        
        detenerHilos();
        limpiarNivelActual();
        aparecioMoghera = false;

        int siguienteNivel = numeroNivelActual + 1;
        
        String archivoSiguienteNivel = "nivel" + siguienteNivel + ".txt";
        java.io.File archivo = new java.io.File(archivoSiguienteNivel);
            
        if (archivo.exists()) {
            System.out.println("Cargando nivel " + siguienteNivel + "...");
            cargarNivel(siguienteNivel, puntajeActual);
            iniciarHilos();
        } else {
            System.out.println("No hay mas niveles. Fin.");
            juegoCompletado();
        }
    }

    @Override
    public void juegoCompletado() {
        detenerHilos();
        controlaGrafica.mostrarPantallaGameOver();
    }
}
