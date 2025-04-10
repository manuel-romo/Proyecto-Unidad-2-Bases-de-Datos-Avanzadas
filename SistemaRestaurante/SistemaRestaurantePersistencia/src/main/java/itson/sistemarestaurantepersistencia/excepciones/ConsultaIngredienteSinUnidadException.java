
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que represena una excepción lanzada cuando se realiza una consulta por
 * unidad de ingrediente y el valor de la unidad es nulo. Para las clases 
 * que implementen la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ConsultaIngredienteSinUnidadException extends Exception{

    /**
     * Constructor por defecto.
     */
    public ConsultaIngredienteSinUnidadException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public ConsultaIngredienteSinUnidadException(String message) {
        super(message);
    }
    
}
