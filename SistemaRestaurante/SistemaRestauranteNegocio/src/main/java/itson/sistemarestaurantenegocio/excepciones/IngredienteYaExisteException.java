
package itson.sistemarestaurantenegocio.excepciones;


public class IngredienteYaExisteException extends Exception{

    public IngredienteYaExisteException() {
    }

    public IngredienteYaExisteException(String message) {
        super(message);
    }
    
}
