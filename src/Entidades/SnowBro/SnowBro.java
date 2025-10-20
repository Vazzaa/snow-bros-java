package Entidades.SnowBro;

import Entidades.Jugador.Jugador;
import EstadoMovimiento.EstadoMovimietoSnowBro;
import Fabricas.FabricaEntidades;
import Entidades.Entidad;
import Entidades.Hitbox;
import Grafica.Observer;
import Entidades.Skin;
import Visitors.Colisionable;
import Grafica.ObserverJugador;

public class SnowBro extends Entidad{
    //Atributos de instancia
    
    protected Jugador jugador;
    protected EstadoMovimietoSnowBro estadoMovimiento;
    protected FabricaEntidades crearNieve;

    protected int puntaje;
    protected int vida;
    protected int velocidad;

    //Constructor

    public SnowBro (Skin aspectos, float x, float y, Jugador jug) {
        super(aspectos, x, y);
        velocidad = 100;
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
        estadoMovimiento.mover();
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

    public void afectar(Estructura es) {

    }


    public void reiniciar() {

    }

    //Consultas

    public int getClaveRepreEstado() {

    }
}
