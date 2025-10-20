package Grafica;

import javax.swing.JFrame;

import Entidades.Entidad;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Juego.ControladorJuego;

public class GUI implements ControladorGrafica {

	//Atributos
	protected PanelPantallaRanking panelRanking;
	protected PanelPantallaPrincipal panelPrincipal;
	protected PanelPantallaElegirDominio panelElegirDominio;
	protected PanelPantallaNivel panelNivel;
	protected PanelPantallaGameOver panelGameOver;
	protected PanelPantallaElegirModoDeJuego panelElegirModoJuego;
	protected JFrame ventana;
	protected ControladorJuego controlarJuego;
	protected ConstantesTeclado keyListener;

	public GUI(JFrame jf){
		ventana=jf;

		keyListener = new ConstantesTeclado();
		ventana.addKeyListener(keyListener);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
	}

	public void setPanelRanking(PanelPantallaRanking pr){
		panelRanking=pr;
	}

	public void setPanelPrincipal(PanelPantallaPrincipal pp){
		panelPrincipal=pp;
	}

	public void setPanelElegirDominio(PanelPantallaElegirDominio pm){
		panelElegirDominio=pm;
	}

	public void setPanelNivel(PanelPantallaNivel pn){
		panelNivel=pn;
	}

	public void setPanelElegirModoJuego(PanelPantallaElegirModoDeJuego pmj){
		panelElegirModoJuego=pmj;
	}

	public void setPanelGameOver(PanelPantallaGameOver pgo){
		panelGameOver=pgo;
	}

	public PanelPantallaRanking getPanelRanking(){
		return panelRanking;
	}

	public PanelPantallaElegirDominio getPanelPantallaElegirDominio(){
		return panelElegirDominio;
	}

	public PanelPantallaPrincipal getPanelPantallaPrincipal(){
		return panelPrincipal;
	}

	public PanelPantallaElegirModoDeJuego getPanelPantallaElegirModoDeJuego(){
		return panelElegirModoJuego;
	}

	public PanelPantallaNivel getPanelPantallaNivel(){
		return panelNivel;
	}

	public PanelPantallaGameOver getPanelPantallaGameOver(){
		return panelGameOver;
	}

	@Override
	public void registrarControladorJuego(ControladorJuego cj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPantallaPrincipal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPantallaNivel() {
		ventana.requestFocusInWindow();
	}

	@Override
	public void mostrarPantallaGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Observer registrarEntidad(EntidadLogica e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observer registrarJugador(EntidadJugador e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sacarEntidad(EntidadLogica e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verificarColisiones(Entidad e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mostrarPantallaRanking() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mostrarPantallaRanking'");
	}

	@Override
	public void mostrarPantallaElegirDominio() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mostrarPantallaElegirDominio'");
	}

	@Override
	public void mostrarPantallaElegirModoJuego() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mostrarPantallaElegirModoJuego'");
	}
    
}
