package Entidades;

abstract public class Entidad implements EntidadLogica {
	
	// Atributos
	protected Skin[] aspecto;
	protected Observer[] obser;
	protected ModoDeJuego miJuego;
	protected ObserverGrafico observer;
	protected Hitbox miHitbox;
	
	// Comandos

	abstract public Skin getSkin();
	abstract public float getPosX();
	abstract public float getPosY();

}
