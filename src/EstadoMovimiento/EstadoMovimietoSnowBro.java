package EstadoMovimiento;

import Entidades.SnowBro.SnowBro;
import Grafica.ConstantesTeclado;

public class EstadoMovimietoSnowBro {
    
    protected SnowBro snowBro;

    private final int VELOCIDAD_MOVIMIENTO = 5;
    private final int FUERZA_SALTO = 18; 
    private final int GRAVEDAD = 1;

    private int velocidadHorizontal = 0;
    private int velocidadVertical = 0;
    protected int [] deriva_x; 
    public int direccion;
    public boolean enElSuelo = false;

    
    public EstadoMovimietoSnowBro(SnowBro snowBro){
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
    	} else if (izquierda) {
    		moverIzquierda();
    	} else {
    		detenerMovimientoHorizontal();
    	}
    	
    	if (salto) {
    		saltar();
    	}
    	
    	actualizar();
    }

    protected void moverDerecha() {
        this.velocidadHorizontal = VELOCIDAD_MOVIMIENTO;
    }

    protected void moverIzquierda() {
        this.velocidadHorizontal = -VELOCIDAD_MOVIMIENTO;
    }
    
    protected void saltar() {
    	if (enElSuelo()) {
    		this.velocidadVertical = FUERZA_SALTO;
            enElSuelo = false;
            System.out.println("SALTANDO - Velocidad vertical: " + velocidadVertical);
    	}
    }
    
    public boolean enElSuelo() {
    	return enElSuelo;
    }

    public void detenerMovimientoHorizontal() {
        this.velocidadHorizontal = 0;
    }

    
    public void actualizar() {
        if (!enElSuelo()) {
            velocidadVertical -= GRAVEDAD;
        }
        int posXAnterior = snowBro.getPosX();
        int posYAnterior = snowBro.getPosY();
        snowBro.setPosX(snowBro.getPosX() + velocidadHorizontal);
        snowBro.setPosY(snowBro.getPosY() + velocidadVertical);
        if (velocidadHorizontal != 0) {
            System.out.println("ACTUALIZAR - PosX: " + posXAnterior + " -> " + snowBro.getPosX() + " (velocidad: " + velocidadHorizontal + ")");
        }
        if (snowBro.getPosY() <= 7650 && velocidadVertical <= 0) {
            snowBro.setPosY(7650); // Mantener en el suelo
            velocidadVertical = 0; // Detener la caída
            enElSuelo = true; // Marcar como en el suelo
            System.out.println("TOCO EL SUELO - PosY: " + snowBro.getPosY());
        }
        /*  if (snowBro.getPosY() <= 7300 && snowBro.getPosY() >= 7290 && snowBro.getPosX() >= 300 && snowBro.getPosX() <= 332 && velocidadVertical <= 0) {
        snowBro.setPosY(7300);
        velocidadVertical = 0;
        enElSuelo = true;
        System.out.println("TOCO LA PLATAFORMA - PosY: " + snowBro.getPosY());
        }*/
        if (enElSuelo() && !ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR)) {
            // Si está en una plataforma pero no en el suelo principal, puede caer
            if (snowBro.getPosY() > 7650) {
                enElSuelo = false;
                System.out.println("CAYENDO DE PLATAFORMA");
            }
        }
        if (velocidadVertical != 0) {
            System.out.println("SALTO - PosY: " + posYAnterior + " -> " + snowBro.getPosY() + " (velocidad: " + velocidadVertical + ", enElSuelo: " + enElSuelo + ")");
        }

    }
}


