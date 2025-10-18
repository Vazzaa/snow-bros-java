package Entidades.SnowBro;
import Entidades.Jugador.Jugador;
import Fabricas.FabricaEntidades;
import Entidades.Hitbox;
import Grafica.Observer;
import Entidades.Skin;
import Visitors.Colisionable;
import Entidades.Enemigos.Enemigo;
import Entidades.PowerUp.PowerUp;
import Entidades.Estructuras.Estructura;
import Grafica.ObserverJugador;

public class SnowBro {
    //Atributos de instancia
    
    protected Jugador jug;
    protected int puntaje;
    protected int vida;
    protected Skin aspecto;
    protected EstadoDireccion estadoDireccion;
    protected ObserverJugador observer;
    protected Hitbox checkpos;
    protected int velocidad;
    protected FabricaEntidades crearNieve;

    //Constructor

    public SnowBro (Skin aspecto_) {
        jug = ;
        puntaje = 0;
        vida = 3;
        aspecto = aspecto_;
        estadoDireccion = ;
        observer = ;
        checkpos = ;
        velocidad = ;
        crearNieve = ;
    }

    //Comandos
    public void setVida(int v) {
        vida = v;
    }
    
    public void setPuntaje(int p) {
        puntaje += p;
    }

    public void setJugador(Jugador j) {
        jug = j;
    }

    public void disparar() {

    }

    public void moverse() {

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

    public void setDireccion (int direccion) {
        estadoDireccion = direccion;
    }

    public void reiniciar() {

    }

    //Consultas
    public int getVida() {
        return vida;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Jugador getJugador() {
        return jug;
    }

    public int getClaveRepreEstado() {

    }
}
