package Entidades.Enemigos;

import Entidades.Estructuras.Estructura;
import Entidades.Estructuras.Obstaculo;
import Entidades.PowerUp.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilBomba;
import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.EstadoEnemigo;
import Fabricas.FabricaEntidades;
import Fabricas.Skin;
import Juego.ModoDeJuego;
import Visitors.Colisionable;

public class Kamakichi extends Enemigo {
    
    protected FabricaEntidades fabParaBomba;

    public Kamakichi(Skin skins, ModoDeJuego juego, int posX, int posY){
        super(skins, juego, posX, posY, 5,300);
    }

    @Override
    public void atacar(Enemigo e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void atacar(SnowBro s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chocar(Colisionable c) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public EstadoEnemigo getEstado() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void recibirDisparo() {
        if (faseActual == FASE_CONGELADO) {
            // No morir, solo ignorar el disparo
            return;
        }

        contadorGolpes++;
        misAspectos.setEstadoActual(2); 

        if (contadorGolpes >= golpesParaCongelar) {
            faseActual = FASE_CONGELADO;
            misAspectos.setEstadoActual(4); 
        } else if (contadorGolpes >= golpesParaEnfurecer) {
            faseActual = FASE_ENFURECIDO;
            misAspectos.setEstadoActual(3); 
        }
    }

    @Override
    public void setEstado(EstadoEnemigo estado) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Skin getSkin() {
        return misAspectos;
    }

    public ProyectilBomba crearBomba(){
        return null;
    }

    @Override
    public void cambiarEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarEstado'");
    }

    public void cambiarEstadoInmediato() {
        if (estadoMovimiento != null) {
            estadoMovimiento = estadoMovimiento.getEstadoOpuesto();
            tiempoUltimoCambio = System.currentTimeMillis();
        }
    }


    @Override
    public void colisionarPowerUp(PowerUp p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarPowerUp'");
    }

    @Override
    public void colisionarEnemigo(Enemigo e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarEnemigo'");
    }

    @Override
    public void colisionarEstructura(Estructura e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarEstructura'");
    }

    @Override
    public void colisionarObstaculo(Obstaculo o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisionarObstaculo'");
    }

    @Override
    public void colisionarProyectil(Proyectil p) {
        
    }

    public boolean esVolador() {
        return false;
    }

    public boolean estaCompletamenteCongelado() {
        return faseActual == FASE_CONGELADO;
    }
}
