
package itson.sistemarestaurantenegocio.excepciones;


/**
 * Clase que represena una excepción lanzada cuando se comprueba que la unidad de
 * ingrediente recibido para realizar alguna operación de negocio es
 * nula. Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class UnidadIngredienteNulaException extends Exception{

    /**
     * Constructor por defecto.
     */
    public UnidadIngredienteNulaException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public UnidadIngredienteNulaException(String message) {
        super(message);
    }
    
}
