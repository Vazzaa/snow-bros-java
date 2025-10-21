package Grafica;

import javax.swing.JFrame;

import Entidades.Entidad;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Juego.ControladorJuego;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI implements ControladorGrafica, ControladorVistas {

	//Atributos
	protected PanelPantallaPrincipal panelPrincipal;
	protected PanelPantallaRanking panelRanking;
	protected PanelPantallaElegirModoDeJuego panelElegirModoJuego;
	protected PanelPantallaElegirDominio panelElegirDominio;
	protected PanelPantallaNivel panelNivel;
	protected PanelPantallaGameOver panelGameOver;
	protected JFrame ventana;
	protected ControladorJuego controlarJuego;
	protected ConstantesTeclado keyListener;

	public GUI(){
		panelPrincipal = new PanelPantallaPrincipal(this);
		panelRanking = new PanelPantallaRanking(null, null);
		panelElegirModoJuego = new PanelPantallaElegirModoDeJuego(null, null, null, null);
		panelElegirDominio = new PanelPantallaElegirDominio(null, null, null);
		panelNivel = new PanelPantallaNivel(null);
		panelGameOver = new PanelPantallaGameOver(null, null);
		configurarVentana();
		//registrarOyenteVentana();

		keyListener = new ConstantesTeclado();
		ventana.addKeyListener(keyListener);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
	}

	protected void configurarVentana(){
		ventana = new JFrame("Tdp : SnowBros");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setFocusable(true);
		registrar_oyente_ventana();
		mostrarPantallaPrincipal();
	}

	protected void registrar_oyente_ventana() {
		ventana.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_A){
					controlarJuego.cambiarDireccionJugador(ConstantesTeclado.IZQUIERDA);
				}
				if (e.getKeyCode() == KeyEvent.VK_D){
					controlarJuego.cambiarDireccionJugador(ConstantesTeclado.DERECHA);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE){
					controlarJuego.lanzarProyectil();
				}
			}
		});
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
		this.controlarJuego = cj;
		
	}

	@Override
	public void mostrarPantallaPrincipal() {
		ventana.setContentPane(panelPrincipal);
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

	@Override
	public void accionarInicioJuego() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaElegirDominio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaElegirModoJuego() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaNivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaPrincipal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaRanking() {
		// TODO Auto-generated method stub
		
	}
    
}
