
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Comanda;
import java.util.List;


public interface IComandasBO {
    
    public abstract List<Comanda> consultarComandas();
}
