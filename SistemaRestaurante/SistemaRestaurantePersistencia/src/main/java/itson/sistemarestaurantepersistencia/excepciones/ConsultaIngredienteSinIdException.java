
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que represena una excepción lanzada cuando se realiza una consulta por
 * Id de ingrediente y el valor del Id es nulo. Para las clases 
 * que implementen la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ConsultaIngredienteSinIdException extends Exception{

    /**
     * Constructor por defecto.
     */
    public ConsultaIngredienteSinIdException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public ConsultaIngredienteSinIdException(String message) {
        super(message);
    }
    
}
