
package itson.sistemarestaurantepersistencia.excepciones;


public class MesaNoExisteException extends Exception{

    public MesaNoExisteException() {
    }

    public MesaNoExisteException(String message) {
        super(message);
    }
    
}
