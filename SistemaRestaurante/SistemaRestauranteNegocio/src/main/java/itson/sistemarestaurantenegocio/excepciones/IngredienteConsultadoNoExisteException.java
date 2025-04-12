
package itson.sistemarestaurantenegocio.excepciones;

/** 
 * Clase que represena una excepción lanzada cuando se comprueba que un ingrediente
 * consultado para realizar alguna operación de negocio no existe. 
 * Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class IngredienteConsultadoNoExisteException extends Exception{

    /**
     * Contructor por defecto
     */
    public IngredienteConsultadoNoExisteException() {
    }

    /**
     * Constructor que recibe el mensaje que tendrá la excepción
     * @param message Objeto String que representa el mensaje que tendrá
     * la excepción
     */
    public IngredienteConsultadoNoExisteException(String message) {
        super(message);
    }
    
}
