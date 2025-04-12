package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba el formato
 * De alguno de los campos al regsitrar un cliente
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class FormatoRegistroClienteException extends Exception {

    /**
     * Constructor por defecto
     */
    public FormatoRegistroClienteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public FormatoRegistroClienteException(String msg) {
        super(msg);
    }
}
