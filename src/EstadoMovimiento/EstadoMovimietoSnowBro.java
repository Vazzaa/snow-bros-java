package EstadoMovimiento;

import Entidades.SnowBro.SnowBro;
import Entidades.Estructuras.Estructura;
import Grafica.ConstantesTeclado;
import Juego.ColisionManager;
import Juego.Hitbox;

public class EstadoMovimietoSnowBro {
    
    protected SnowBro snowBro;

    private int fuerzaSalto = 11; 
    private int gravedad = 1;

    private int velocidadHorizontal = 0;
    private int velocidadVertical = 0;
    protected int [] deriva_x; 
    public int direccion;
    public boolean enElSuelo = false;
    public boolean modoEscalera = false;
    ColisionManager controladorColisiones;
    
    public EstadoMovimietoSnowBro(SnowBro snowBro){
        controladorColisiones = new ColisionManager();
        this.snowBro = snowBro;
        deriva_x = new int[] {-1, 1};
        direccion=0;
        enElSuelo = true;
    }

    public void cambiar_direccion(int direccion){
        if (direccion == ConstantesTeclado.DERECHA){
            this.direccion = 0;
        }else{
            if (direccion == ConstantesTeclado.IZQUIERDA){
                this.direccion=180;
                }
            }   
        }
    
    
    public void mover(boolean derecha, boolean izquierda, boolean salto) {
        boolean subirEscalera = ConstantesTeclado.estaPresionada(ConstantesTeclado.SUBIR_ESCALERA);
        boolean bajarEscalera = ConstantesTeclado.estaPresionada(ConstantesTeclado.BAJAR_ESCALERA);

    // 1. Handle horizontal movement input
    if (derecha) {
        moverDerecha();
    } else if (izquierda) {
        moverIzquierda();
    } else {
        if (snowBro.estaResbalando()) { // Apply friction if resbalando
            velocidadHorizontal *= 0.97;
            if (Math.abs(velocidadHorizontal) < 0.5) {
                velocidadHorizontal = 0;
            }
        } else {
            detenerMovimientoHorizontal(); // Stop immediately if not resbalando
        }
    }

    // 2. Handle vertical movement input (ladder vs. jump)
    // Reset modoEscalera at the start of vertical logic for this frame
    modoEscalera = false; 
    if (snowBro.estaEnEscalera()) { // If currently colliding with a ladder
        if (subirEscalera) {
            modoEscalera = true;
            velocidadVertical = 3;
        } else if (bajarEscalera) {
            modoEscalera = true;
            velocidadVertical = -3;
        } else {
            velocidadVertical = 0; // Stop vertical movement if on ladder but no input
        }
    } else if (salto && enElSuelo() && snowBro.puedeSaltar()) { // Only jump if not on ladder and on solid ground and can jump
        saltar();
    }
    // If not on ladder, not jumping, gravity will be applied in actualizar()

    // 3. Apply physics and update position
    actualizar();
    }
        protected void moverDerecha() {
            int velocidadBase = snowBro.getVelocidad();
            if (snowBro.estaResbalando()) {
                this.velocidadHorizontal = (int)(velocidadBase * 0.5); // 50% de velocidad
            } else {
                this.velocidadHorizontal = velocidadBase;
            }
        }
    
        protected void moverIzquierda() {
            int velocidadBase = snowBro.getVelocidad();
            if (snowBro.estaResbalando()) {
                this.velocidadHorizontal = -(int)(velocidadBase * 0.5); // 50% de velocidad
            } else {
                this.velocidadHorizontal = -velocidadBase;
            }
        }
    
        protected void saltar() {
            if (enElSuelo()) {
                this.velocidadVertical = fuerzaSalto;
                enElSuelo = false;
                snowBro.iniciarAnimacionSalto(); 
            }
        }
    
    public boolean enElSuelo() {
        if (snowBro.getNivel() == null || snowBro.getNivel().getMisEstructuras() == null) {
            return false;
        }
    	if (controladorColisiones.estaEnSuelo(snowBro, snowBro.getNivel().getMisEstructuras())) {
            return true;
        }
        return false;
    }

    public void detenerMovimientoHorizontal() {
        this.velocidadHorizontal = 0;
    }

    
    public void actualizar() {
        // Sincronizar enElSuelo con la realidad ANTES de aplicar física
        // Si el jugador tiene velocidad de plataforma vertical, verificar si realmente está en suelo
        boolean realmenteEnSuelo = enElSuelo();
        if (snowBro.getVelocidadPlataformaY() != 0) {
            // Si tiene velocidad de plataforma vertical, verificar si realmente está en contacto
            if (!realmenteEnSuelo && enElSuelo) {
                // El jugador pensaba que estaba en suelo pero no lo está
                enElSuelo = false;
            } else if (realmenteEnSuelo && !enElSuelo) {
                // El jugador está en suelo pero la variable no lo refleja
                enElSuelo = true;
            }
        } else {
            // Si no tiene velocidad de plataforma, sincronizar normalmente
            if (!realmenteEnSuelo && enElSuelo) {
                enElSuelo = false;
            }
        }
        
        if (!modoEscalera && !enElSuelo()) { // Apply gravity if not climbing and not on solid ground
            velocidadVertical -= gravedad;
        }

    int velocidadHorizontalTotal = velocidadHorizontal + snowBro.getVelocidadPlataformaX();
    int velocidadVerticalTotal = velocidadVertical + snowBro.getVelocidadPlataformaY();
        
        if (snowBro.getNivel() != null && snowBro.getNivel().getMisEstructuras() != null && velocidadHorizontalTotal != 0) {
            int nuevaX = snowBro.getPosX() + velocidadHorizontal;
            boolean colisionaria = false;
            for (Estructura estructura : snowBro.getNivel().getMisEstructuras()) {
                if (estructura.bloquearMovimientoHorizontal()){
                    Hitbox hitboxFutura = new Hitbox(snowBro.getHitbox().getAncho(), snowBro.getHitbox().getAlto(), nuevaX, snowBro.getPosY());
                    if (controladorColisiones.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                        colisionaria = true;
                        break;
                    }
                }
            }
            if (colisionaria) {
                // No cambiamos la velocidad, solo no aplicamos el movimiento
            } else {
                snowBro.setPosX(snowBro.getPosX() + velocidadHorizontalTotal); // Esta línea ya era correcta para X
            }
        } else {
            snowBro.setPosX(snowBro.getPosX() + velocidadHorizontalTotal);
        }
        
        if (!snowBro.estaEnEscalera()) {
            if (snowBro.getNivel() != null && snowBro.getNivel().getMisEstructuras() != null && velocidadVerticalTotal > 0) {
                int nuevaY = snowBro.getPosY() + velocidadVertical;
                int posYActual = snowBro.getPosY();
                
                for (int y = posYActual; y <= nuevaY; y++) {
                    Hitbox hitboxFutura = new Hitbox(snowBro.getHitbox().getAncho(), snowBro.getHitbox().getAlto(), snowBro.getPosX(), y);
                    
                    for (Estructura estructura : snowBro.getNivel().getMisEstructuras()) {
                        if (estructura.bloquearMovimientoHorizontal()) { // Paredes también bloquean verticalmente
                            if (controladorColisiones.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                                int cabezaSnowBro = y + snowBro.getHitbox().getAlto();
                                int sueloEstructura = estructura.getHitbox().getPosY();
                                
                                // Si la cabeza choca con el suelo de la plataforma, detener el movimiento vertical (incluyendo la velocidad de la plataforma)
                                if (cabezaSnowBro >= sueloEstructura - 5 && cabezaSnowBro <= sueloEstructura + 10) {
                                    snowBro.setPosY(sueloEstructura - snowBro.getHitbox().getAlto());
                                    velocidadVertical = 0;
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            if (snowBro.getNivel() != null && snowBro.getNivel().getMisEstructuras() != null && velocidadVerticalTotal < 0) {
                int nuevaY = snowBro.getPosY() + velocidadVertical;
                int posYActual = snowBro.getPosY();
                
                int paso = velocidadVertical < -5 ? -1 : velocidadVertical;
                
                for (int y = posYActual; y >= nuevaY; y += paso) {
                    Hitbox hitboxFutura = new Hitbox(snowBro.getHitbox().getAncho(), snowBro.getHitbox().getAlto(), snowBro.getPosX(), y);
                    
                    for (Estructura estructura : snowBro.getNivel().getMisEstructuras()) {
                            if (controladorColisiones.colisionaAABB(hitboxFutura, estructura.getHitbox())) {
                                int techoEstructura = estructura.getHitbox().getPosY() + estructura.getHitbox().getAlto();
                                int pieSnowBro = y;
                                
                                if (pieSnowBro >= techoEstructura - 5 && pieSnowBro <= techoEstructura + 10) {
                                    snowBro.setPosY(techoEstructura);
                                    velocidadVertical = 0;
                                    enElSuelo = true;
                                    return; 
                                }
                            }   
                        }
                    }
                }
            }
        
        snowBro.setPosY(snowBro.getPosY() + velocidadVerticalTotal); // Aplicar la velocidad vertical total


        // if (velocidadHorizontal != 0) {
        //     System.out.println("ACTUALIZAR - PosX: " + posXAnterior + " -> " + snowBro.getPosX() + " (velocidad: " + velocidadHorizontal + ")");
        // }

        if (!snowBro.estaEnEscalera() && enElSuelo() && velocidadVertical == 0) {
            enElSuelo = true;
            Estructura plataformaDebajo = controladorColisiones.getPlataformaDebajo(snowBro, snowBro.getNivel().getMisEstructuras());
            if (plataformaDebajo != null) {
                int techoPlataforma = plataformaDebajo.getHitbox().getPosY() + plataformaDebajo.getHitbox().getAlto();
                int pieSnowBro = snowBro.getPosY();
                if (Math.abs(pieSnowBro - techoPlataforma) <= 5) {
                    snowBro.setPosY(techoPlataforma);
                }
            }
        }
    
        if (!snowBro.estaEnEscalera() && enElSuelo() && !ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR)) {
            if (!enElSuelo()) {
                enElSuelo = false;
                // System.out.println("CAYENDO DE PLATAFORMA");
            }
        }

        // if (velocidadVertical != 0) {
        //     System.out.println("SALTO - PosY: " + posYAnterior + " -> " + snowBro.getPosY() + " (velocidad: " + velocidadVertical + ", enElSuelo: " + enElSuelo + ")");
        // }
    }

    public boolean estaSubiendoEscalera() {
        if (!snowBro.estaEnEscalera()) {
            return false;
        }
        return ConstantesTeclado.estaPresionada(ConstantesTeclado.SUBIR_ESCALERA);
    }
    
    public boolean estaEnEscalera() {
        return snowBro.estaEnEscalera();
    }
    
    public boolean estaEnModoEscalera() {
        return modoEscalera && snowBro.estaEnEscalera();
    }

    public int getVelocidadVertical() {
        return velocidadVertical;
    }
}
