
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando se desea eliminar un
 * ingrediente con un id que tiene valor nulo. Utilizada por las clases que
 * implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class EliminacionIngredienteSinIdException extends Exception{

    /**
     * Constructor por defecto
     */
    public EliminacionIngredienteSinIdException() {
    }

    /**
     * Contructor que recibe el mensaje de la excepción.
     * @param message Objeto String que represena el mensaje con el que se
     * lanzará la excepción.
     */
    public EliminacionIngredienteSinIdException(String message) {
        super(message);
    }
    
}
