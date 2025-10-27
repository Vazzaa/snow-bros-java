package Fabricas;

import Entidades.Enemigos.*;
import Entidades.Estructuras.*;
import Entidades.Jugador.Jugador;
import Entidades.PowerUp.*;
import Entidades.Proyectiles.*;
import Entidades.SnowBro.SnowBro;
import Fabricas.FabricaSkin;
import Grafica.Observer;
import Grafica.ObserverGrafico;
import Juego.Hitbox;
//import Entidades.BolaDeNieve;
//import Entidades.Proyectiles.ProyectilBomba;
import Juego.ModoDeJuego;

public class FabricaEntidades {


protected FabricaSkin fabricaSkin;
protected Jugador miJugador;
protected ModoDeJuego miJuego;


public FabricaEntidades (FabricaSkin fb, ModoDeJuego juego) {
    fabricaSkin = fb;
    miJuego = juego;
}


public SnowBro getSnowBro(int x, int y) {
    SnowBro s = new SnowBro(this.fabricaSkin.crearSkinSnowBro(), x, y, null, null);
    return s;
};


public DemonioRojo getDemonioRojo(int x, int y) {
    return new DemonioRojo(this.fabricaSkin.crearSkinDemonioRojo(), x, y);
}

public TrollAmarillo getTrollAmarillo(int x, int y) {
    return new TrollAmarillo(this.fabricaSkin.crearSkinTrollAmarillo(), x, y);
}

public RanaDeFuego getRanaDeFuego(int x, int y) {
    return new RanaDeFuego(this.fabricaSkin.crearSkinRanaDeFuego(), x, y);
}

public Calabaza getCalabaza(int x, int y) {
    return new Calabaza(this.fabricaSkin.crearSkinCalabaza(), x, y);
}

public Fantasma getFantasma(int x, int y) {
    return new Fantasma(this.fabricaSkin.crearSkinFantasma(), x, y);
}

public Moghera getMoghera(int x, int y) {
    return new Moghera(this.fabricaSkin.crearSkinMoghera(), x, y);
}

public Kamakichi getKamakichi(int x, int y) {
    return new Kamakichi(this.fabricaSkin.crearSkinKamakichi(), x, y);
}

public Azul getPowerUpAzul(int x, int y) {
    return new Azul(this.fabricaSkin.crearSkinPowerUpAzul(), x, y,new Hitbox(y, y, x, y));
}

public Rojo getPowerUpRojo(int x, int y) {
    return new Rojo(this.fabricaSkin.crearSkinPowerUpRojo(), x, y,new Hitbox(y, y, x, y));
}

public Verde getPowerUpVerde(int x, int y) {
    return new Verde(this.fabricaSkin.crearSkinPowerUpVerde(), x, y,new Hitbox(y, y, x, y));
}

public Fruta getFruta(int x, int y) {
    Fruta frutillita = new Fruta(this.fabricaSkin.crearSkinFruta(), x, y,new Hitbox(y, y, x, y));
    frutillita.setJuego(miJuego);
    return frutillita;
}

public VidaExtra getVidaExtra(int x, int y) {
    return new VidaExtra(this.fabricaSkin.crearSkinVidaExtra(), x, y,new Hitbox(y, y, x, y));
}

public Pincho getPincho(int x, int y) {
    return new Pincho(this.fabricaSkin.crearSkinPincho(), x, y);
}

public Escalera getEscalera(int x, int y) {
    return new Escalera(this.fabricaSkin.crearSkinEscalera(), x, y);
}

public Pared getPared(int x, int y) {
    return new Pared(this.fabricaSkin.crearSkinPared(), x, y);
}

public SueloResbaladizo getSueloResbaladizo(int x, int y) {
    return new SueloResbaladizo(this.fabricaSkin.crearSkinSueloResbaladizo(), x, y);
}

public PlatQuebradiza getPlatQuebradiza(int x, int y) {
    return new PlatQuebradiza(this.fabricaSkin.crearSkinPlatQuebradiza(), x, y);
}

public PlatMovil getPlatMovil(int x, int y) {
    return new PlatMovil(this.fabricaSkin.crearSkinPlatMovil(), x, y);
}
public Plataforma getPlataforma(int x, int y) {
    return new Plataforma(this.fabricaSkin.crearSkinPlataforma(), x, y);
}

public ProyectilBomba getProyectilBomba(int x, int y) {
    return null;
    //return new ProyetilBomba(this.fabricaSkin.crearSkinProyectilBomba(), x, y);
}

public ProyectilFuego getProyectilFuego(int x, int y) {
    return null;
    //return new ProyectilFuego(this.fabricaSkin.crearSkinProyectilFuego(), x, y);
}

public ProyectilNieve getProyectilNieve(int x, int y) {
    return null;
    //return new ProyectilNieve(this.fabricaSkin.crearSkinProyectilNieve(), x, y);
}

public BolaDeNieve getBolaDeNieve(int x, int y) {
    return null;
    //return new BolaDeNieve(this.fabricaSkin.crearSkinBolaDeNieve(), x, y);
}
}
