
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que represena una excepción lanzada cuando se realiza una consulta o
 * actualización para un ingrediente y se determina que este no existe.
 * que implementen la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class IngredienteNoExisteException extends Exception{

    public IngredienteNoExisteException() {
    }

    public IngredienteNoExisteException(String message) {
        super(message);
    }
    
}
