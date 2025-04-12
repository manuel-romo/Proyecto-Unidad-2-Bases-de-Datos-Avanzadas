package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el telefono del
 * cliente recibido para realizar alguna operación de negocio es nulo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteSinTelefonoException extends Exception {

    /**
     * Constructor por defecto
     */
    public ClienteSinTelefonoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteSinTelefonoException(String msg) {
        super(msg);
    }
}
