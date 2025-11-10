package Grafica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class ConstantesTeclado implements KeyListener {
    public static int DERECHA = KeyEvent.VK_D;  // Tecla D
    public static int IZQUIERDA = KeyEvent.VK_A;  // Tecla A
    public static int SALTAR = KeyEvent.VK_W;  // Tecla W
    public static int SUBIR_ESCALERA = KeyEvent.VK_W;  // Tecla W para subir
    public static int BAJAR_ESCALERA = KeyEvent.VK_S;  // Tecla S para bajar
    
    public static Set<Integer> teclasPresionadas = new HashSet<>();
    
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
