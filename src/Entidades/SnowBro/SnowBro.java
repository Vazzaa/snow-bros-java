package Entidades.SnowBro;

import Entidades.Jugador.Jugador;
import Entidades.Estructuras.Estructura;
import Entidades.PowerUp.PowerUp;
import Entidades.Enemigos.Enemigo;
import EstadoMovimiento.EstadoMovimietoSnowBro;
import Fabricas.FabricaEntidades;
<<<<<<< HEAD
import Fabricas.Skin;
=======
import Entidades.Entidad;
import Entidades.EntidadJugador;
import Entidades.Hitbox;
>>>>>>> e5cd332f09d85be70e117c946da8959b12f29755
import Grafica.ConstantesTeclado;
import Grafica.Observer;
import Visitors.Colisionable;
import Grafica.ObserverJugador;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.Hitbox;

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
    
    //Consultas
    
    public int getClaveRepreEstado() {
        return 0;
    }
    
}
