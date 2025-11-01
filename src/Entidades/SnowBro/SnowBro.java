package Entidades.SnowBro;

import Entidades.Jugador.Jugador;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.Proyectiles.ProyectilNieve;

import java.util.List;

import javax.swing.Timer;

import Entidades.Enemigos.Enemigo;
import EstadoMovimiento.EstadoMovimietoSnowBro;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Grafica.ConstantesTeclado;
import Grafica.ObserverGrafico;
import Grafica.ObserverJugador;
import Visitors.Colisionable;
import Visitors.Colisionador;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.Nivel;
import Juego.ModoDeJuego;

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
    public SnowBro (Skin aspectos, ModoDeJuego juego, int x, int y, Jugador jug, Nivel nivelPerteneciente, FabricaEntidades crearNieve) {
        super(aspectos, juego, x, y);
        velocidad = 3;
        jugador = jug;
        vida = 3;
        puntaje = 0;
        estadoMovimiento = new EstadoMovimietoSnowBro(this);
        nivel = nivelPerteneciente;
        this.crearNieve = crearNieve;
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
    
    public void sumarPuntaje(int p) {
        puntaje += p;
    }

    public void setJugador(Jugador j) {
        jugador = j;
    }
    
    public void setVelocidad(int v){
        velocidad = v;
    }
    
    public void disparar() {
        ProyectilNieve disparo = crearNieve.getProyectilNieve(miHitbox.getPosX(), miHitbox.getPosY(), estadoMovimiento.direccion);
        nivel.agregarProyectiles(disparo);
        miJuego.registrarObserver(disparo);
         Timer timer = new Timer(disparo.getAlcance(), e -> {
        miJuego.getControladoraGrafica().sacarEntidad(disparo); 
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    public void moverse() {
        boolean derecha = ConstantesTeclado.estaPresionada(ConstantesTeclado.DERECHA);
    	boolean izquierda = ConstantesTeclado.estaPresionada(ConstantesTeclado.IZQUIERDA);
    	boolean salto = ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR);
    	// if (derecha || izquierda || salto) {
        //     System.out.println("MOVIMIENTO DETECTADO - Derecha: " + derecha + ", Izquierda: " + izquierda + ", Salto: " + salto);
        //     System.out.println("Posición actual X: " + getPosX() + ", Y: " + getPosY());
        // }
        estadoMovimiento.mover(derecha, izquierda, salto);
        notificarObserver();
    }
    
    public void setDireccion(int direccion){
        estadoMovimiento.cambiar_direccion(direccion);
		misAspectos.setEstadoActual(getClaveRepreEstado());
		notificarObserver();
	}
    
    public void morir() {
        jugador.sumarPuntaje(puntaje);
        System.out.println("SnowBro ha muerto");
        if (miJuego != null && miJuego.getControladoraGrafica() != null) {
            miJuego.getControladoraGrafica().mostrarPantallaGameOver();
            miJuego.reiniciarNivel();
        }
    }
    
    public void disminuirVida() {
        if(vida > 0)
            vida--;
        else 
            morir();
    }
    
    public void procesarColision(Colisionable c) {
        c.aceptarColision(this);
    }
    
    public void afectar(Enemigo e) {
        vida -= 1;
        miHitbox.setPosX(10);
        miHitbox.setPosY(7650);
        resetVelocidad();
        notificarObserver();
        if (vida <= 0) {
            morir();
        }
    }
    
    public void afectar(PowerUp p) {
        p.afectar(this);
        nivel.eliminarPowerUp(p);
        notificarObserver();
    }
    
    public void afectar(Estructura e) {
        e.afectar(this);
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
                return 3 + estadoMovimiento.getFrameAnimacion(); // Estado caminando derecha
            case 180: // Caminando hacia la izquierda
                return 6 + estadoMovimiento.getFrameAnimacion(); // Estado caminando izquierda
            default:
                return 1; // Estado por defecto
        }
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void colisionarPowerUp(PowerUp p) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, p.getHitbox());
        if (!colisiona) return;
        afectar(p);
        return;
    }

    public void colisionarEnemigo(Enemigo e) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (!colisiona) return;
        afectar(e);
        return;
    }
    
    public void colisionarEstructura(Estructura e) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (!colisiona) return;
        afectar(e);
        return;
    }

    public void colisionarObstaculo(Obstaculo o) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, o.getHitbox());
        if (!colisiona) return;
        afectar(o);
        return;
    }

    public void resetVelocidad() {
        this.velocidad = 3;
    }

    public void detenerMovimiento() {
        estadoMovimiento.detenerMovimientoHorizontal();
    }
}
