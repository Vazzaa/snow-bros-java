package Grafica;

import Juego.ControladorJuego;

public interface ControladorVistas {
    
    public void accionarInicioJuego(int modo);
    public void accionarPantallaRanking();
    public void accionarPantallaGameOver();
    public void accionarPantallaElegirDominio();
    public void accionarPantallaElegirModoJuego();
    public void accionarPantallaPrincipal();
    public void accionarPantallaNivel();
    public ControladorJuego getControladorJuego();
}
