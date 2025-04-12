
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que represena una excepción lanzada cuando se determina que ya existe un 
 * ingrediente con el mismo nombre y unidad que el que se intenta almacenar
 * que implementen la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class IngredienteMismoNombreUnidadExistenteException extends Exception{

    /**
     * Constructor por defecto.
     */
    public IngredienteMismoNombreUnidadExistenteException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public IngredienteMismoNombreUnidadExistenteException(String message) {
        super(message);
    }
    
}
