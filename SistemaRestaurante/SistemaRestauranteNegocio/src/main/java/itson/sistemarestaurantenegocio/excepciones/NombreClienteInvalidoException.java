package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el nombre del
 * Cliente recibido para realizar alguna operación de negocio es inválido
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class NombreClienteInvalidoException extends Exception{

    /**
     * Constructor por defecto
     */
    public NombreClienteInvalidoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public NombreClienteInvalidoException(String msg) {
        super(msg);
    }
}
