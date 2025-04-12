
package itson.sistemarestaurantepersistencia.excepciones;

/**
 * Clase que representa una excepción lanzada cuando se realiza la consulta
 * de existencias de ingredientes para un producto, y el valor del Id del
 * producto es nulo. Utilizado por las clases que implementan la interfaz
 * {@link IProducosDAO}
 * 
 */
public class ConsultaExistenciasSinIdProductoException extends Exception{

    /**
     * Constructor por defecto
     */
    public ConsultaExistenciasSinIdProductoException() {
    }

    /**
     * Contructor que recibe el mesnaje de la excepción.
     * @param message Objeto String que representa el mensje de la excepción.
     */
    public ConsultaExistenciasSinIdProductoException(String message) {
        super(message);
    }
    
}
