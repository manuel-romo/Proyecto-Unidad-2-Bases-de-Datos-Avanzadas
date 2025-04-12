
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.MesaNuevaComandaNulaException;
import java.util.List;


public interface IComandasBO {
    
    public abstract List<Comanda> consultarComandas();
    
    public Comanda registrarComanda(Mesa mesaNuevaComanda) throws MesaNuevaComandaNulaException;
    
}
