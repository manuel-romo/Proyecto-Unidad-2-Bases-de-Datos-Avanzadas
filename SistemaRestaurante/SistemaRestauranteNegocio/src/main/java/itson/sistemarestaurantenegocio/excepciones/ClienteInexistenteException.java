package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se registra un cliente sin teléfono o crreo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteInexistenteException extends Exception{

    /**
     * Constructor por defecto
     */
    public ClienteInexistenteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteInexistenteException(String msg) {
        super(msg);
    }
}
