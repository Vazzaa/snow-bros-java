package Juego;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.Jugador.Jugador;
import Entidades.PowerUp.PowerUp;
import Entidades.SnowBro.SnowBro;
import Fabricas.FabricaDominio1;
import Fabricas.FabricaDominio2;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Parser.CreadorDeNivel;
import Grafica.*;
import Hilos.HiloEntidades;
import Hilos.HiloJugador;

public abstract class ModoDeJuego implements ControladorJuego {
    
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
		fabricaSkins = null;
		miFabricaEntidades = null;
		miCreadorNivel = null;
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
		//nivelActual.getSnowBro().setDireccion(direccion);
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
		fabricaSkins = new FabricaDominio1();
		miFabricaEntidades = new FabricaEntidades(fabricaSkins, this);
		miCreadorNivel = new CreadorDeNivel(fabricaSkins);
		miCreadorNivel.setFrabricaEntidades(miFabricaEntidades);
	}

	public void activarDominio2(){
		fabricaSkins = new FabricaDominio2();
		miFabricaEntidades = new FabricaEntidades(fabricaSkins, this);
		miCreadorNivel = new CreadorDeNivel(fabricaSkins);
		miCreadorNivel.setFrabricaEntidades(miFabricaEntidades);
	}

	public void moverJugador(){
		nivelActual.getSnowBro().moverse();
	}

	@Override
	public abstract void iniciar();
	public abstract void verificarNivelCompletado();
	public abstract void juegoCompletado();

	protected void cargarNivel(int numeroNivel, int puntaje) {
		String archivoNivel = "nivel" + numeroNivel + ".txt";

		CreadorDeNivel creador = new CreadorDeNivel(fabricaSkins);
		creador.setFrabricaEntidades(miFabricaEntidades);
		nivelActual = creador.leerArchivo(archivoNivel);

		String rutaFondo;
		if (numeroNivel >= 4 && numeroNivel <= 6) {
			rutaFondo = "/Imagenes/Background/Fondo3.png"; // Ruta para niveles 4, 5, 6
		} else {
			rutaFondo = "/Imagenes/Background/Fondo1.png"; // Ruta para niveles 1, 2, 3
		}
		controlaGrafica.setImagenDeFondoNivel(rutaFondo);

		nivelActual.setJuego(this);

		nivelActual.getSnowBro().setNivel(nivelActual);
		nivelActual.getSnowBro().setJugador(jugador);
		nivelActual.getSnowBro().sumarPuntaje(puntaje);

		numeroNivelActual = numeroNivel;
		registrarObservers();
	}

	protected void limpiarNivelActual() {
		if(nivelActual != null) {
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
	}

	protected void iniciarHilos() {
		hiloJugador = new HiloJugador(nivelActual);
		hiloJugador.start();
		hiloEntidades = new HiloEntidades(nivelActual);
		hiloEntidades.start();
	}

	public void detenerHilos() {
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
		// No aplica para este caso
	}

	@Override
	public void lanzarProyectil() {
		if (nivelActual != null && nivelActual.getSnowBro() != null)
			nivelActual.getSnowBro().disparar();
	}

	@Override
	public boolean estaColisionando(Entidad e) {
		// No aplica para este caso
		throw new UnsupportedOperationException("Unimplemented method 'estaColisionando'");
	}

	@Override
	public void moverAbajo() {
		// No aplica para este caso
	}
	
	@Override
	public Nivel getNivelActual() {
		return nivelActual;
	}

	public void reiniciarNivel() {
		limpiarNivelActual();
		nivelActual.reiniciarNivel();
	}

	public void actualizarRanking(Jugador jugador) {
		controlaGrafica.agregarAlRanking(jugador);
	}

	@Override
	public boolean debeMostrarTiempo() {
		return false;
	}

}
