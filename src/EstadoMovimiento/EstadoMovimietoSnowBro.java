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
    public void moverDerecha() {
        this.velocidadHorizontal = VELOCIDAD_MOVIMIENTO;
    }

    public void moverIzquierda() {
        this.velocidadHorizontal = -VELOCIDAD_MOVIMIENTO;
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

