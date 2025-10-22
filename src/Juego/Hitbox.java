package Juego;

public class Hitbox {
	
	// Atributos
	protected int ancho;
	protected int alto;
	protected int posicionX;
	protected int posicionY;
	
	// Constructor
	public Hitbox(int anch, int alt, int posX, int posY) {
		ancho = anch;
		alto = alt;
		posicionX = posX;
		posicionY = posY;
	}
	
	// Comandos	
	public void setAncho(int a) {
		ancho = a;
	}
	
	public void setAlto(int al) {
		alto = al;
	}
	
	public void setPosX(int x) {
		posicionX = x;
	}
	
	public void setPosY(int y) {
		posicionY = y;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public int getPosX() {
		return posicionX;
	}
	
	public int getPosY() {
		return posicionY;
	}
}
