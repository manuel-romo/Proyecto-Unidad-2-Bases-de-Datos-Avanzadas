
package itson.sistemarestaurantepersistencia.excepciones;


public class RegistroIngredienteSinNombreException extends Exception{

    public RegistroIngredienteSinNombreException() {
    }

    public RegistroIngredienteSinNombreException(String message) {
        super(message);
    }
    
}
