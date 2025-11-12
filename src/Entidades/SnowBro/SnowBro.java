package Entidades.SnowBro;

import Entidades.Jugador.Jugador;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.BolaDeNieve;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilNieve;
import Entidades.Enemigos.Enemigo;
import EstadoMovimiento.EstadoMovimietoSnowBro;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Grafica.ConstantesTeclado;
import Visitors.Colisionable;
import Visitors.Colisionador;
import Juego.Entidad;
import Juego.EntidadJugador;
import Juego.Nivel;
import Sonidos.GestorSonidos;
import Juego.ModoDeJuego;

public class SnowBro extends Entidad implements EntidadJugador, Colisionador {

    //Atributos de instancia
    protected Jugador jugador;
    protected EstadoMovimietoSnowBro estadoMovimiento;
    protected FabricaEntidades crearNieve;
    protected Nivel nivel;

    protected int puntaje;
    protected int vida;
    protected int velocidad;

    protected int dañoProyectil;
    private long tiempoFinalBoostAzul=0;
    private long tiempoFinalBoostRojo=0;
    private long tiempoFinAnimacionDisparo = 0;
    private boolean estaResbalando = false;
    private boolean enContactoConEscalera = false;
    private int velocidadPlataformaX = 0;
    private int velocidadPlataformaY = 0;
    private static final long DURACION_ANIMACION_DISPARO_MS = 300; 
    
    //Constructor
    public SnowBro (Skin aspectos, ModoDeJuego juego, int x, int y, Jugador jug, Nivel nivelPerteneciente, FabricaEntidades crearNieve) {
        super(aspectos, juego, x, y);
        velocidad = 3;
        jugador = jug;
        vida = 3;
        puntaje = 0;
        dañoProyectil = 1;
        estadoMovimiento = new EstadoMovimietoSnowBro(this);
        nivel = nivelPerteneciente;
        this.crearNieve = crearNieve;
    }

    public void resetVelocidadPlataforma() {
        this.velocidadPlataformaX = 0;
        this.velocidadPlataformaY = 0;
    }

    public void setVelocidadPlataforma(int velX, int velY) {
        this.velocidadPlataformaX = velX;
        this.velocidadPlataformaY = velY;
    }

    //Comandos
    public Nivel getNivel() {
        return this.nivel;
    }

    public int getVida() {
        return vida;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getVelocidad(){
        return velocidad;
    }
    
    public String getNombre() {
        return jugador.getNombre();
    }

    public void setNivel(Nivel nivelAlQuePertenece) {
        nivel = nivelAlQuePertenece;
    }

    public void setVida(int v) {
        vida = v;
    }
    
    public void sumarPuntaje(int p) {
        puntaje += p;
    }

    public void setJugador(Jugador j) {
        jugador = j;
    }
    
    public void setVelocidad(int v){
        velocidad = v;
    }
    
    public void setEnContactoConEscalera(boolean enContacto) {
        this.enContactoConEscalera = enContacto;
    }
    
    public void disparar() {
        tiempoFinAnimacionDisparo = System.currentTimeMillis() + DURACION_ANIMACION_DISPARO_MS;
        ProyectilNieve disparo = crearNieve.getProyectilNieve(miHitbox.getPosX(), miHitbox.getPosY() - 5, estadoMovimiento.direccion);

        if (dañoProyectil == 1) {
            if (estadoMovimiento.direccion == 0) { 
                disparo.getSkin().setEstadoActual(1);
            } else { 
                disparo.getSkin().setEstadoActual(2);
            }
        } else {
            if (estadoMovimiento.direccion == 0) {
                disparo.getSkin().setEstadoActual(3);
            } else { 
                disparo.getSkin().setEstadoActual(4);
            }
        }
        disparo.setDaño(dañoProyectil);
        nivel.agregarProyectiles(disparo);
        miJuego.registrarObserver(disparo);
        GestorSonidos.getInstancia().reproducirEfecto("shoot");
        
        misAspectos.setEstadoActual(getClaveRepreEstado());
        notificarObserver();
    }
    
    public void moverse() {
        boolean derecha = ConstantesTeclado.estaPresionada(ConstantesTeclado.DERECHA);
    	boolean izquierda = ConstantesTeclado.estaPresionada(ConstantesTeclado.IZQUIERDA);
    	boolean salto = ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR);
        verificarFinBoostAzul();
        verificarFinBoostRojo();

        if (derecha && estadoMovimiento.direccion != 0) {
            estadoMovimiento.cambiar_direccion(ConstantesTeclado.DERECHA);
        } else if (izquierda && estadoMovimiento.direccion != 180) {
            estadoMovimiento.cambiar_direccion(ConstantesTeclado.IZQUIERDA);
        }

        estadoMovimiento.mover(derecha, izquierda, salto);
        misAspectos.setEstadoActual(getClaveRepreEstado());
        notificarObserver();
        if (salto)
            GestorSonidos.getInstancia().reproducirEfecto("jump");
    }
    
    
    public void morir() {
        jugador.sumarPuntaje(puntaje);
        System.out.println("SnowBro ha muerto");
        if (miJuego != null && miJuego.getControladoraGrafica() != null) {
            GestorSonidos.getInstancia().reproducirEfecto("death");
            GestorSonidos.getInstancia().detenerMusica();
            miJuego.getControladoraGrafica().mostrarPantallaGameOver();
            miJuego.reiniciarNivel();
        }
    }
    
    public void disminuirVida() {
        if(vida > 0)
            vida--;
        else 
            morir();
    }
    
    public void procesarColision(Colisionable c) {
        c.aceptarColision(this);
    }
    
    public void afectar(Enemigo e) {
        if (e.estaCompletamenteCongelado()) {
            return;
        }
        vida -= 1;
        miHitbox.setPosX(10);
        miHitbox.setPosY(7650);
        resetVelocidad();
        resetDañoProyectil();
        notificarObserver();
        if (vida <= 0) {
            morir();
        }
    }
    
    public void afectar(PowerUp p) {
        p.afectar(this);
        notificarObserver();
    }
    
    public void afectar(Estructura e) {
        e.afectar(this);
    }
    
    public void afectar(Obstaculo o) {
        o.afectar(this);
    }
    
    public void afectar(Proyectil p) {
        vida -= 1;
        miHitbox.setPosX(10);
        miHitbox.setPosY(7650);
        resetVelocidad();
        resetDañoProyectil();
        notificarObserver();
        if (vida <= 0) {
            morir();
        }
    }
    
    public void reiniciar() {
        
    }
    
    public int getClaveRepreEstado() {
        if (System.currentTimeMillis() < tiempoFinAnimacionDisparo) {
            if (estadoMovimiento.direccion == 0) { 
                return 10;
            } else { 
                return 11;
            }
        }

        if (estadoMovimiento.estaEnModoEscalera()) {
            return 9; 
        }
        
        boolean estaSaltando = !estadoMovimiento.enElSuelo() || estadoMovimiento.getVelocidadVertical() != 0;
        if (estaSaltando) {
            return 4; 
        }
        
        switch (estadoMovimiento.direccion) {
            case 0: 
                return 2;
            case 180: 
                return 3;
            default:
                return 1; 
        }
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public void colisionarPowerUp(PowerUp p) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, p.getHitbox());
        if (!colisiona) return;
        afectar(p);
        return;
    }

    public void colisionarEnemigo(Enemigo e) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (!colisiona) return;
        if (e.estaCompletamenteCongelado()) {
            if (!e.estaSiendoEmpujado()) {
                int direccionEmpuje = 0;
                if (estadoMovimiento.direccion == 0) { 
                    direccionEmpuje = 5;
                } else if (estadoMovimiento.direccion == 180) { 
                    direccionEmpuje = -5;
                } else {
                    if (this.getPosX() < e.getPosX()) {
                        direccionEmpuje = -5;
                    } else {
                        direccionEmpuje = 5;
                    }
                }
                e.setVelocidadDeslizamiento(direccionEmpuje);
            }
            return;
        }
        afectar(e);
        return;
    }
    
    public void colisionarEstructura(Estructura e) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, e.getHitbox());
        if (!colisiona) return;
        afectar(e);
        return;
    }

    public void colisionarObstaculo(Obstaculo o) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, o.getHitbox());
        if (!colisiona) return;
        afectar(o);
        return;
    }

    @Override
    public void colisionarProyectil(Proyectil p) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, p.getHitbox());
        if (!colisiona) return;
        afectar(p);
        return;
    }

    public void colisionarBolaDeNieve(BolaDeNieve b) {
        boolean colisiona = this.colisionaAABB(this.miHitbox, b.getHitbox());
        if (!colisiona) return;
        b.afectar(this);
        return;
    }

    public void resetVelocidad() {
        this.velocidad = 3;
    }

    public void resetDañoProyectil() {
        this.dañoProyectil = 1;
    }

    public void detenerMovimiento() {
        estadoMovimiento.detenerMovimientoHorizontal();
    }

    public void activarBoostAzul(int duracion) {
        velocidad = 6;
        tiempoFinalBoostAzul = System.currentTimeMillis() + duracion;
    }

    public void activarBoostRojo(int duracion) {
        dañoProyectil = 2;
        tiempoFinalBoostRojo = System.currentTimeMillis() + duracion;
    }

    public void activarBoostVerde(int duracion){
        getNivel().detenerEnemigos(duracion);
    }

    private void verificarFinBoostAzul() {
        if (velocidad==6 && System.currentTimeMillis() >= tiempoFinalBoostAzul) {
            resetVelocidad();
            tiempoFinalBoostAzul = 0;
        }
    }

    private void verificarFinBoostRojo() {
        if (dañoProyectil == 2 && System.currentTimeMillis() >= tiempoFinalBoostRojo) {
            resetDañoProyectil();

            tiempoFinalBoostRojo = 0;
        }
    }


    public int getDañoProyectil() {
        return dañoProyectil;
    }
    public boolean estaEnEscalera() {
        return enContactoConEscalera;
    }
    

    public EstadoMovimietoSnowBro getEstadoMovimiento() {
        return estadoMovimiento;
    }

	public void moverHorizontalmente(int deltaX) {
        // Este método ahora es solo para movimientos que no son de plataforma.
		// setPosX(getPosX() + deltaX);
        notificarObserver();
	}

    public void moverVerticalmente(int deltaY) {
        // Este método ahora es solo para movimientos que no son de plataforma.
        // setPosY(getPosY() + deltaY);
        notificarObserver();
    }

    public void setEstaResbalando(boolean resbalando) {
        estaResbalando = resbalando;
    }

    public boolean estaResbalando() {
        return estaResbalando;
    }
    
    // Nuevo método para resetear estados temporales al inicio de cada fotograma
    public void resetTemporaryStates() {
        this.enContactoConEscalera = false;
        this.estaResbalando = false;
    }

    public int getVelocidadPlataformaX() {
        return velocidadPlataformaX;
    }

    public int getVelocidadPlataformaY() {
        return velocidadPlataformaY;
    }

    public boolean estaEnPlataformaMovil() {
        return velocidadPlataformaX != 0 || velocidadPlataformaY != 0;
    }

    public void setVelocidadPlataformaX(int velocidadPlataformaX) {
        this.velocidadPlataformaX = velocidadPlataformaX;
    }
}
