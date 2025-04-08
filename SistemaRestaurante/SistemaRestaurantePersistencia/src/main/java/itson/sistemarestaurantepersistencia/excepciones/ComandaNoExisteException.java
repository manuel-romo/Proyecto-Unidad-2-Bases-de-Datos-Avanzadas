
package itson.sistemarestaurantepersistencia.excepciones;


public class ComandaNoExisteException extends Exception{

    public ComandaNoExisteException() {
    }

    public ComandaNoExisteException(String message) {
        super(message);
    }
    
    
}
