
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Mesa;
import java.util.List;


public interface IMesasBO {
    
    public abstract List<Mesa> consultarMesasDisponibles();
}
