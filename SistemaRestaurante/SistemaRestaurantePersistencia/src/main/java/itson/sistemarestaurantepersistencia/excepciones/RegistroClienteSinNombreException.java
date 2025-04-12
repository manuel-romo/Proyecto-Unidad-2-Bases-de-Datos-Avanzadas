package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba en un registro que el nombre del
 * Cliente recibido para realizar alguna operación de persistencia es nulo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class RegistroClienteSinNombreException extends Exception{

    /**
     * Constructor por defecto
     */
    public RegistroClienteSinNombreException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public RegistroClienteSinNombreException(String msg) {
        super(msg);
    }
}
