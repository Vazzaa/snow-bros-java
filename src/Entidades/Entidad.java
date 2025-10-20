package Entidades;

import java.util.LinkedList;
import java.util.List;

import Grafica.*;
import Juego.ModoDeJuego;

public abstract class Entidad implements EntidadLogica {
	
	protected Skin misAspectos;
	protected List<Observer> listaObservadores;
	protected ModoDeJuego miJuego;
	protected ObserverGrafico observer;
	protected Hitbox miHitbox;

	protected Entidad(Skin skins, float x, float y){
		misAspectos = skins;
		listaObservadores = new LinkedList<Observer>();
		miHitbox.setPosX(x);
		miHitbox.setPosY(y);
	}

	public Skin getSkin(){
		return misAspectos;
	}

	public float getPosX(){
		return miHitbox.getPosX();
	}

	public float getPosY(){
		return miHitbox.getPosY();
	}

	public void setPosX(float x){
		miHitbox.setPosX(x);
	}

	public void setPosY(float y){
		miHitbox.setPosY(y);;
	}

	public void registrarObserver(Observer obs){
		listaObservadores.add(obs);
	}

	public void quitarObserver(Observer obs){
		listaObservadores.remove(obs);
	}

	public void notificarObserver(){
		for(Observer o: listaObservadores){
			o.actualizar();
		}
	}



}
