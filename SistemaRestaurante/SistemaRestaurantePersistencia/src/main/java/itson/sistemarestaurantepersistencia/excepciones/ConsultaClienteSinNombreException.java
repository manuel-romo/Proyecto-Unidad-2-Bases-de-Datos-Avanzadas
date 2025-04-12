package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba en una consulta que el nombre del
 * Cliente recibido para realizar alguna operación de persistencia es nulo
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ConsultaClienteSinNombreException extends Exception{

    /**
     * Constructor por defecto
     */
    public ConsultaClienteSinNombreException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ConsultaClienteSinNombreException(String msg) {
        super(msg);
    }
}
