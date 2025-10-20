package Juego;

import java.util.LinkedList;
import java.util.List;

import Entidades.Entidad;
import Entidades.SnowBro.SnowBro;
import Fabricas.FabricaEntidades;
import Entidades.Enemigos.*;
import Entidades.PowerUp.*;
import Entidades.Proyectiles.Proyectil;
import Entidades.Estructuras.*;

public class Nivel {

    protected int numero;
    protected SnowBro snowBro;
    protected List<Estructura> misEstructuras;
    protected List<Enemigo> misEnemigos;
    protected List<PowerUp> misPowerUps;
    protected List<Proyectil> misProyectiles;
    protected FabricaEntidades miFabrica;


    public Nivel(int num){
        numero = num;
        misEstructuras = new LinkedList<Estructura>();
        misEnemigos = new LinkedList<Enemigo>();
        misPowerUps = new LinkedList<PowerUp>();
        misProyectiles = new LinkedList<Proyectil>();
        miFabrica = new FabricaEntidades();
    }

    

    public int getNumero() {
        return numero;
    }



    public SnowBro getSnowBro() {
        return snowBro;
    }



    public List<Estructura> getMisEstructuras() {
        return misEstructuras;
    }



    public List<Enemigo> getMisEnemigos() {
        return misEnemigos;
    }



    public List<PowerUp> getMisPowerUps() {
        return misPowerUps;
    }



    public List<Proyectil> getMisProyectiles() {
        return misProyectiles;
    }


    public FabricaEntidades getMiFabrica() {
        return miFabrica;
    }

    public void agregarSnowBro(SnowBro snowBro){
        this.snowBro = snowBro;
    }

    public void agregarEnemigos(Enemigo e){
        misEnemigos.addLast(e);
    }

    public void agregarPowerUps(PowerUp p){
        misPowerUps.addLast(p);
    }

    public void agregarEstructura(Estructura e){
        misEstructuras.addLast(e);
    }

    public void agregarProyectiles(Proyectil p){
        misProyectiles.addLast(p);
    }

    public void moverSnowBro(){
    	snowBro.moverse();
    }

    public void moverEntidad(Entidad e){
        
    }
    
}
