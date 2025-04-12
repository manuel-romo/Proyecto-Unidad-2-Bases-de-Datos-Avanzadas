
package itson.sistemarestaurantenegocio.excepciones;


public class FormatoContraseniaInvalidoException extends Exception{

    /**
    * Contructor por defecto
    */
    public FormatoContraseniaInvalidoException() {
    }

    /**
     * Constructor que recibe el mensaje que tendrá la excepción
     * @param message Objeto String que representa el mensaje que tendrá
     * la excepción
     */
    public FormatoContraseniaInvalidoException(String message) {
        super(message);
    }
    
    
}
