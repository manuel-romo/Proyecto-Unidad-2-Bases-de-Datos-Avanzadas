package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el correo del
 * Cliente recibido para realizar alguna operación de negocio ya está registrado
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class MismoCorreoException extends Exception{

    /**
     * Constructor por defecto
     */
    public MismoCorreoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public MismoCorreoException(String msg) {
        super(msg);
    }
}
