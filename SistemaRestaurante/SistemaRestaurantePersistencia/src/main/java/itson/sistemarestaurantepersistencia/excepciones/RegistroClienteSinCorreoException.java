package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba en un registro que el correo del
 * Cliente recibido para realizar alguna operación de persistencia es nulo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class RegistroClienteSinCorreoException extends Exception {

    /**
     * Constructor por defecto
     */
    public RegistroClienteSinCorreoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public RegistroClienteSinCorreoException(String msg) {
        super(msg);
    }
}
