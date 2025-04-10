
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que represena una excepción lanzada cuando se realiza una consulta por
 * nombre de ingrediente y el valor del nombre es nulo. Para las clases 
 * que implementen la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ConsultaIngredienteSinNombreException extends Exception {

    /**
     * Constructor por defecto.
     */
    public ConsultaIngredienteSinNombreException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public ConsultaIngredienteSinNombreException(String message) {
        super(message);
    }
    
}
