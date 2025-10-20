package EstadoMovimiento;

import Entidades.SnowBro.SnowBro;
import Grafica.ConstantesTeclado;

public class EstadoMovimietoSnowBro {
    
    protected SnowBro snowBro;

    private final int VELOCIDAD_MOVIMIENTO = 5;
    private final int FUERZA_SALTO = -18;
    private final int GRAVEDAD = 1;

    private int velocidadHorizontal = 0;
    private int velocidadVertical = 0;


    
    public EstadoMovimietoSnowBro(SnowBro snowBro){
        this.snowBro = snowBro;
    }
<<<<<<< HEAD
    
    public void mover(boolean derecha, boolean izquierda, boolean salto) {
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
=======
    public void moverDerecha() {
>>>>>>> branch 'main' of https://github.com/2025-Proyectos-TdP-2C/p-comision-11.git
        this.velocidadHorizontal = VELOCIDAD_MOVIMIENTO;
    }

    protected void moverIzquierda() {
        this.velocidadHorizontal = -VELOCIDAD_MOVIMIENTO;
    }
    
    protected void saltar() {
    	if (enElSuelo()) {
    		this.velocidadVertical = FUERZA_SALTO;
    	}
    }
    
    protected boolean enElSuelo() {
    	return velocidadVertical >= 0;
    }

    public void detenerMovimientoHorizontal() {
        this.velocidadHorizontal = 0;
    }

    
    public void actualizar() {
        velocidadVertical += GRAVEDAD;

        snowBro.setPosX(snowBro.getPosX() + velocidadHorizontal);
        snowBro.setPosY(snowBro.getPosY() + velocidadVertical);

    }
}

