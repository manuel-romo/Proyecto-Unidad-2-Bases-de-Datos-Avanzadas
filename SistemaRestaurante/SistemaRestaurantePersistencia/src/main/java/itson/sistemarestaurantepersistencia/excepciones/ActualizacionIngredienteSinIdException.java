
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al actualizar un ingrediente,
 * se comprueba que id con el que se obtiene, tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ActualizacionIngredienteSinIdException extends Exception{

    /**
     * Constructor por defecto
     */
    public ActualizacionIngredienteSinIdException() {
    }

    /**
     * Consutrctor que recibe el mensaje que tedrá la excepción
     * @param message Objeto String que representa el mensaje
     * de la nueva excepción
     */
    public ActualizacionIngredienteSinIdException(String message) {
        super(message);
    }
    
}
