package EstadoMovimiento;

import Entidades.SnowBro.SnowBro;
import Entidades.Estructuras.Estructura;
import Grafica.ConstantesTeclado;
import Juego.ColisionManager;

public class EstadoMovimietoSnowBro {
    
    protected SnowBro snowBro;

    private int velocidad = 3;
    private int fuerzaSalto = 12; 
    private int gravedad = 1;

    private int velocidadHorizontal = 0;
    private int velocidadVertical = 0;
    protected int [] deriva_x; 
    public int direccion;
    public boolean enElSuelo = false;
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
        snowBro.setPosX(snowBro.getPosX() + velocidadHorizontal);
        snowBro.setPosY(snowBro.getPosY() + velocidadVertical);
        if (velocidadHorizontal != 0) {
            System.out.println("ACTUALIZAR - PosX: " + posXAnterior + " -> " + snowBro.getPosX() + " (velocidad: " + velocidadHorizontal + ")");
        }
        if (enElSuelo()) {
            velocidadVertical = 0; // Detener la caída
            enElSuelo = true; // Marcar como en el suelo
            System.out.println("TOCO EL SUELO - PosY: " + snowBro.getPosY());
        }

        if (velocidadVertical > 0 && snowBro.getNivel() != null && snowBro.getNivel().getMisEstructuras() != null) {
            Estructura plataformaArriba = controladorColisiones.colisionaConPlataformaArriba(snowBro, snowBro.getNivel().getMisEstructuras());
            if (plataformaArriba != null) {
                // Si elimino el "&& velocidadVertical > 0" del if, el SnowBro salta siempre. Si lo agrego (como esta ahora), tiene supersalto y traspasa las plataformas.
                snowBro.setPosY(plataformaArriba.getPosY());
                velocidadVertical = 0;
                enElSuelo = true;
                System.out.println("-------------------------TOQUE LA PLATAFORMA DE ARRIBA----------------------------" );
                System.out.println("TOCO LA PLATAFORMA - PosY: " + snowBro.getPosY());
            }
        }
        if (enElSuelo() && !ConstantesTeclado.estaPresionada(ConstantesTeclado.SALTAR)) {
            // Si está en una plataforma pero no en el suelo principal, puede caer
            if (!enElSuelo()) {
                enElSuelo = false;
                System.out.println("CAYENDO DE PLATAFORMA");
            }
        }
        if (velocidadVertical != 0) {
            System.out.println("SALTO - PosY: " + posYAnterior + " -> " + snowBro.getPosY() + " (velocidad: " + velocidadVertical + ", enElSuelo: " + enElSuelo + ")");
        }
        if(chocarparediz()){
            snowBro.setPosX(0);
        }
        if(chocarparedde()){
            snowBro.setPosX(800);
        
        }

    }

    private boolean chocarparediz() {
        if(snowBro.getPosX()<=0){
            return true;
        }
        return false;
    }

    private boolean chocarparedde() {
        if(snowBro.getPosX()>=800){
            return true;
        }
        return false;
    }
}


