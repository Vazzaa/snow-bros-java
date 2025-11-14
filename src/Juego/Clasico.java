package Juego;

import Entidades.Jugador.Jugador;
import Grafica.ControladorGrafica;
import Sonidos.GestorSonidos;

public class Clasico extends ModoDeJuego {

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

        String archivoSiguienteNivel = "nivel" + siguienteNivel + ".txt";
        java.io.File archivo = new java.io.File(archivoSiguienteNivel);

		if(archivo.exists()) {
            GestorSonidos.getInstancia().reproducirEfecto("level_up");
            System.out.println("Nivel " + siguienteNivel + " cargado.");
            cargarNivel(siguienteNivel, puntajeActual);
            iniciarHilos();
            if (numeroNivelActual == 3 || numeroNivelActual == 6)
                GestorSonidos.getInstancia().reproducirEfecto("bossintro");
		} else {
            System.out.println("No hay mas niveles. Fin.");
            nivelActual.getSnowBro().sumarPuntaje(puntajeActual);
            juegoCompletado();
		}
    }

    @Override
    public void juegoCompletado() {
        detenerHilos();
        Jugador jugadorFinal= nivelActual.getSnowBro().getJugador();
        jugadorFinal.sumarPuntaje(nivelActual.getSnowBro().getPuntaje());
        actualizarRanking(nivelActual.getSnowBro().getJugador());
        controlaGrafica.mostrarPantallaGameOver();
        GestorSonidos.getInstancia().detenerMusica();
        GestorSonidos.getInstancia().reproducirMusica("src/Sonidos/Background/09_Yoh_(Ending).wav");
    }
}
