package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el teléfono del
 * Cliente recibido para realizar alguna operación de negocio ya se encuentra registrado
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class TelefonoClienteYaExisteException extends Exception{

    /**
     * Constructor por defecto
     */
    public TelefonoClienteYaExisteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public TelefonoClienteYaExisteException(String msg) {
        super(msg);
    }
}
