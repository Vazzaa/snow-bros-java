package Juego;

import java.util.LinkedList;
import java.util.List;

import Fabricas.Skin;
import Grafica.*;

public abstract class Entidad implements EntidadLogica {
	
	protected Skin misAspectos;
	protected ModoDeJuego miJuego;
	protected ObserverGrafico observer;
	protected Hitbox miHitbox;

	protected Entidad(Skin skins, ModoDeJuego juego, int x, int y){
		miHitbox = new Hitbox(16, 16, x, y);
		misAspectos = skins;
		miJuego = juego;
	}

	public ObserverGrafico getObserverGrafico(){
		return observer;
	}
	
	public ModoDeJuego getJuego() {
		return miJuego;
	}

	public Skin getSkin(){
		return misAspectos;
	}

	public int getPosX(){
		return miHitbox.getPosX();
	}

	public int getPosY(){
		return miHitbox.getPosY();
	}

	public void setPosX(int x){
		miHitbox.setPosX(x);
	}

	public void setPosY(int y){
		miHitbox.setPosY(y);
	}

	public void registrarObserver(Observer obs){
		observer = (ObserverGrafico) obs; // ObserverGrafico implementa Observer
	}

	public void eliminarObserver(Observer obs){
		observer = null;
	}

	public void notificarObserver(){
		observer.actualizar();
	}
	
	public Hitbox getHitbox(){
		return miHitbox;
	}

	public boolean colisionaAABB(Hitbox h1, Hitbox h2) {
		ColisionManager cm = new ColisionManager();
		return cm.colisionaAABB(h1, h2);
	}
	
	public boolean esColisionable() {
		return false;
	}

}
