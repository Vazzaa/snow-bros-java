package Entidades.Jugador;

public class Jugador {

    protected String nombre;
    protected int puntaje;

    public Jugador (String n, int p) {
        nombre = n;
        puntaje = p;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setPuntaje (int p) {
        puntaje += p;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
