package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba el correo y teléfono del
 * Cliente recibido para realizar alguna operación de persistencia y determinar si ya existe
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class ClienteMismoCorreoTelefonoExistenteException extends Exception{

    /**
     * Constructor por defecto
     */
    public ClienteMismoCorreoTelefonoExistenteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public ClienteMismoCorreoTelefonoExistenteException(String msg) {
        super(msg);
    }
}
