
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando se intenta deshabilitar
 * un producto, pero se comprueba que tiene objetos IngredienteProducto
 * relacionados que apuntan a un objeto Producto que no está deshabilitado.
 * Utilizada por las clases que implementan la interfaz {@link IIngredientesDAO}
 * 
 * @author Manuel Romo López
 */
public class ProductosHabilitadosException extends Exception{

    /**
     * Constructor por defecto.
     */
    public ProductosHabilitadosException() {
    }

    /**
     * Constructor que recibe el mensaje de la excepción.
     * @param message Objeto String que representa el mensaje con el que la excepción es
     * lanzada.
     */
    public ProductosHabilitadosException(String message) {
        super(message);
    }
    
}
