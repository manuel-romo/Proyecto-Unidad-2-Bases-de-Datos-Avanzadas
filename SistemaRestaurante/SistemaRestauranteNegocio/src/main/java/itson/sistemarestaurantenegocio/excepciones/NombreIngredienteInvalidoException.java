
package itson.sistemarestaurantenegocio.excepciones;

/**
 * Clase que representa una excepción lanzada cuando se determina que el 
 * nombre de un ingrediente es inválido.
 * 
 * @author Manuel Romo López
 */
public class NombreIngredienteInvalidoException extends Exception{

    /**
     * Constructor por defecto.
     */
    public NombreIngredienteInvalidoException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public NombreIngredienteInvalidoException(String message) {
        super(message);
    }
    
}
