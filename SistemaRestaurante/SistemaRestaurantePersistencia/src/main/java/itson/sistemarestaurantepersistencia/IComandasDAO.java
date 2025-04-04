
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Comanda;
import java.util.List;


public interface IComandasDAO {
    
    public List<Comanda> consultarComandas();
    
}
