package Juego;

import java.awt.List;
import java.util.LinkedList;

import Entidades.Enemigos.Enemigo;
import Entidades.Estructuras.Estructura;
import Entidades.SnowBro.SnowBro;
import Fabricas.FabricaDominio1;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Parser.CreadorDeNivel;
import Grafica.*;
import Hilos.HiloJugador;
import Juego.Nivel;
import Juego.Ranking;

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
		// ¿Y ese entero que haría? No hay ningún atributo de tipo int. En la interfaz ControladorJuego, este método no recibe ningún parámetro.
	}
	
	public void cambiarDireccionJugador(int direccion) {
		nivelActual.getSnowBro().setDireccion(direccion);
		// TODO
		// En la interfaz ControladorJuego, cambiarDireccionJugador() recibe un entero n.
		
		// No hay ningún método en ControladorGrafica que controle la dirección de un jugador:
	 	// registrarJugador() recibe un jugador y lo registra, y verificarColisiones() verifica las colisiones, no los movimientos.
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
		
		//nivelActual = miCreadorNivel.crearNivelHarcodeando();
		CreadorDeNivel creador = new CreadorDeNivel(fabricaSkins);
		creador.setFrabricaEntidades(miFabricaEntidades);
		nivelActual = creador.leerArchivo("nivel_simple.json");
		registrarObservers();
		controlaGrafica.mostrarPantallaNivel();
		HiloJugador hiloJugador = new HiloJugador(nivelActual);
		hiloJugador.start();
	}

	protected void registrarObservers() {
		registrarObserverJugador(nivelActual.getSnowBro());
		registrarObserverEnemigos(nivelActual.getMisEnemigos());
		registrarObserversEstructuras(nivelActual.getMisEstructuras());
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

}
