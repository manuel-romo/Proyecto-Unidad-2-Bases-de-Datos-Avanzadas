
package itson.sistemarestaurantepersistencia.excepciones;


public class RegistroIngredienteSinCantidadException extends Exception{

    public RegistroIngredienteSinCantidadException() {
    }

    public RegistroIngredienteSinCantidadException(String message) {
        super(message);
    }
    
}
