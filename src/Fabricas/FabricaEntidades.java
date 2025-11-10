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

    public FabricaSkin getFabricaSkin() {
        return fabricaSkin;
    }

    public SnowBro getSnowBro(int x, int y) {
        SnowBro s = new SnowBro(this.fabricaSkin.crearSkinSnowBro(), miJuego, x, y, null, null, this);
        return s;
    };

    public DemonioRojo getDemonioRojo(int x, int y) {
        return new DemonioRojo(this.fabricaSkin.crearSkinDemonioRojo(), miJuego, x, y);
    }

    public TrollAmarillo getTrollAmarillo(int x, int y) {
        return new TrollAmarillo(this.fabricaSkin.crearSkinTrollAmarillo(), miJuego, x, y);
    }

    public RanaDeFuego getRanaDeFuego(int x, int y) {
        return new RanaDeFuego(this.fabricaSkin.crearSkinRanaDeFuego(), miJuego, x, y, this);
    }

    public Calabaza getCalabaza(int x, int y) {
        return new Calabaza(this.fabricaSkin.crearSkinCalabaza(), miJuego, x, y,this);
    }

    public Fantasma getFantasma(int x, int y) {
        return new Fantasma(this.fabricaSkin.crearSkinFantasma(), miJuego, x, y);
    }

    public Moghera getMoghera(int x, int y) {
        return new Moghera(this.fabricaSkin.crearSkinMoghera(), miJuego, x, y,this);
    }

    public Kamakichi getKamakichi(int x, int y) {
        return new Kamakichi(this.fabricaSkin.crearSkinKamakichi(), miJuego, x, y);
    }

    public Azul getPowerUpAzul(int x, int y) {
        return new Azul(this.fabricaSkin.crearSkinPowerUpAzul(), miJuego, x, y);
    }

    public Rojo getPowerUpRojo(int x, int y) {
        return new Rojo(this.fabricaSkin.crearSkinPowerUpRojo(), miJuego, x, y);
    }

    public Verde getPowerUpVerde(int x, int y) {
        return new Verde(this.fabricaSkin.crearSkinPowerUpVerde(), miJuego, x, y);
    }

    public Fruta getFruta(int x, int y) {
        return new Fruta(this.fabricaSkin.crearSkinFruta(), miJuego, x, y);
    }

    public VidaExtra getVidaExtra(int x, int y) {
        return new VidaExtra(this.fabricaSkin.crearSkinVidaExtra(), miJuego, x, y);
    }

    public Pincho getPincho(int x, int y) {
        return new Pincho(this.fabricaSkin.crearSkinPincho(), miJuego, x, y);
    }

    public Escalera getEscalera(int x, int y) {
        return new Escalera(this.fabricaSkin.crearSkinEscalera(), miJuego, x, y);
    }

    public Pared getPared(int x, int y) {
        Pared pared;
        if (x >= 700) {
            pared = new Pared(this.fabricaSkin.crearSkinParedDerecha(), miJuego, x, y);
        } else {
            pared = new Pared(this.fabricaSkin.crearSkinParedIzquierda(), miJuego, x, y);
        }
        pared.getSkin().setEstadoActual(miJuego.getNivelActual().getNumero());
        return pared;
    }

    public SueloResbaladizo getSueloResbaladizo(int x, int y) {
        return new SueloResbaladizo(this.fabricaSkin.crearSkinSueloResbaladizo(), miJuego, x, y);
    }

    public PlatQuebradiza getPlatQuebradiza(int x, int y) {
        return new PlatQuebradiza(this.fabricaSkin.crearSkinPlatQuebradiza(), miJuego, x, y);
    }

    public PlatMovilHorizontal getPlatMovil(int x, int y) {
        return new PlatMovilHorizontal(this.fabricaSkin.crearSkinPlatMovil(), miJuego, x, y);
    }

    public Plataforma getPlataforma(int x, int y) {
        return new Plataforma(this.fabricaSkin.crearSkinPlataforma(), miJuego, x, y);
    }

    public Plataforma getPlataformaSuelo(int x, int y) {
        Plataforma platSuelo = new Plataforma(this.fabricaSkin.crearSkinPlataforma(), miJuego, x, y, true);
        return platSuelo;
    }

    public ProyectilBomba getProyectilBomba(int x, int y) {
        return null;
        //return new ProyetilBomba(this.fabricaSkin.crearSkinProyectilBomba(), x, y);
    }

    public ProyectilFuego getProyectilFuego(int x, int y, int direccion) {
        return new ProyectilFuego(this.fabricaSkin.crearSkinProyectilFuego(), miJuego, x, y, 5, 1, 500, direccion);
    }

    public ProyectilNieve getProyectilNieve(int x, int y, int direccion) {
        return new ProyectilNieve(this.fabricaSkin.crearSkinProyectilNieve(), miJuego, x, y, direccion);
    }

    public BolaDeNieve getBolaDeNieve(int x, int y, int direccion) {
        return new BolaDeNieve(this.fabricaSkin.crearSkinBolaDeNieve(), miJuego, x, y, 5, 1, 1000000, direccion);
    }
}
