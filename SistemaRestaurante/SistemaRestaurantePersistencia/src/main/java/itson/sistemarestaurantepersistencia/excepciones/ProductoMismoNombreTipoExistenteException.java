
package itson.sistemarestaurantepersistencia.excepciones;

public class ProductoMismoNombreTipoExistenteException extends Exception {

    public ProductoMismoNombreTipoExistenteException() {
    }

    public ProductoMismoNombreTipoExistenteException(String message) {
        super(message);
    }
    
}
