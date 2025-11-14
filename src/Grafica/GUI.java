package Grafica;

import javax.swing.JFrame;

import Entidades.Jugador.Jugador;
import Juego.Clasico;
import Juego.ContraReloj;
import Juego.Supervivencia;
import Juego.ControladorJuego;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.EntidadLogica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI implements ControladorGrafica, ControladorVistas {

	protected PanelPantallaPrincipal panelPrincipal;
	protected PanelPantallaRanking panelRanking;
	protected PanelPantallaElegirModoDeJuego panelElegirModoJuego;
	protected PanelPantallaElegirDominio panelElegirDominio;
	protected PanelPantallaNivel panelNivel;
	protected PanelPantallaGameOver panelGameOver;
	protected JFrame ventana;
	protected ControladorJuego controlarJuego;
	protected ConstantesTeclado keyListener;
	protected int modoDeJuegoActual;

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
		ventana = new JFrame("TdP : Snow Bros");
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
			public void keyPressed(KeyEvent e) {
				if (controlarJuego != null) {
					ConstantesTeclado.teclasPresionadas.add(e.getKeyCode());
					if (e.getKeyCode() == KeyEvent.VK_A){
						controlarJuego.cambiarDireccionJugador(ConstantesTeclado.IZQUIERDA);
					}
					if (e.getKeyCode() == KeyEvent.VK_D){
						controlarJuego.cambiarDireccionJugador(ConstantesTeclado.DERECHA);
					}
					if (e.getKeyCode() == KeyEvent.VK_W){
						controlarJuego.cambiarDireccionJugador(ConstantesTeclado.SALTAR);
					}
					if (e.getKeyCode() == KeyEvent.VK_SPACE){
						controlarJuego.lanzarProyectil();
					}
				}
			}
			public void keyReleased(KeyEvent e){
				ConstantesTeclado.teclasPresionadas.remove(e.getKeyCode());
			}
		});
	};

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

	public ControladorJuego getControladorJuego(){
		return controlarJuego;
	}

	@Override
	public void registrarControladorJuego(ControladorJuego cj) {
		this.controlarJuego = cj;
	}

	@Override
	public void mostrarPantallaPrincipal() {
		ventana.setContentPane(panelPrincipal);
		refrescar();
	}

	@Override
	public void mostrarPantallaNivel() {
		panelNivel.reiniciarPanel();
		ventana.setContentPane(panelNivel);
		refrescar();
	}

	@Override
	public void mostrarPantallaGameOver() {
		ventana.setContentPane(panelGameOver);
		refrescar();
	}

	@Override
	public void mostrarPantallaRanking() {
		ventana.setContentPane(panelRanking);
		refrescar();
	}

	@Override
	public void mostrarPantallaElegirDominio() {
		ventana.setContentPane(panelElegirDominio);
		refrescar();
	}

	@Override
	public void mostrarPantallaElegirModoJuego() {
		ventana.setContentPane(panelElegirModoJuego);
		refrescar();
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

	public Observer registrarSilueta(EntidadLogica silueta) {
		Observer observerSilueta = panelNivel.incorporarSilueta(silueta);
		refrescar();
		return observerSilueta;
	}

	@Override
	public void sacarEntidad(EntidadLogica e) {
		panelNivel.removerEntidad(e.getObserverGrafico());
		refrescar();
	}
	public void sacarJugador(EntidadJugador e) {
		panelNivel.removerEntidad(e.getObserverGrafico());
		refrescar();
	}
	
	@Override
	public boolean verificarColisiones(Entidad e) {
		// No aplica en este caso
		return false;
	}


	@Override
	public void accionarInicioJuego(int modo) {
		// Si ya existe un juego en curso (de una partida anterior), lo detenemos.
		if (controlarJuego != null) {
			controlarJuego.detenerHilos();
		}

		switch(modo) {
			case ConstantesModoDeJuego.CLASICO:
				controlarJuego = new Clasico(this);
				this.registrarControladorJuego(controlarJuego);
				modoDeJuegoActual = ConstantesModoDeJuego.CLASICO;
				break;
			case ConstantesModoDeJuego.CONTRARELOJ:
				controlarJuego = new ContraReloj(this);
				this.registrarControladorJuego(controlarJuego);
				modoDeJuegoActual = ConstantesModoDeJuego.CONTRARELOJ;
				break;
			case ConstantesModoDeJuego.SUPERVIVENCIA:
				controlarJuego = new Supervivencia(this);
				this.registrarControladorJuego(controlarJuego);
				modoDeJuegoActual = ConstantesModoDeJuego.SUPERVIVENCIA;
				break;
		}
		this.accionarPantallaElegirDominio();
	}

	@Override
	public void accionarPantallaElegirDominio() {
		ventana.setContentPane(panelElegirDominio);
		refrescar();
	}

	@Override
	public void accionarPantallaElegirModoJuego() {
		ventana.setContentPane(panelElegirModoJuego);
		refrescar();
	}

	@Override
	public void accionarPantallaGameOver() {
		ventana.setContentPane(panelGameOver);
		refrescar();
	}

	@Override
	public void accionarPantallaNivel() {
		ventana.setContentPane(panelNivel);
		refrescar();
	}

	@Override
	public void accionarPantallaPrincipal() {
		ventana.setContentPane(panelPrincipal);
		refrescar();
	}

	@Override
	public void accionarPantallaRanking() {
		panelRanking.refrescarRankingsEnPantalla();
		ventana.setContentPane(panelRanking);
		refrescar();
		
	}


	protected void refrescar(){
		ventana.revalidate();
		ventana.repaint();
	}

	@Override
	public void actualizarTiempo(String timepo) {
		if (controlarJuego != null && controlarJuego.debeMostrarTiempo()) {
			panelNivel.actualizarTiempo(timepo);
		}
	}

	public void setImagenDeFondoNivel(String rutaImagen) {
		panelNivel.setImagenDeFondo(rutaImagen);
		refrescar();
	}

	@Override
	public void agregarAlRanking(Jugador jugador) {
		panelRanking.agregarJugadoresRanking(modoDeJuegoActual, jugador);
		refrescar();
	}
	public void limpiarNivel() {
		panelNivel.limpiarPanel();
		refrescar();
	}
}
