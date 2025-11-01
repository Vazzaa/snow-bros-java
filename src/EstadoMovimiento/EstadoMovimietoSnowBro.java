package EstadoMovimiento;

import Entidades.SnowBro.SnowBro;
import Entidades.Estructuras.Estructura;
import Grafica.ConstantesTeclado;
import Juego.ColisionManager;
import Juego.Hitbox;
import Entidades.Estructuras.Estructura;

public class EstadoMovimietoSnowBro {
    
    protected SnowBro snowBro;

    private int fuerzaSalto = 12; 
    private int gravedad = 1;

    private int velocidadHorizontal = 0;
    private int velocidadVertical = 0;
    protected int [] deriva_x; 
    public int direccion;
    public boolean enElSuelo = false;
    ColisionManager controladorColisiones;

    private int frameAnimacion = 1;
    private static final int FRAMES_POR_ESTADO = 8;
    
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
            //actualizar();
        }else{
            if (direccion == ConstantesTeclado.IZQUIERDA){
                this.direccion=180;
                //actualizar();
            }   
        }
    }
    
    public void mover(boolean derecha, boolean izquierda, boolean salto) {
        if (derecha || izquierda || salto) {
            System.out.println("EstadoMovimiento.mover() - Derecha: " + derecha + ", Izquierda: " + izquierda + ", Salto: " + salto);
            System.out.println("Velocidad horizontal antes: " + velocidadHorizontal);
        }
        if (derecha) {
    		moverDerecha();
            frameAnimacion++;
    	} else if (izquierda) {
    		moverIzquierda();
            frameAnimacion++;
    	} else {
    		detenerMovimientoHorizontal();
            frameAnimacion = 0;
    	}
    	
    	if (salto) {
    		saltar();
    	}
    	
    	actualizar();
    }

    public int getFrameAnimacion() {
        return (frameAnimacion / FRAMES_POR_ESTADO) % 3;
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
            System.out.println("SALTANDO - Velocidad vertical: " + velocidadVertical);
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
        if (!enElSuelo()) {
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
        
       snowBro.setPosY(snowBro.getPosY() + velocidadVertical);
        if (velocidadHorizontal != 0) {
            System.out.println("ACTUALIZAR - PosX: " + posXAnterior + " -> " + snowBro.getPosX() + " (velocidad: " + velocidadHorizontal + ")");
        }

        if (enElSuelo()) {
            velocidadVertical = 0;
            enElSuelo = true;
            System.out.println("TOCO EL SUELO - PosY: " + snowBro.getPosY());
        }
    
        if (enElSuelo() && !ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR)) {
            if (!enElSuelo()) {
                enElSuelo = false;
                System.out.println("CAYENDO DE PLATAFORMA");
            }
        }

        if (velocidadVertical != 0) {
            System.out.println("SALTO - PosY: " + posYAnterior + " -> " + snowBro.getPosY() + " (velocidad: " + velocidadVertical + ", enElSuelo: " + enElSuelo + ")");
        }
    }
}