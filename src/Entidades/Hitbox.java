package Entidades;

public class Hitbox {
	
	// Atributos
	protected float ancho;
	protected float alto;
	protected float posicionX;
	protected float posicionY;
	
	// Constructor
	public Hitbox(float anch, float alt, float posX, float posY) {
		ancho = anch;
		alto = alt;
		posicionX = posX;
		posicionY = posY;
	}
	
	// Comandos	
	public void setAncho(float a) {
		ancho = a;
	}
	
	public void setAlto(float al) {
		alto = al;
	}
	
	public void setPosX(float x) {
		posicionX = x;
	}
	
	public void setPosY(float y) {
		posicionY = y;
	}
	
	public float getAncho() {
		return ancho;
	}
	
	public float getAlto() {
		return alto;
	}
	
	public float getPosX() {
		return posicionX;
	}
	
	public float getPosY() {
		return posicionY;
	}
}
