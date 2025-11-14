package Juego;

import Entidades.Jugador.Jugador;
import Grafica.ControladorGrafica;
import Sonidos.GestorSonidos;

public class ContraReloj extends ModoDeJuego{

    private static final long TIEMPO_LIMITE_MS = 180000;

    protected long tiempoInicio;
    protected long tiempoRestante;
    protected boolean tiempoAgotado;

    public ContraReloj(ControladorGrafica controladorGrafica) {
        super(controladorGrafica);
        tiempoAgotado = false;
    }

    @Override
    public void iniciar() {
        jugador = new Jugador(nombreJugador, 0);
        
        tiempoInicio = System.currentTimeMillis();
        tiempoRestante = TIEMPO_LIMITE_MS;
        tiempoAgotado = false;

        GestorSonidos.getInstancia().reproducirMusica("src/Sonidos/Background/03_Yukida-March_(Stage2_4).wav");
        cargarNivel(1, 0);
        
        controlaGrafica.mostrarPantallaNivel();
        iniciarHilos();
        
        System.out.println("Modo Contrareloj iniciado");
        System.out.println("Tiempo límite: " + (TIEMPO_LIMITE_MS / 1000) + " segundos");
    }

    public void verificarNivelCompletado() {
        if (nivelActual != null) {
            actualizarTiempo();
        
            if (tiempoAgotado) {
                System.out.println("Tiempo agotado.");
                GestorSonidos.getInstancia().reproducirEfecto("clock");
                juegoCompletado();
                return;
            }
            
            if (nivelActual.estaCompletado()) {
                System.out.println("Nivel " + numeroNivelActual + " completado.");
                System.out.println("Tiempo restante: " + (tiempoRestante / 1000) + " segundos");
                
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                avanzarSiguienteNivel();
            }
        }
    }

    private void actualizarTiempo() {
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
        tiempoRestante = TIEMPO_LIMITE_MS - tiempoTranscurrido;
        
        if (tiempoRestante <= 0) {
            tiempoRestante = 0;
            tiempoAgotado = true;
        }
        
        if (numeroNivelActual == 3 && (tiempoTranscurrido >= 20000 || nivelActual.getMisEnemigos().isEmpty()))
            nivelActual.spawnMoghera();

        controlaGrafica.actualizarTiempo(getTiempoRestanteFormateado());

        if (tiempoRestante <= 30000 && tiempoRestante > 29000) {
            System.out.println("ADVERTENCIA: 30 segundos restantes.");
        } else if (tiempoRestante <= 10000 && tiempoRestante > 9000) {
            System.out.println("ADVERTENCIA: 10 segundos restantes.");
        }
    }

    public int getTiempoRestanteSegundos() {
        return (int) (tiempoRestante / 1000);
    }

    public String getTiempoRestanteFormateado() {
        int segundosTotales = getTiempoRestanteSegundos();
        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public void juegoCompletado() {
        detenerHilos();
        Jugador jugadorFinal= nivelActual.getSnowBro().getJugador();
        jugadorFinal.sumarPuntaje(nivelActual.getSnowBro().getPuntaje());
        actualizarRanking(nivelActual.getSnowBro().getJugador());
        controlaGrafica.mostrarPantallaGameOver();
        GestorSonidos.getInstancia().detenerMusica();
        GestorSonidos.getInstancia().reproducirMusica("src/Sonidos/Background/09_Yoh_(Ending).wav");
    }

    public void juegoGanado() {
        detenerHilos();
        actualizarRanking(nivelActual.getSnowBro().getJugador());
        controlaGrafica.mostrarPantallaVictoria();
        GestorSonidos.getInstancia().detenerMusica();
        GestorSonidos.getInstancia().reproducirMusica("src/Sonidos/Background/09_Yoh_(Ending).wav");
    }

    protected void avanzarSiguienteNivel() {
        int puntajeActual = nivelActual.getSnowBro().getPuntaje();
        
        detenerHilos();
        limpiarNivelActual();
        
        int siguienteNivel = numeroNivelActual + 1;
        
        String archivoSiguienteNivel = "nivel" + siguienteNivel + ".txt";
        java.io.File archivo = new java.io.File(archivoSiguienteNivel);
            
        if (archivo.exists()) {
            GestorSonidos.getInstancia().reproducirEfecto("level_up");
            System.out.println("Cargando nivel " + siguienteNivel + "...");
            cargarNivel(siguienteNivel, puntajeActual);
            iniciarHilos();
            if (numeroNivelActual == 6)
                GestorSonidos.getInstancia().reproducirEfecto("bossintro");
        } else {
            System.out.println("No hay mas niveles. Fin.");
            nivelActual.getSnowBro().sumarPuntaje(puntajeActual);
            juegoGanado();
        }
    }

    @Override
	public boolean debeMostrarTiempo() {
		return true;
	}
}