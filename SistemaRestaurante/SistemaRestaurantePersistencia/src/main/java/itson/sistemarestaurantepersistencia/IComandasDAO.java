
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantepersistencia.excepciones.RegistroComandaSinMesaException;
import java.util.List;


public interface IComandasDAO {
    
    public abstract Comanda registrarComanda(Mesa mesaNuevaComanda) 
            throws RegistroComandaSinMesaException;
    
    public abstract List<Comanda> consultarComandas();
    
}
