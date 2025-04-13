package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el teléfono del
 * Cliente recibido para realizar alguna operación de negocio ya está registrado
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class MismoTelefonoException extends Exception{

    /**
     * Constructor por defecto
     */
    public MismoTelefonoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public MismoTelefonoException(String msg) {
        super(msg);
    }
}