
package itson.sistemarestaurantenegocio.excepciones;


/** 
 * Clase que represena una excepción lanzada cuando se comprueba que la dirección
 * de imange de un ingrediente es nula.
 * Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class IngredienteSinDireccionImagenException extends Exception{

    /**
     * Contructor por defecto
     */
    public IngredienteSinDireccionImagenException() {
    }

    /**
     * Constructor que recibe el mensaje que tendrá la excepción
     * @param message Objeto String que representa el mensaje que tendrá
     * la excepción
     */
    public IngredienteSinDireccionImagenException(String message) {
        super(message);
    }
    
}
