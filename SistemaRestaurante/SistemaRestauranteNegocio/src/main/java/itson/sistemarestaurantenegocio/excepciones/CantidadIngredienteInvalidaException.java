
package itson.sistemarestaurantenegocio.excepciones;


/**
 * Clase que represena una excepción lanzada cuando se comprueba que la cantidad de
 * ingrediente recibido para realizar alguna operación de negocio es
 * inválida. Utilizada por las clases que implementan la interfaz {@link IIngredientesBO}
 * 
 * @author Manuel Romo López
 */
public class CantidadIngredienteInvalidaException extends Exception{

    /**
     * Contructor por defecto
     */
    public CantidadIngredienteInvalidaException() {
    }

    /**
     * Constructor que recibe el mensaje que tendrá la excepción
     * @param message Objeto String que representa el mensaje que tendrá
     * la excepción
     */
    public CantidadIngredienteInvalidaException(String message) {
        super(message);
    }
    
}
