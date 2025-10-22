package Grafica;

import javax.swing.JFrame;

import Juego.ControladorJuego;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.EntidadLogica;

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
		panelRanking = new PanelPantallaRanking(this);
		panelElegirModoJuego = new PanelPantallaElegirModoDeJuego(this);
		panelElegirDominio = new PanelPantallaElegirDominio(this);
		panelNivel = new PanelPantallaNivel(this);
		panelGameOver = new PanelPantallaGameOver(this);
		configurarVentana();
		registrarOyenteVentana();

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
		mostrarPantallaPrincipal();
	}

	protected void registrarOyenteVentana() {
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
		Observer observerEntidad=panelNivel.incorporarEntidad(e);
		refrescar();
		return observerEntidad;
	}

	@Override
	public Observer registrarJugador(EntidadJugador entidadJuga) {
		Observer observerJugador = panelNivel.incorporarEntidadJugador(entidadJuga);
		refrescar();
		return observerJugador;
	}

	public Observer registrar_silueta(EntidadLogica silueta) {
		Observer observer_silueta = panelNivel.incorporarSilueta(silueta);
		refrescar();
		return observer_silueta;
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
		controlarJuego.iniciar();
		
	}

	@Override
	public void accionarPantallaElegirDominio() {
		ventana.setContentPane(panelElegirDominio);
		ventana.revalidate();
    	ventana.repaint();
		
	}

	@Override
	public void accionarPantallaElegirModoJuego() {
		ventana.setContentPane(panelElegirModoJuego);
        ventana.revalidate();
        ventana.repaint();
		
	}

	@Override
	public void accionarPantallaGameOver() {
		ventana.setContentPane(panelGameOver);
		ventana.revalidate();
    	ventana.repaint();
		
	}

	@Override
	public void accionarPantallaNivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accionarPantallaPrincipal() {
		ventana.setContentPane(panelPrincipal);
		ventana.revalidate();
    	ventana.repaint();
		
	}

	@Override
	public void accionarPantallaRanking() {
		ventana.setContentPane(panelRanking);
		ventana.revalidate();
    	ventana.repaint();
		
	}

	@Override
	public void sacarJugador(EntidadJugador e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'sacarJugador'");
	}

	protected void refrescar(){
		ventana.revalidate();
		ventana.repaint();
	}
    
}
