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

	protected Entidad(Skin skins, int x, int y){
		misAspectos = skins;
		listaObservadores = new LinkedList<Observer>();
		miHitbox.setPosX(x);
		miHitbox.setPosY(y);
	}

	public ObserverGrafico getObserverGrafico(){
		return observer;
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
