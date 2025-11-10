package EstadoMovimiento;

import Entidades.SnowBro.SnowBro;
import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Escalera;
import Grafica.ConstantesTeclado;
import Juego.ColisionManager;
import Juego.Hitbox;

public class EstadoMovimietoSnowBro {
    
    protected SnowBro snowBro;

    private int fuerzaSalto = 13; 
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
        // if (derecha || izquierda || salto) {
        //     System.out.println("EstadoMovimiento.mover() - Derecha: " + derecha + ", Izquierda: " + izquierda + ", Salto: " + salto);
        //     System.out.println("Velocidad horizontal antes: " + velocidadHorizontal);
        // }
        boolean subirEscalera = ConstantesTeclado.estaPresionada(ConstantesTeclado.SUBIR_ESCALERA);
        boolean bajarEscalera = ConstantesTeclado.estaPresionada(ConstantesTeclado.BAJAR_ESCALERA);
        if (snowBro.estaEnEscalera()) {
            if (subirEscalera) {
                modoEscalera = true;
            }
            if (derecha) {
                moverDerecha();
            } else if (izquierda) {
                moverIzquierda();
            } else {
                detenerMovimientoHorizontal();
            }
            
            if (subirEscalera) {
                velocidadVertical = 3; 
                enElSuelo = false; 
            } else if (bajarEscalera) {
                velocidadVertical = -3; 
                enElSuelo = false;
            } else {
                velocidadVertical = 0;
            }
            actualizar();
        } else {
            modoEscalera = false;
            if (derecha) {
                moverDerecha();
            } else if (izquierda) {
                moverIzquierda();
            } else {
                if (snowBro.estaResbalando()) {
                    velocidadHorizontal *= 0.97; 
                    if (Math.abs(velocidadHorizontal) < 0.5) {
                        velocidadHorizontal = 0;
                    }
                } else {
                    detenerMovimientoHorizontal();
                }
            }
            
            if (salto) {
                saltar();
            }
            actualizar();
        }
    }

    protected void moverDerecha() {
        this.velocidadHorizontal = snowBro.getVelocidad();
    }

    protected void moverIzquierda() {
        this.velocidadHorizontal = -snowBro.getVelocidad();
    }
    
    protected void saltar() {
    	if (enElSuelo()) {
    		this.velocidadVertical = fuerzaSalto;
            enElSuelo = false;
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
        if (!snowBro.estaEnEscalera() && !enElSuelo()) {
            velocidadVertical -= gravedad;
        }

        int posXAnterior = snowBro.getPosX();
        int posYAnterior = snowBro.getPosY();
        
        if (snowBro.getNivel() != null && snowBro.getNivel().getMisEstructuras() != null && velocidadHorizontal != 0) {
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
                velocidadHorizontal = 0;
            } else {
                snowBro.setPosX(snowBro.getPosX() + velocidadHorizontal);
            }
        } else {
            snowBro.setPosX(snowBro.getPosX() + velocidadHorizontal);
        }

        if (snowBro.getNivel() != null && snowBro.getNivel().getMisEstructuras() != null && velocidadVertical < 0) {
            int nuevaY = snowBro.getPosY() + velocidadVertical;
            int posYActual = snowBro.getPosY();
            
            int paso = velocidadVertical < -5 ? -1 : velocidadVertical;
            
            for (int y = posYActual; y >= nuevaY; y += paso) {
                Hitbox hitboxFutura = new Hitbox(snowBro.getHitbox().getAncho(), snowBro.getHitbox().getAlto(), snowBro.getPosX(), y);
                
                for (Estructura estructura : snowBro.getNivel().getMisEstructuras()) {
                    if (snowBro.estaEnEscalera() && estructura.esEscalera()) {
                        continue;
                    }
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
        
        snowBro.setPosY(snowBro.getPosY() + velocidadVertical);


        // if (velocidadHorizontal != 0) {
        //     System.out.println("ACTUALIZAR - PosX: " + posXAnterior + " -> " + snowBro.getPosX() + " (velocidad: " + velocidadHorizontal + ")");
        // }

        if (!snowBro.estaEnEscalera() && enElSuelo()) {
            if (velocidadVertical <= 0) {
                velocidadVertical = 0;
                enElSuelo = true;
                Estructura plataformaDebajo = controladorColisiones.getPlataformaDebajo(snowBro, snowBro.getNivel().getMisEstructuras());
                if (plataformaDebajo != null) {
                    int techoPlataforma = plataformaDebajo.getHitbox().getPosY() + plataformaDebajo.getHitbox().getAlto();
                    int pieSnowBro = snowBro.getPosY();
                    if (Math.abs(pieSnowBro - techoPlataforma) <= 5) {
                        snowBro.setPosY(techoPlataforma);
                    }
                }
            } else {
                enElSuelo = false;
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
}