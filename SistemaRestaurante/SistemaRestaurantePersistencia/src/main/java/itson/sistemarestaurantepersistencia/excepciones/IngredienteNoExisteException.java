
package itson.sistemarestaurantepersistencia.excepciones;


public class IngredienteNoExisteException extends Exception{

    public IngredienteNoExisteException() {
    }

    public IngredienteNoExisteException(String message) {
        super(message);
    }
    
}
