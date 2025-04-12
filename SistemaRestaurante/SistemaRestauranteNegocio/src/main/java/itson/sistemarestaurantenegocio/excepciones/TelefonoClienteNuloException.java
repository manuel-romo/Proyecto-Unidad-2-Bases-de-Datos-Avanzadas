package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el teléfono del
 * Cliente recibido para realizar alguna operación de negocio es nulo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class TelefonoClienteNuloException extends Exception{

    /**
     * Construcor por defecto
     */
    public TelefonoClienteNuloException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public TelefonoClienteNuloException(String msg) {
        super(msg);
    }
}
