package Launcher;

import java.awt.EventQueue;
import Grafica.ControladorGrafica;
import Grafica.GUI;

public class Launcher {

	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorGrafica controladorGrafica = new GUI();
					controladorGrafica.mostrarPantallaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}