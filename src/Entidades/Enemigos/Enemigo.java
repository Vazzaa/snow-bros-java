package Entidades.Enemigos;

abstract class Enemigo{
    protected int vida;
    protected EstadoEnemigo estado;

    public Enemigo(int vida){
        this.vida = vida;
        this.estado = new EstadoEnemigo(this);
    }   
    public abstract void atacar(Enemigo enemigo);

    public abstract void atacar(Jugador jugador);

    public abstract void moverse();
    
    public abstract void recibirDisparo();

    public abstract void crearPowerUp();

    public abstract void setEstado(EstadoEnemigo estado);

    public abstract EstadoEnemigo getEstado();

} 