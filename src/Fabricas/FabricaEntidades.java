package Fabricas;

public class FabricaEntidades {

//atributos de instancia
protected FabricaSkin fabricSkin;
protected ModoDeJuego miModoDeJuego;

//constructor
public FabricaEntidades (FabricaSkin fb, ModoDeJuego mdj) {
    fabricaSkin = fb;
    miModoDeJuego = mdj;
}

//Consultas
public getSnowBro(int x, int y) {
    return new SnowBro(this.fabricaSkin.crearSkinSnowBro(), x, y);
}

public getDemonioRojo(int x, int y) {
    return new DemonioRojo(this.fabricaSkin.crearSkinDemonioRojo(), x, y);
}

public getTrollAmarillo(int x, int y) {
    return new TrollAmarillo(this.fabricaSkin.crearSkinTrollAmarillo(), x, y);
}

public getRanaDeFuego(int x, int y) {
    return new RanaDeFuego(this.fabricaSkin.crearSkinRanaDeFuego(), x, y);
}

public getCalabaza(int x, int y) {
    return new Calabaza(this.fabricaSkin.crearSkinCalabaza(), x, y);
}

public getFantasma(int x, int y) {
    return new Fantasma(this.fabricaSkin.crearSkinFantasma(), x, y);
}

public getMoghera(int x, int y) {
    return new Moghera(this.fabricaSkin.crearSkinMoghera(), x, y);
}

public getKamakichi(int x, int y) {
    return new Kamakichi(this.fabricaSkin.crearSkinKamakichi(), x, y);
}

public getPowerUpAzul(int x, int y) {
    return new Azul(this.fabricaSkin.crearSkinPowerUpAzul(), x, y);
}

public getPowerUpRojo(int x, int y) {
    return new Rojo(this.fabricaSkin.crearSkinPowerUpRojo(), x, y);
}

public getPowerUpVerde(int x, int y) {
    return new Verde(this.fabricaSkin.crearSkinPowerUpVerde(), x, y);
}

public getFruta(int x, int y) {
    return new Fruta(this.fabricaSkin.crearSkinFruta(), x, y);
}

public getVidaExtra(int x, int y) {
    return new VidaExtra(this.fabricaSkin.crearSkinVidaExtra(), x, y);
}

public getPincho(int x, int y) {
    return new Pincho(this.fabricaSkin.crearSkinPincho(), x, y);
}

public getEscalera(int x, int y) {
    return new Escalera(this.fabricaSkin.crearSkinEscalera(), x, y);
}

public getPared(int x, int y) {
    return new Pared(this.fabricaSkin.crearSkinPared(), x, y);
}

public getSueloResbaladizo(int x, int y) {
    return new SueloResbaladizo(this.fabricaSkin.crearSkinSueloResbaladizo(), x, y);
}

public getPlatQuebradiza(int x, int y) {
    return new PlatQuebradiza(this.fabricaSkin.crearSkinPlatQuebradiza(), x, y);
}

public getPlatMovil(int x, int y) {
    return new PlatMovil(this.fabricaSkin.crearSkinPlatMovil(), x, y);
}

public getProyectilBomba(int x, int y) {
    return new ProyectilBomba(this.fabricaSkin.crearSkinProyectilBomba(), x, y);
}

public getProyectilFuego(int x, int y) {
    return new ProyectilFuego(this.fabricaSkin.crearSkinProyectilFuego(), x, y);
}

public getProyectilNieve(int x, int y) {
    return new ProyectilNieve(this.fabricaSkin.crearSkinProyectilNieve(), x, y);
}

public getBolaDeNieve(int x, int y) {
    return new BolaDeNieve(this.fabricaSkin.crearSkinBolaDeNieve(), x, y);
}
}
