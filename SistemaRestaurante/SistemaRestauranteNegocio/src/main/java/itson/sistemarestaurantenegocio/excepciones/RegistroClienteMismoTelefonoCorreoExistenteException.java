package itson.sistemarestaurantenegocio.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba que el teléfono y el correo del
 * Cliente recibido para realizar alguna operación de negocio ya se encuentran registrados
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class RegistroClienteMismoTelefonoCorreoExistenteException extends Exception{

    /**
     * Constructor por defecto
     */
    public RegistroClienteMismoTelefonoCorreoExistenteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public RegistroClienteMismoTelefonoCorreoExistenteException(String msg) {
        super(msg);
    }
}
