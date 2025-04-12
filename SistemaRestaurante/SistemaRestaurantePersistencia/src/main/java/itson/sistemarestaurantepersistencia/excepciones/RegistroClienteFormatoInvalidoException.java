package itson.sistemarestaurantepersistencia.excepciones;
/**
 * Clase que representa una excepción personalizada, cuando se comprueba el formato de alguno de los parámetros del
 * Cliente recibido para realizar alguna operación de persistencia y determinar si es inválido
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class RegistroClienteFormatoInvalidoException extends Exception {
    
    /**
     * Constructor por defecto
     */
    public RegistroClienteFormatoInvalidoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción
     * @param msg Representa el mensaje con el que la excepción es lanzada
     */
    public RegistroClienteFormatoInvalidoException(String msg) {
        super(msg);
    }
}
