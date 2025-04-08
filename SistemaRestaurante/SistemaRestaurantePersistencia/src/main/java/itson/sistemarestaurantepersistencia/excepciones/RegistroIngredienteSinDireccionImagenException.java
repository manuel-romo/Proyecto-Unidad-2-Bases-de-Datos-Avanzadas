
package itson.sistemarestaurantepersistencia.excepciones;


/**
 * Clase que representa una excepción lanzada cuando se intenta registrar un
 * Ingrediente sin Dirección de imagen, en la clase IngredientesDAO.
 * 
 * @author 
 */
public class RegistroIngredienteSinDireccionImagenException extends Exception{
    
    /**
     * Constructor por defecto.
     */
    public RegistroIngredienteSinDireccionImagenException() {
    }

    /**
     * Constructor que recibe un mensaje como parámetro.
     * @param message Objeto String que representa el mensaje con el que se lanzará la
     * excepción.
     */
    public RegistroIngredienteSinDireccionImagenException(String message) {
        super(message);
    }
    
}
