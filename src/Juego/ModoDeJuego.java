package Juego;

import Entidades.Entidad;
import Fabricas.FabricaDominio1;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Grafica.*;
import Juego.Nivel;
import Juego.Ranking;
import Parser.CreadorDeNivel;

public class ModoDeJuego implements ControladorJuego {
    
	// Atributos
	protected Ranking rank;
	protected ControladorGrafica controlaGrafica;
	protected Nivel nivelActual;
	protected FabricaEntidades miFabricaEntidades;
	protected CreadorDeNivel miCreadorNivel;
	
	// Comandos
	public ModoDeJuego(ControladorGrafica controlador_grafica) {
		this.controlaGrafica = controlador_grafica;
		FabricaSkin fabricaSkinsActuales= new FabricaDominio1();
		miFabricaEntidades = new FabricaEntidades(fabricaSkinsActuales);
		miCreadorNivel = new CreadorDeNivel(fabricaSkinsActuales);
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

	public void activarDominio1(){

	}

	public void activarDominio2(){
		
	}

	public void moverJugador(){
		nivelActual.getSnowBro().moverse();
	}

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iniciar'");
	}

	@Override
	public void cambiarModoDeJuego() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cambiarModoDeJuego'");
	}

	@Override
	public void lanzarProyectil() {
		// TODO Auto-generated method stub
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
