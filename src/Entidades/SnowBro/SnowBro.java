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
import Visitors.Colisionador;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.Nivel;

public class SnowBro extends Entidad implements EntidadJugador, Colisionador {
    //Atributos de instancia
    
    protected Jugador jugador;
    protected EstadoMovimietoSnowBro estadoMovimiento;
    protected FabricaEntidades crearNieve;
    protected Nivel nivel;
    
    protected int puntaje;
    protected int vida;
    protected int velocidad;
    
    //Constructor
    public SnowBro (Skin aspectos, int x, int y, Jugador jug, Nivel nivelAlQuePertenece) {
        super(aspectos, x, y);
        velocidad = 5;
        jugador = jug;
        vida = 3;
        puntaje = 0;
        jugador = jug;
        estadoMovimiento = new EstadoMovimietoSnowBro(this);
        nivel = nivelAlQuePertenece;
    }

    //Comandos
    public Nivel getNivel() {
        return this.nivel;
    }
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

    public void setNivel(Nivel nivelAlQuePertenece) {
        nivel = nivelAlQuePertenece;
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
    	if (derecha || izquierda || salto) {
            System.out.println("MOVIMIENTO DETECTADO - Derecha: " + derecha + ", Izquierda: " + izquierda + ", Salto: " + salto);
            System.out.println("Posición actual X: " + getPosX() + ", Y: " + getPosY());
        }
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
        if (!estadoMovimiento.enElSuelo()) {
            switch (estadoMovimiento.direccion) {
                case 0:
                    return 10;
                case 180:
                    return 11;
                default:
                    return 10;
        }
    }
        switch (estadoMovimiento.direccion) {
            case 0: // Caminando hacia la derecha
                return 1; // Estado caminando derecha
            case 180: // Caminando hacia la izquierda
                return 2; // Estado caminando izquierda
            default:
                return 1; // Estado por defecto
        }
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    @Override
    public void colisionar(Entidad e1, Entidad e2) {
        // TODO Auto-generated method stub
        
    }
    
}
