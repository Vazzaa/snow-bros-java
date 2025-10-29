package Juego;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import Fabricas.FabricaDominio1;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Parser.CreadorDeNivel;
import Grafica.*;
import Hilos.HiloEntidades;
import Hilos.HiloJugador;

public class ModoDeJuego implements ControladorJuego {
    
	// Atributos
	protected Ranking rank;
	protected ControladorGrafica controlaGrafica;
	protected Nivel nivelActual;
	protected FabricaEntidades miFabricaEntidades;
	protected CreadorDeNivel miCreadorNivel;
	protected String nombreJugador;
	protected FabricaSkin fabricaSkins;
	
	// Comandos
	public ModoDeJuego(ControladorGrafica controlador_grafica) {
		this.controlaGrafica = controlador_grafica;
		fabricaSkins = new FabricaDominio1();
		miFabricaEntidades = new FabricaEntidades(fabricaSkins, this);
		miCreadorNivel = new CreadorDeNivel(fabricaSkins);
		miCreadorNivel.setFrabricaEntidades(miFabricaEntidades);
	}
	public ControladorGrafica getControladoraGrafica(){
		return controlaGrafica;
	}
	
	public void configurar(){
		controlaGrafica.mostrarPantallaPrincipal();
	}
	
	public void cambiarModoDeJuego(int modo) {
		// TODO
	}
	
	public void cambiarDireccionJugador(int direccion) {
		nivelActual.getSnowBro().setDireccion(direccion);
	}

	public Nivel getNivel(){
		return nivelActual;
	}

	public void setNivel(Nivel n){
		nivelActual = n;
	}

	public void setNombreJugador(String nombre){
		nombreJugador = nombre;
	}

	public void activarDominio1(){

	}

	public void activarDominio2(){
		
	}

	public void moverJugador(){
		nivelActual.getSnowBro().moverse();
	}

	@Override
	public void iniciar() {
		CreadorDeNivel creador = new CreadorDeNivel(fabricaSkins);
		creador.setFrabricaEntidades(miFabricaEntidades);
		nivelActual = creador.leerArchivo("nivel_simple.json");
		nivelActual.getSnowBro().setNivel(nivelActual);
		registrarObservers();
		controlaGrafica.mostrarPantallaNivel();
		HiloJugador hiloJugador = new HiloJugador(nivelActual);
		hiloJugador.start();
		HiloEntidades hiloEntidades = new HiloEntidades(nivelActual);
		hiloEntidades.start();

	}

	protected void registrarObservers() {
		registrarObserverJugador(nivelActual.getSnowBro());
		registrarObserverEnemigos(nivelActual.getMisEnemigos());
		registrarObserversEstructuras(nivelActual.getMisEstructuras());
		registrarObserversPowerUps(nivelActual.getMisPowerUps());
	}

	protected void registrarObserverJugador(SnowBro jugador) {
		Observer observerJugador = controlaGrafica.registrarJugador(jugador);
		jugador.registrarObserver(observerJugador);
		
	}

	protected void registrarObserverEnemigos(java.util.List<Enemigo> enemigos) {
		for (Enemigo e : enemigos) {
			Observer observerEnemigo =controlaGrafica.registrarEntidad(e);
			e.registrarObserver(observerEnemigo);
		}
	}

	protected void registrarObserversEstructuras(java.util.List<Estructura> estructuras) {
		for (Estructura es : estructuras) {
			Observer observerEstructura = controlaGrafica.registrarEntidad(es);
			es.registrarObserver(observerEstructura);
		}
	}

	protected void registrarObserversPowerUps(java.util.List<PowerUp> powerups) {
		for (PowerUp pu : powerups) {
			Observer observerPowerUp = controlaGrafica.registrarEntidad(pu);
			pu.registrarObserver(observerPowerUp);
		}
	}

	@Override
	public void cambiarModoDeJuego() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cambiarModoDeJuego'");
	}

	@Override
	public void lanzarProyectil() {
		System.out.println("¡Proyectil lanzado!");
		throw new UnsupportedOperationException("Unimplemented method 'lanzarProyectil'");
	}

	@Override
	public boolean estaColisionando(Entidad e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'estaColisionando'");
	}

	@Override
	public void moverAbajo() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'moverAbajo'");
	}
	@Override
	public Nivel getNivelActual() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getNivelActual'");
	}

	public void reiniciarNivel() {
		controlaGrafica.sacarJugador(nivelActual.getSnowBro());
		for(Estructura es : nivelActual.getMisEstructuras()){
			controlaGrafica.sacarEntidad(es);
		}
		for(Enemigo en : nivelActual.getMisEnemigos()){
			controlaGrafica.sacarEntidad(en);
		}
		for(PowerUp pu : nivelActual.getMisPowerUps()){
			controlaGrafica.sacarEntidad(pu);
		}
		nivelActual.reiniciarnivel();
	}

}
