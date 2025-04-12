
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al actualizar un ingrediente,
 * se comprueba que el nuvo valor de habilitado tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ActualizacionIngredienteSinHabilitadoException extends Exception{
    /**
     * Constructor por defecto
     */
    public ActualizacionIngredienteSinHabilitadoException() {
    }

    /**
     * Consutrctor que recibe el mensaje que tedrá la excepción
     * @param message Objeto String que representa el mensaje
     * de la nueva excepción
     */
    public ActualizacionIngredienteSinHabilitadoException(String message) {
        super(message);
    }
}
