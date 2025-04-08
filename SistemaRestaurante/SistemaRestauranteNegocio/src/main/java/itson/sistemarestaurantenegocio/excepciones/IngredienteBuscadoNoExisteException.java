
package itson.sistemarestaurantenegocio.excepciones;


public class IngredienteBuscadoNoExisteException extends Exception{

    public IngredienteBuscadoNoExisteException() {
    }

    public IngredienteBuscadoNoExisteException(String message) {
        super(message);
    }
    
}
