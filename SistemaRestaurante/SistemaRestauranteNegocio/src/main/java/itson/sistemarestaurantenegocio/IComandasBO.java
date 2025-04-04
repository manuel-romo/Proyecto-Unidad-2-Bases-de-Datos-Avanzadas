
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Comanda;
import java.util.List;


public interface IComandasBO {
    
    public abstract List<Comanda> consultarComandas();
}
