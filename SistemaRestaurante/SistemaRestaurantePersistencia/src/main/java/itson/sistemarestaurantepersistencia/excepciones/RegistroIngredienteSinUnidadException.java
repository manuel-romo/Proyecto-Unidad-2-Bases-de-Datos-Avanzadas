
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando al registrar un ingrediente,
 * se comprueba que el valor del parámetro unidad tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class RegistroIngredienteSinUnidadException extends Exception{

    /**
     * Constructor por defecto.
     */
    public RegistroIngredienteSinUnidadException() {
    }

    /**
     * Constructor que recibe un mensaje como parámetro.
     * @param message Objeto String que representa el mensaje con el que se lanzará la
     * excepción.
     */
    public RegistroIngredienteSinUnidadException(String message) {
        super(message);
    }
    
}
