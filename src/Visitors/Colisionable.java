package Visitors;

public interface Colisionable {
    public void aceptarColision(Colisionador c);
    default boolean esColisionable() {
        return true;
    }
}
