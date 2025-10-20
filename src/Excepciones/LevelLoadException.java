package Excepciones;

public class LevelLoadException extends RuntimeException {

    /** 
     * @param message 
     * @param cause 
     */
    public LevelLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}