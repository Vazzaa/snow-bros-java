package Launcher;

import java.awt.EventQueue;
import Grafica.ControladorGrafica;
import Grafica.GUI;
import Juego.ModoDeJuego;
import Juego.Clasico;

public class Launcher {

	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorGrafica controlador_grafica = new GUI();
					ModoDeJuego juego = new Clasico(controlador_grafica);
					controlador_grafica.registrarControladorJuego(juego);
					juego.configurar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}