package Juego;

import Entidades.Jugador.Jugador;
import Grafica.ControladorGrafica;

public class Clasico extends ModoDeJuego {

    private static final int CANTIDAD_NIVELES = 3;

    public Clasico (ControladorGrafica controladorGrafica) {
        super(controladorGrafica);
    }

    @Override
    public void iniciar() {
        jugador = new Jugador(nombreJugador, 0);
        cargarNivel(1, 0);
        controlaGrafica.mostrarPantallaNivel();
        iniciarHilos();
        System.out.println("Modo Clásico iniciado - Nivel 1");
    }

    @Override
    public void verificarNivelCompletado() {
        if (nivelActual != null && nivelActual.estaCompletado()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            avanzarSiguienteNivel();
        }
    }

    public void avanzarSiguienteNivel() {
        int puntajeActual = nivelActual.getSnowBro().getPuntaje();

		detenerHilos();
		limpiarNivelActual();

		int siguienteNivel = numeroNivelActual + 1;

		if(siguienteNivel <= CANTIDAD_NIVELES) {
            String archivoSiguienteNivel = "nivel" + siguienteNivel + ".txt";
            java.io.File archivo = new java.io.File(archivoSiguienteNivel);

		    if(archivo.exists()) {
                cargarNivel(siguienteNivel, puntajeActual);
                iniciarHilos();
                System.out.println("Nivel " + siguienteNivel + " cargado.");
		    } else {
                System.out.println("ERROR: No se encontró el archivo " + archivoSiguienteNivel);
                juegoCompletado();
		    }
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
