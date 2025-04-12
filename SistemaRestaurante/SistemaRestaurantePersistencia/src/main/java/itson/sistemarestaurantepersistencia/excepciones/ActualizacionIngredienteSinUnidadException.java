
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al actualizar un ingrediente,
 * se comprueba que la nueva unidad, tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ActualizacionIngredienteSinUnidadException extends Exception{

    /**
     * Constructor por defecto
     */
    public ActualizacionIngredienteSinUnidadException() {
    }

    /**
     * Consutrctor que recibe el mensaje que tedrá la excepción
     * @param message Objeto String que representa el mensaje
     * de la nueva excepción
     */
    public ActualizacionIngredienteSinUnidadException(String message) {
        super(message);
    }
    
}
