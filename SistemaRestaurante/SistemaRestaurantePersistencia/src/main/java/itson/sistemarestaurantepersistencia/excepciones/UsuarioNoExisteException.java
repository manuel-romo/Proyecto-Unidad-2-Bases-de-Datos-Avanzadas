
package itson.sistemarestaurantepersistencia.excepciones;


public class UsuarioNoExisteException extends Exception{

    public UsuarioNoExisteException() {
    }

    public UsuarioNoExisteException(String message) {
        super(message);
    }
    
}
