package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el correo del
 * Cliente recibido para realizar alguna operación de persistencia ya está registrado
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteMismoCorreoExistenteException extends Exception{

    /**
     * Constructor por defecto
     */
    public ClienteMismoCorreoExistenteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteMismoCorreoExistenteException(String msg) {
        super(msg);
    }
}
