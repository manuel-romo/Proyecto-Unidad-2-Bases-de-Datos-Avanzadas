package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se registra un cliente sin id
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteRegistroSinIDException extends Exception{

    /**
     * Constructor por defecto
     */
    public ClienteRegistroSinIDException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteRegistroSinIDException(String msg) {
        super(msg);
    }
}