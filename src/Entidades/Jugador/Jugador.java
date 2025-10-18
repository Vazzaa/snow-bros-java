package Entidades.Jugador;

public class Jugador {
    //Atributos de instancia
    protected String nombre;
    protected int puntaje;
    //Constructor
    public Jugador (String n, int p) {
        nombre = n;
        puntaje = p;
    }
    //Comandos
    public void setNombre(String n) {
        nombre = n;
    }

    public void setPuntaje (int p) {
        puntaje += p;
    }
    //Consultas
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
