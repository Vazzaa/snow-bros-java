package Grafica;

public class AdaptadorPosicionPixel {
	
	public static int transformar_x(int x) {
		return x;
	}
	
	
	public static int transformar_y(int y) {
		return ConstantesVistas.LONGITUD_PISTA - y;
	}
}

