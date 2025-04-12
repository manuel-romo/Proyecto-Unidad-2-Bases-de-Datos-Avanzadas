
package itson.sistemarestaurantenegocio.excepciones;


/** 
 * Clase que represena una excepción lanzada cuando se comprueba que la cantidad
 * de un ingrediente tiene valor nulo.
 * Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class IngredienteSinCantidadException extends Exception{

    /**
     * Contructor por defecto
     */
    public IngredienteSinCantidadException() {
    }

    /**
     * Constructor que recibe el mensaje que tendrá la excepción
     * @param message Objeto String que representa el mensaje que tendrá
     * la excepción
     */
    public IngredienteSinCantidadException(String message) {
        super(message);
    }
    
}
