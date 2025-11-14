package Sonidos;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestorSonidos {
    
    private static GestorSonidos instancia;
    private Map<String, Clip> efectosSonido;
    private Clip musicaFondo;

    private float volumenEfectos = 0.7f;
    private float volumenMusica = 0.5f;

    private boolean efectosHabilitados = true;
    private boolean musicaHabilitada = true;

    private GestorSonidos() {
        efectosSonido = new HashMap<>();
        cargarSonidos();
    }

    public static GestorSonidos getInstancia() {
        if (instancia == null) {
            instancia = new GestorSonidos();
        }
        return instancia;
    }

    private void cargarSonidos() {
        cargarEfecto("shoot", "src/Sonidos/Efectos/snowshot.wav");
        cargarEfecto("death", "src/Sonidos/Efectos/snowdead.wav");
        cargarEfecto("enemy_death", "src/Sonidos/Efectos/enemydead.wav");
        cargarEfecto("enemy_fire", "src/Sonidos/Efectos/enemyfire.wav");
        cargarEfecto("jump", "src/Sonidos/Efectos/jump.wav");
        cargarEfecto("powerup", "src/Sonidos/Efectos/powerup.wav");
        cargarEfecto("hit", "src/Sonidos/Efectos/snowkick.wav");
        cargarEfecto("explosion", "src/Sonidos/Efectos/explosion.wav");
        cargarEfecto("press_start", "src/Sonidos/Efectos/pressstartbutton.wav");
        cargarEfecto("press_button", "src/Sonidos/Efectos/nameinput.wav");
        cargarEfecto("level_up", "src/Sonidos/Efectos/1up.wav");
        cargarEfecto("clock", "src/Sonidos/Efectos/clock.wav");

        cargarEfecto("gameover", "src/Sonidos/Background/10_GameOver.wav");
        cargarEfecto("bossintro", "src/Sonidos/Background/05_BossIntro.wav");
    }

    private void cargarEfecto(String nombre, String ruta) {
        try {
            File archivoSonido = new File(ruta);
            if (!archivoSonido.exists()) {
                System.err.println("Archivo de sonido no encontrado: " + ruta);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            efectosSonido.put(nombre, clip);

        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio no soportado: " + ruta);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de sonido: " + ruta);
        } catch (LineUnavailableException e) {
            System.err.println("Línea de audio no disponible: " + ruta);
        }
    }

    public void reproducirEfecto(String nombre) {
        if (efectosHabilitados) {
            Clip clip = efectosSonido.get(nombre);
            if (clip == null) {
                System.err.println("Efecto de sonido no encontrado: " + nombre);
                return;
            }

            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            ajustarVolumen(clip, volumenEfectos);
            clip.start();
        }
    }

    public void reproducirMusica(String ruta) {
        if (musicaHabilitada) {
            detenerMusica();

            try {
                File archivoMusica = new File(ruta);
                if (!archivoMusica.exists()) {
                    System.err.println("Archivo de música no encontrado: " + ruta);
                    return;
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoMusica);
                musicaFondo = AudioSystem.getClip();
                musicaFondo.open(audioStream);

                ajustarVolumen(musicaFondo, volumenMusica);
                musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);

            } catch (Exception e) {
                System.err.println("Error al reproducir música: " + e.getMessage());
            }
        }
    }

    public void detenerMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    public void pausarMusica() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
        }
    }

    public void ajustarVolumen(Clip clip, float volumen) {
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volumen) / Math.log(10.0) * 20.0);
            dB = Math.max(gainControl.getMinimum(), Math.min(dB, gainControl.getMaximum()));
            gainControl.setValue(dB);
        } catch (Exception e) {
            System.err.println("Error al ajustar el volumen: " + e.getMessage());
        }
    }

    public void setVolumenEfectos(float volumen) {
        this.volumenEfectos = Math.max(0.0f, Math.min(1.0f, volumen));
    }
    
    public void setVolumenMusica(float volumen) {
        this.volumenMusica = Math.max(0.0f, Math.min(1.0f, volumen));
        if (musicaFondo != null) {
            ajustarVolumen(musicaFondo, volumenMusica);
        }
    }
    
    public float getVolumenEfectos() {
        return volumenEfectos;
    }
    
    public float getVolumenMusica() {
        return volumenMusica;
    }
    
    public void habilitarEfectos(boolean habilitado) {
        this.efectosHabilitados = habilitado;
    }
    
    public void habilitarMusica(boolean habilitado) {
        this.musicaHabilitada = habilitado;
        if (!habilitado) {
            detenerMusica();
        }
    }
    
    public boolean efectosHabilitados() {
        return efectosHabilitados;
    }
    
    public boolean musicaHabilitada() {
        return musicaHabilitada;
    }

    public void limpiar() {
        detenerMusica();
        
        for (Clip clip : efectosSonido.values()) {
            if (clip != null) {
                clip.close();
            }
        }
        
        efectosSonido.clear();
    }

}
