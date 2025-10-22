package Entidades.SnowBro;

import Entidades.Jugador.Jugador;
import Entidades.Estructuras.Estructura;
import Entidades.PowerUp.PowerUp;
import Entidades.Enemigos.Enemigo;
import EstadoMovimiento.EstadoMovimietoSnowBro;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Grafica.ConstantesTeclado;
import Visitors.Colisionable;
import Juego.Entidad;
import Juego.EntidadJugador;

public class SnowBro extends Entidad implements EntidadJugador {
    //Atributos de instancia
    
    protected Jugador jugador;
    protected EstadoMovimietoSnowBro estadoMovimiento;
    protected FabricaEntidades crearNieve;

    
    protected int puntaje;
    protected int vida;
    protected int velocidad;
    
    //Constructor
    public SnowBro (Skin aspectos, int x, int y, Jugador jug) {
        super(aspectos, x, y);
        velocidad = 20;
        jugador = jug;
        vida = 3;
        puntaje = 0;
        jugador = jug;
        estadoMovimiento = new EstadoMovimietoSnowBro(this);
    }

    //Comandos
    
    public int getVida() {
        return vida;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getVelocidad(){
        return velocidad;
    }
    
    public String getNombre() {
        return jugador.getNombre();
    }

    public void setVida(int v) {
        vida = v;
    }
    
    public void setPuntaje(int p) {
        puntaje += p;
    }

    public void setJugador(Jugador j) {
        jugador = j;
    }
    
    public void setVelocidad(int v){
        velocidad = v;
    }
    
    public void disparar() {
        
    }
    
    public void moverse() {
        boolean derecha = ConstantesTeclado.estaPresionada(ConstantesTeclado.DERECHA);
    	boolean izquierda = ConstantesTeclado.estaPresionada(ConstantesTeclado.IZQUIERDA);
    	boolean salto = ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR);
    	
        estadoMovimiento.mover(derecha, izquierda, salto);
        notificarObserver();
        
        
    }
    
    public void setDireccion(int direccion){
        estadoMovimiento.cambiar_direccion(direccion);
		misAspectos.setEstadoActual(getClaveRepreEstado());
		notificarObserver();
	}
    
    public void morir() {
        
    }
    
    public void disminuirVida() {
        
    }
    
    public void chocar(Colisionable c) {
        
    }
    
    public void afectar(Enemigo e) {
        
    }
    
    public void afectar(PowerUp p) {
        
    }
    
    public void afectar(Estructura e) {
        
    }
    
    
    public void reiniciar() {
        
    }
    
    public int getClaveRepreEstado() {
        return 0;
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }
    
}
