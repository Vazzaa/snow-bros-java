package Entidades.Jugador;

import java.io.Serializable;

public class Jugador implements Serializable{

    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected int puntaje;

    public Jugador (String n, int p) {
        nombre = n;
        puntaje = p;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void sumarPuntaje (int p) {
        puntaje += p;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
