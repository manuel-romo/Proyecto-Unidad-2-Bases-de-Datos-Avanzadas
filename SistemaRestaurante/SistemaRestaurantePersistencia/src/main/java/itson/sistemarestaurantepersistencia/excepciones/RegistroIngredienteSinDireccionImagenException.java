
package itson.sistemarestaurantepersistencia.excepciones;


/**
 * Clase que representa una excepción lanzada cuando al registrar un ingrediente,
 * se comprueba que la dirección de imagen tiene valor nulo.
 * Utilizado por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class RegistroIngredienteSinDireccionImagenException extends Exception{
    
    /**
     * Constructor por defecto.
     */
    public RegistroIngredienteSinDireccionImagenException() {
    }

    /**
     * Constructor que recibe un mensaje como parámetro.
     * @param message Objeto String que representa el mensaje con el que se lanzará la
     * excepción.
     */
    public RegistroIngredienteSinDireccionImagenException(String message) {
        super(message);
    }
    
}
