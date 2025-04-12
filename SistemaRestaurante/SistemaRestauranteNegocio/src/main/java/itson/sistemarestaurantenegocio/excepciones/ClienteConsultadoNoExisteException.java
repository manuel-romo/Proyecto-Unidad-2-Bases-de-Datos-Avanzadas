package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se consulta un cliente
 * Y este no existe
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteConsultadoNoExisteException extends Exception{

    /**
     * Constructor por defecto
     */
    public ClienteConsultadoNoExisteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteConsultadoNoExisteException(String msg) {
        super(msg);
    }
}