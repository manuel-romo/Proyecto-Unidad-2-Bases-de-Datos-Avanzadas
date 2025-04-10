
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Mesa;
import java.util.List;


public interface IMesasDAO {
    
    public abstract List<Mesa> consultarMesasDisponibles();
}
