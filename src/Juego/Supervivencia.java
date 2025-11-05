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
    // protected int PuntajeFinal;

    public Supervivencia(ControladorGrafica controladorGrafica) {
        super(controladorGrafica);
        numeroOleada = 1;
        random = new Random();
    }

    public void CrearOleada() {

    }

    public void PasarNivel() {

    }

    public void terminarJuego() {

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
}
