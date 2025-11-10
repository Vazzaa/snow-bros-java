package Juego;

import Grafica.ControladorGrafica;
import Entidades.Enemigos.Enemigo;
import Entidades.Jugador.Jugador;

import java.util.Random;

public class Supervivencia extends ModoDeJuego {

    private static final int PUNTUACION_OBJETIVO = 50000;
    private static final int ENEMIGOS_POR_OLEADA = 3;
    private static final int MIN_ENEMIGOS = 2;

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
        
        System.out.println("Modo Supervivencia iniciado");
        System.out.println("Objetivo: Alcanzar " + PUNTUACION_OBJETIVO + " puntos");
    }

    @Override
    public void verificarNivelCompletado() {
        if (nivelActual == null)
            return;
        
        int puntajeActual = nivelActual.getSnowBro().getPuntaje();

        if (puntajeActual >= PUNTUACION_OBJETIVO) {
            System.out.println("Objetivo alcanzado.");
            avanzarSiguienteNivel();
            return;
        }

        int enemigosMortales = contarEnemigosMortales();
        
        if (enemigosMortales <= MIN_ENEMIGOS) {
            crearOleadaEnemigos();
        }
    }

    private int contarEnemigosMortales() {
        int cont = 0;
        for (Enemigo e : nivelActual.getMisEnemigos()) {
            if (!e.esInmortal())
                cont++;
        }
        return cont;
    }

    private boolean hayEnemigoInmortal() {
        for (Enemigo e : nivelActual.getMisEnemigos()) {
            if (e.esInmortal())
                return true;
        }
        return false;
    }

    private void crearOleadaEnemigos() {
        numeroOleada++;
        System.out.println("Oleada " + numeroOleada + ". Apareciendo " + ENEMIGOS_POR_OLEADA + " enemigos...");
        
        for (int i = 0; i < ENEMIGOS_POR_OLEADA; i++) {
            crearEnemigoAleatorio();
        }
    }

    private void crearEnemigoAleatorio() {
        if (nivelActual == null || miFabricaEntidades == null) return;
        
        // Posiciones aleatorias dentro del nivel
        // Ajusta estos valores según el tamaño de tu nivel
        int x = 100 + random.nextInt(600); // Entre 100 y 700
        int y = 7600 + random.nextInt(200); // Entre 7600 y 7800
        
        String[] tiposEnemigos;
        if (hayEnemigoInmortal()) {
            tiposEnemigos = new String[]{"demonioRojo", "trollAmarillo", "ranaDeFuego", "moghera"};
        } else {
            tiposEnemigos = new String[]{"demonioRojo", "calabaza", "trollAmarillo", "ranaDeFuego", "moghera"};
        }

        String tipoEnemigo = tiposEnemigos[random.nextInt(tiposEnemigos.length)];
        
        Enemigo nuevoEnemigo = null;
        
        switch (tipoEnemigo) {
            case "demonioRojo":
                nuevoEnemigo = miFabricaEntidades.getDemonioRojo(x, y);
                break;
            case "calabaza":
                nuevoEnemigo = miFabricaEntidades.getCalabaza(x, y);
                break;
            case "trollAmarillo":
                nuevoEnemigo = miFabricaEntidades.getTrollAmarillo(x, y);
                break;
            case "ranaDeFuego":
                nuevoEnemigo = miFabricaEntidades.getRanaDeFuego(x, y);
                break;
            case "moghera":
                //nuevoEnemigo = miFabricaEntidades.getMoghera(x, y); // Lo comenté porque esta muy OP
                break;
        }
        
        if (nuevoEnemigo != null) {
            nivelActual.agregarEnemigos(nuevoEnemigo);
            registrarObserver(nuevoEnemigo);
            System.out.println("Enemigo creado: " + tipoEnemigo + " en (" + x + ", " + y + ")");
        }
    }

    protected void avanzarSiguienteNivel() {
        int puntajeActual = nivelActual.getSnowBro().getPuntaje();
        
        detenerHilos();
        limpiarNivelActual();
        
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
