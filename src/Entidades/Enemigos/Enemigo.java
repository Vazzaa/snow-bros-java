package Entidades.Enemigos;

import EstadoMovimiento.*;
import Fabricas.Skin;
import Juego.Entidad;
import Juego.ModoDeJuego;
import Entidades.SnowBro.SnowBro;
import Visitors.Colisionable;
import Visitors.Colisionador;
import Entidades.Estructuras.Plataforma;

public abstract class Enemigo extends Entidad implements Colisionable, Movible, Colisionador{

    
    protected int vida;
    protected int puntaje;
    protected EstadoEnemigo estadoEnemigo;
    protected EstadoMovimientoEnemigo estadoMovimiento;
    protected long tiempoUltimoCambio;
    protected static final long INTERVALO_CAMBIO = 500;
    protected boolean estaVivo = true;
    protected boolean detenidoGlobalmente = false;
    protected Skin skinOriginal;
    protected int velocidadDeslizamiento = 0; 
    protected boolean siendoEmpujado = false;  
    protected int velocidadPlataformaX = 0;
    protected int velocidadPlataformaY = 0;
    
    
    public Enemigo(Skin skins, ModoDeJuego juego , int posX, int posY, int v, int p) {
        super(skins,juego,posX,posY);
        vida = v;
        puntaje = p;
        skinOriginal = skins;
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
        if(siendoEmpujado){
            this.getSkin().setEstadoActual(5);
        }
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

	public abstract void moverHorizontalmente(int i);

	public abstract void moverVerticalmente(int i);

    public void resetVelocidadPlataforma() {
        this.velocidadPlataformaX = 0;
        this.velocidadPlataformaY = 0;
    }

    public void setVelocidadPlataforma(int velX, int velY) {
        this.velocidadPlataformaX = velX;
        this.velocidadPlataformaY = velY;
    }

    public int getVelocidadPlataformaX() {
        return velocidadPlataformaX;
    }

    public int getVelocidadPlataformaY() {
        return velocidadPlataformaY;
    }

    public boolean estaEnPlataformaMovil() {
        return velocidadPlataformaX != 0 || velocidadPlataformaY != 0;
    }

    public void afectar(Plataforma p) {
        if (estadoMovimiento != null) {
            estadoMovimiento.afectar(this, p);
        }
    }


}