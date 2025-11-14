package Juego;

import Entidades.Jugador.Jugador;
import Grafica.ControladorGrafica;
import Sonidos.GestorSonidos;

public class Clasico extends ModoDeJuego {

    protected long tiempoInicio;
    protected long tiempoTranscurrido;

    public Clasico (ControladorGrafica controladorGrafica) {
        super(controladorGrafica);
    }

    @Override
    public void iniciar() {
        jugador = new Jugador(nombreJugador, 0);
        cargarNivel(1, 0);
        controlaGrafica.mostrarPantallaNivel();
        iniciarHilos();
        tiempoInicio = System.currentTimeMillis();
        tiempoTranscurrido = 0;
        System.out.println("Modo Clásico iniciado - Nivel 1");
    }

    @Override
    public void verificarNivelCompletado() {
        if (nivelActual != null) {

            actualizarTiempo();

            if (numeroNivelActual == 3 && nivelActual.getMisEnemigos().isEmpty())
                nivelActual.spawnMoghera();
                
            if (nivelActual.estaCompletado()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                avanzarSiguienteNivel();
            }
        }
    }

    private void actualizarTiempo() {
        tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
        controlaGrafica.actualizarTiempo(getTiempoTranscurridoFormateado());
    }

    public String getTiempoTranscurridoFormateado() {
        int segundosTotales = (int) tiempoTranscurrido / 1000;
        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
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
            if (numeroNivelActual == 6)
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
        controlaGrafica.mostrarPantallaVictoria();
        GestorSonidos.getInstancia().detenerMusica();
        GestorSonidos.getInstancia().reproducirMusica("src/Sonidos/Background/09_Yoh_(Ending).wav");
    }

    @Override
    public boolean debeMostrarTiempo() {
        return true;
    }
}
