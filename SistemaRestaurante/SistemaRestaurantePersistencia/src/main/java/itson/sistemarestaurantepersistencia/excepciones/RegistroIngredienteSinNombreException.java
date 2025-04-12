
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al registrar un ingrediente,
 * se comprueba que el valor del parámetro nombre tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class RegistroIngredienteSinNombreException extends Exception{

    /**
     * Constructor por defecto.
     */
    public RegistroIngredienteSinNombreException() {
    }

    /**
     * Constructor que recibe un mensaje como parámetro.
     * @param message Objeto String que representa el mensaje con el que se lanzará la
     * excepción.
     */
    public RegistroIngredienteSinNombreException(String message) {
        super(message);
    }
    
}
