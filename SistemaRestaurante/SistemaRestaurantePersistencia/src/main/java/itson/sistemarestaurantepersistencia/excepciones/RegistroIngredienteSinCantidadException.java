
package itson.sistemarestaurantepersistencia.excepciones;


/**
 * Clase que representa una excepción lanzada cuando al registrar un ingrediente,
 * se comprueba que la cantidad tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class RegistroIngredienteSinCantidadException extends Exception{

        /**
     * Constructor por defecto.
     */
    public RegistroIngredienteSinCantidadException() {
    }

    /**
     * Constructor que recibe un mensaje como parámetro.
     * @param message Objeto String que representa el mensaje con el que se lanzará la
     * excepción.
     */
    public RegistroIngredienteSinCantidadException(String message) {
        super(message);
    }
    
}
