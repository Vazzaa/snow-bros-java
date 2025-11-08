package Juego;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Entidades.SnowBro.SnowBro;
import EstadoMovimiento.Movible;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSkin;
import Entidades.Enemigos.*;
import Entidades.PowerUp.*;
import Entidades.Proyectiles.Proyectil;
import Entidades.Estructuras.*;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilFuego;

public class Nivel {

    protected int numero;
    protected SnowBro snowBro;
    protected List<Estructura> misEstructuras;
    protected List<Enemigo> misEnemigos;
    protected List<PowerUp> misPowerUps;
    protected List<Proyectil> misProyectiles;
    protected FabricaEntidades miFabrica;
    protected ModoDeJuego miJuego;
    private long tiempoFinDetencionEnemigos = 0;


    public Nivel(int num, List<Estructura> misEstructuras, List<Enemigo> misEnemigos, SnowBro snowBro, FabricaEntidades miFabrica) {
        numero = num;
        this.snowBro = snowBro;
        this.misEstructuras = misEstructuras;
        this.misEnemigos = misEnemigos;
        misPowerUps = new CopyOnWriteArrayList<PowerUp>();
        misProyectiles = new CopyOnWriteArrayList<Proyectil>();
        this.miFabrica = miFabrica;
        miJuego = null;
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

    public ModoDeJuego getJuego() {
        return miJuego;
    }

    public void setJuego(ModoDeJuego juego) {
        miJuego = juego;
    }

    public void agregarSnowBro(SnowBro snowBro){
        this.snowBro = snowBro;
    }

    public void agregarEnemigos(Enemigo e){
        misEnemigos.add(e);
    }

    public void agregarPowerUps(PowerUp p){
        misPowerUps.add(p);
    }
    
    public void eliminarPowerUp(PowerUp p){
        p.eliminar();
        misPowerUps.remove(p);
    }

    public void agregarEstructura(Estructura e){
        misEstructuras.add(e);
    }

    public void agregarProyectiles(Proyectil p){
        misProyectiles.add(p);
    }

    public void moverSnowBro(){
        if (snowBro != null) 
    	    snowBro.moverse();
    }
    
    public void verificarColisiones() {
        try {
            if (misEnemigos != null) {
                for (Enemigo enemigo : misEnemigos) {
                    if (snowBro != null)
                        snowBro.colisionarEnemigo(enemigo);
                }
            }
            if (misPowerUps != null) {
                for (PowerUp powerUp : misPowerUps) {
                    if (snowBro != null)
                        snowBro.colisionarPowerUp(powerUp);
                }
            }
            if (misEstructuras != null) {
                for (Estructura estructura : misEstructuras) {
                    if (snowBro != null)
                        snowBro.colisionarEstructura(estructura);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verificarColisionesProyectiles() {
        try {
            if (misProyectiles != null) {
                for (Proyectil proyectil : misProyectiles) {
                    if (proyectil.afectaAEnemigos()) {
                        if (misEnemigos != null) {
                            for (Enemigo enemigo : misEnemigos) {
                                enemigo.colisionarProyectil(proyectil);
                            }
                        }
                    } else {
                        if (snowBro != null) {
                            snowBro.colisionarProyectil(proyectil);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reiniciarNivel(){
        snowBro=null;
        misEstructuras.clear();
        misEnemigos.clear();
        misPowerUps.clear();
        misProyectiles.clear();
    }
    public void moverEntidad(Movible e){
        if (e != null) {
            e.moverse();
        }
    }

    public void moverEnemigos(){
        if (misEnemigos == null) return;
        verificarFinDetencionEnemigos();
        for (Enemigo enemigo : misEnemigos) {
            if (enemigo != null) enemigo.moverse();
        }
        misEnemigos.removeIf(e -> !e.estaVivo());
    }

    public void moverEstructurasMoviles(){
        // if (misEstructuras == null) return;
        // for (Estructura estru : misEstructuras) {
        //     if(estru.esMovible())
        //         estru.moverse();
        // }
    }


    public void moverProyectiles() {
        if (misProyectiles == null) return;
        for (Proyectil p : misProyectiles) {
            p.mover();
        }
        misProyectiles.removeIf(p -> !p.estaActivo());
    }

    public void actualizarPowerUps() {
        if (misPowerUps == null) return;
        for (PowerUp p : misPowerUps) {
            p.verificarTiempoDeVida();
        }
        misPowerUps.removeIf(p -> !p.estaActivo());
    }

    public boolean estaCompletado() {
        if(snowBro != null && snowBro.getVida() > 0)
            return misEnemigos.isEmpty();
        else
            return false;
    }

    public void detenerEnemigos(int duracion) {
        this.tiempoFinDetencionEnemigos = System.currentTimeMillis() + duracion;
        for (Enemigo enemigo : misEnemigos) {
            enemigo.detenerMovimientoGlobal();
        }
    }

    public void reanudarEnemigos() {
        for (Enemigo enemigo : misEnemigos) {
            enemigo.reanudarMovimientoGlobal();
        }
    }

    private void verificarFinDetencionEnemigos() {
        if (tiempoFinDetencionEnemigos == 0) return;
        if (System.currentTimeMillis() >= tiempoFinDetencionEnemigos) {
            reanudarEnemigos();
            tiempoFinDetencionEnemigos = 0;
        }
    }

    public void limpiarEnemigosCaidosDelMapa() {
        List<Enemigo> aEliminar = new ArrayList<>();
        
        for (Enemigo e : misEnemigos) {
            // Si el enemigo está fuera del mapa
            if (e.getPosY() > 8100 || e.getPosY() < 7600) {  // Ajusta estos límites
                aEliminar.add(e);
            }
        }
        
        for (Enemigo e : aEliminar) {
            misEnemigos.remove(e);
            e.getJuego().getControladoraGrafica().sacarEntidad(e);
        }
    }

    public void limpiarSnowBroCaidoDelMapa() {
        if (snowBro != null && (snowBro.getPosY() > 8100 || snowBro.getPosY() < 7600)) {
            snowBro.morir();
        }
    }
}
