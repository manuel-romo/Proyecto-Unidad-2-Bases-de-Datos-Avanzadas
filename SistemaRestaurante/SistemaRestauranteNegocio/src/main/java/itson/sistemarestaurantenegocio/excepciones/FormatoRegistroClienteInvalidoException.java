package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba el formato de algún parametro del
 * Cliente recibido para realizar alguna operación de negocio y determinar si es inválido
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class FormatoRegistroClienteInvalidoException extends Exception {

    /**
     * Constructor por defecto
     */
    public FormatoRegistroClienteInvalidoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public FormatoRegistroClienteInvalidoException(String msg) {
        super(msg);
    }
}
