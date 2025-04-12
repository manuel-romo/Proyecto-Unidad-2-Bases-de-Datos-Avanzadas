package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el Id del
 * cliente recibido para realizar alguna operación de negocio es nulo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class IdClienteNuloException extends Exception{

    /**
     * Construcor por defecto
     */
    public IdClienteNuloException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public IdClienteNuloException(String msg) {
        super(msg);
    }
}
