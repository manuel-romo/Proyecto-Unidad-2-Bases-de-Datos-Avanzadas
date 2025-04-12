
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al registrar un ingrediente,
 * se comprueba que el valor del parámetro habilitado tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class RegistroIngredienteSinHabilitadoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public RegistroIngredienteSinHabilitadoException() {
    }

    /**
     * Constructor que recibe un mensaje como parámetro.
     * @param message Objeto String que representa el mensaje con el que se lanzará la
     * excepción.
     */
    public RegistroIngredienteSinHabilitadoException(String message) {
        super(message);
    }
}
