
package itson.sistemarestaurantepresentacion.excepciones;


/**
 * Clase que representa una excepción que se lanza si la sesión de
 * un usuario es inválida.
 * @author romom
 */
public class SesionUsuarioInvalidaException extends Exception{

    public SesionUsuarioInvalidaException() {
    }

    public SesionUsuarioInvalidaException(String message) {
        super(message);
    }
    
}
