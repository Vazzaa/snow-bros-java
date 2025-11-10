package Entidades.Enemigos;

import EstadoMovimiento.*;
import Fabricas.Skin;
import Juego.Entidad;
import Juego.ModoDeJuego;
import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;
import Visitors.Colisionador;

public abstract class Enemigo extends Entidad implements Colisionable, Movible, Colisionador{

    
    protected int vida;
    protected int puntaje;
    protected EstadoEnemigo estadoEnemigo;
    protected EstadoMovimientoEnemigo estadoMovimiento;
    protected long tiempoUltimoCambio;
    protected static final long INTERVALO_CAMBIO = 2800;
    protected boolean estaVivo = true;
    protected boolean detenidoGlobalmente = false;
    protected Skin skinOriginal;
    protected int velocidadDeslizamiento = 0; 
    protected boolean siendoEmpujado = false;  
    
    
    
    public Enemigo(Skin skins, ModoDeJuego juego , int posX, int posY, int v, int p) {
        super(skins,juego,posX,posY);
        vida = v;
        puntaje = p;
        skinOriginal = skins; // Guardamos la skin con la que fue creado
        estadoEnemigo = new EstadoActivo();
    }

    public void setVida(int v){
        vida = v;
    }

    public void setPuntaje(int p){
        puntaje = p;
    }

    public void setVelocidadDeslizamiento(int v){
        velocidadDeslizamiento = v;
        siendoEmpujado = (v != 0);
    }

    public int getVelocidadDeslizamiento() {
        return velocidadDeslizamiento;
    }
    
    public boolean estaSiendoEmpujado() {
        return siendoEmpujado;
    }

    public int getVida(){
        return vida;
    }

    public int getPuntaje(){
        return puntaje;
    }
    
    
    public abstract void atacar(Enemigo e);

    public abstract void atacar(SnowBro s);

    public abstract void moverse();

    public abstract void chocar(Colisionable c);
    
    public abstract void recibirDisparo();

    public abstract void setEstado(EstadoEnemigo estado);

    public abstract EstadoEnemigo getEstado();

    public abstract void cambiarEstado();

    public abstract void morir();
    
    public void cambiarEstadoMovimiento(EstadoMovimientoEnemigo nuevoEstado) {
        this.estadoMovimiento = nuevoEstado;
    }

    @Override
    public void aceptarColision(Colisionador c) {
        c.colisionarEnemigo(this);
    }

    public abstract void cambiarEstadoInmediato();

    public abstract boolean esVolador();

    public boolean estaVivo() {
        return estaVivo;
    }

    public abstract boolean estaCompletamenteCongelado();


    public void setSkin(Skin nuevaSkin) {
        this.misAspectos = nuevaSkin;
    }

    public Skin getSkinOriginal() {
        return skinOriginal;
    }

    public void detenerMovimientoGlobal() {
        this.detenidoGlobalmente = true;
    }

    public void reanudarMovimientoGlobal() {
        this.detenidoGlobalmente = false;
    }

    public boolean esInmortal() {
        return false;
    }

	public abstract void moverHorizontalmente(int i);

    public abstract void moverVerticalmente(int i);


} 