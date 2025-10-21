package Grafica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class ConstantesTeclado implements KeyListener {
    public static int DERECHA = 10;
    public static int IZQUIERDA = 20;
    public static int SALTAR = 30;
    
    private static Set<Integer> teclasPresionadas = new HashSet<>();
    
    public void keyPressed(KeyEvent e) {
    	teclasPresionadas.add(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e) {
    	teclasPresionadas.remove(e.getKeyCode());
    }

	public void keyTyped(KeyEvent e) {
		// No se usa.
	}
    
    public static boolean estaPresionada(int keyCode) {
    	return teclasPresionadas.contains(keyCode);
    }
    
    public static void limpiar() {
    	teclasPresionadas.clear();
    }
}
