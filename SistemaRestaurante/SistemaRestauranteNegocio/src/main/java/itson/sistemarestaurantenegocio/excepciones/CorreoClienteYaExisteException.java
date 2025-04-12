package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el correo electrónico del
 * cliente recibido para realizar alguna operación de negocio ya existe
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class CorreoClienteYaExisteException extends Exception {

    /**
     * Constructor por defecto
     */
    public CorreoClienteYaExisteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public CorreoClienteYaExisteException(String msg) {
        super(msg);
    }
}
