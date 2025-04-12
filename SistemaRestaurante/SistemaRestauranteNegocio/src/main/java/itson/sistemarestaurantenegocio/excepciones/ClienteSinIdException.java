package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba el id de un cliente
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteSinIdException extends Exception{

    /**
     * Constructor por defecto
     */
    public ClienteSinIdException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteSinIdException(String msg) {
        super(msg);
    }
}