
package itson.sistemarestaurantenegocio.excepciones;

/**
 * Clase que represena una excepción lanzada cuando se comprueba que el nombre de
 * ingrediente recibido para realizar alguna operación de negocio es
 * nulo. Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class NombreIngredienteNuloException extends Exception {

    /**
     * Constructor por defecto.
     */
    public NombreIngredienteNuloException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public NombreIngredienteNuloException(String message) {
        super(message);
    }
    
}
