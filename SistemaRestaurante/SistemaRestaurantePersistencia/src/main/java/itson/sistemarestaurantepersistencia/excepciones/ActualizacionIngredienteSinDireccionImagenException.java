
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al actualizar un ingrediente,
 * se comprueba que la nueva dirección de imagen tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ActualizacionIngredienteSinDireccionImagenException extends Exception {

    /**
     * Constructor por defecto
     */
    public ActualizacionIngredienteSinDireccionImagenException() {
    }

    /**
     * Consutrctor que recibe el mensaje que tedrá la excepción
     * @param message Objeto String que representa el mensaje
     * de la nueva excepción
     */
    public ActualizacionIngredienteSinDireccionImagenException(String message) {
        super(message);
    }
    
}
