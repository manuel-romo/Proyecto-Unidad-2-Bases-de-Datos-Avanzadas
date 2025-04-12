
package itson.sistemarestaurantenegocio.excepciones;


/** 
 * Clase que represena una excepción lanzada cuando se comprueba el nombre
 * de un ingrediente tiene valor nulo. 
 * Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class IngredienteSinNombreException extends Exception{

    public IngredienteSinNombreException() {
    }

    public IngredienteSinNombreException(String message) {
        super(message);
    }
    
}
