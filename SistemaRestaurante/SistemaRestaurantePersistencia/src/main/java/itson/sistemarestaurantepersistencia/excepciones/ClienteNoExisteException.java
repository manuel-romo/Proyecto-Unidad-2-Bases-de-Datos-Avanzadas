package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el
 * Cliente recibido para realizar alguna operación de persistencia no exista
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteNoExisteException extends Exception {

    /**
     * Constructor por defecto
     */
    public ClienteNoExisteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteNoExisteException(String msg) {
        super(msg);
    }
}
