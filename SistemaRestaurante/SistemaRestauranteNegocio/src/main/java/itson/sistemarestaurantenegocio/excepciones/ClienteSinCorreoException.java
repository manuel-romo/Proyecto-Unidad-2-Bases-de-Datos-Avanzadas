package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba el correo de un cliente
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteSinCorreoException extends Exception {

    /**
     * Constructor por defecto
     */
    public ClienteSinCorreoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteSinCorreoException(String msg) {
        super(msg);
    }
}