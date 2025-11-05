package Juego;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.Jugador.Jugador;
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
	protected Jugador jugador;
	protected int numeroNivelActual;
	protected HiloEntidades hiloEntidades;
	protected HiloJugador hiloJugador;
	
	// Comandos
	public ModoDeJuego(ControladorGrafica controlador_grafica) {
		this.controlaGrafica = controlador_grafica;
		fabricaSkins = new FabricaDominio1();
		miFabricaEntidades = new FabricaEntidades(fabricaSkins, this);
		miCreadorNivel = new CreadorDeNivel(fabricaSkins);
		miCreadorNivel.setFrabricaEntidades(miFabricaEntidades);
		numeroNivelActual = 1;
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
		jugador = new Jugador(nombreJugador, 0);
		cargarNivel(1);

		controlaGrafica.mostrarPantallaNivel();
		iniciarHilos();
	}

	private void cargarNivel(int numeroNivel) {
		String archivoNivel = "nivel" + numeroNivel + ".txt";

		CreadorDeNivel creador = new CreadorDeNivel(fabricaSkins);
		creador.setFrabricaEntidades(miFabricaEntidades);
		nivelActual = creador.leerArchivo(archivoNivel);

		nivelActual.setJuego(this);

		nivelActual.getSnowBro().setNivel(nivelActual);
		nivelActual.getSnowBro().setJugador(jugador);

		registrarObservers();
	}

	public void avanzarSiguienteNivel() {
		detenerHilos();
		limpiarNivelActual();
		int siguienteNivel = numeroNivelActual + 1;

		String archivoSiguienteNivel = "nivel" + siguienteNivel + ".txt";
		java.io.File archivo = new java.io.File(archivoSiguienteNivel);

		if(archivo.exists()) {
			cargarNivel(siguienteNivel);
			iniciarHilos();
			System.out.println("Nivel " + siguienteNivel + " cargado.");
		} else {
			System.out.println("No hay mas niveles. Fin.");
			juegoCompletado();
		}

		// try {
		// 	cargarNivel(numeroNivelActual);
		// 	iniciarHilos();
		// 	System.out.println("Nivel " + numeroNivelActual + " cargado.");
		// } catch (Exception e) {
		// 	System.out.println("No hay mas niveles. Fin.");
		// 	juegoCompletado();
		// }
	}
	
	public void verificarNivelCompletado() {
		if (nivelActual != null && nivelActual.estaCompletado()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			avanzarSiguienteNivel();
		}
	}

	private void limpiarNivelActual() {
		if(nivelActual == null)
			return;
		if (nivelActual.getSnowBro() != null)
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
	}

	private void iniciarHilos() {
		hiloJugador = new HiloJugador(nivelActual);
		hiloJugador.start();
		hiloEntidades = new HiloEntidades(nivelActual);
		hiloEntidades.start();
	}

	private void detenerHilos() {
		if(hiloJugador != null && hiloJugador.isAlive()) {
			hiloJugador.detener();
			try {
				hiloJugador.join(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if(hiloEntidades != null && hiloEntidades.isAlive()) {
			hiloEntidades.detener();
			try {
				hiloEntidades.join(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void juegoCompletado() {
		detenerHilos();
		controlaGrafica.mostrarPantallaGameOver();
		System.out.println("Juego completado. Puntuación final: " + jugador.getPuntaje());
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

	public void registrarObserver(Entidad nuevaEntidad){
		Observer nuevoObserver = controlaGrafica.registrarEntidad(nuevaEntidad);
		nuevaEntidad.registrarObserver(nuevoObserver);
	}

	@Override
	public void cambiarModoDeJuego() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cambiarModoDeJuego'");
	}

	@Override
	public void lanzarProyectil() {
		nivelActual.getSnowBro().disparar();
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
		return nivelActual;
	}

	public void reiniciarNivel() {
		limpiarNivelActual();
		nivelActual.reiniciarNivel();
	}

}
