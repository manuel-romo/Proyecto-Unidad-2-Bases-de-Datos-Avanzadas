
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantepersistencia.excepciones.ConsultarMesaSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.MesaNoExisteException;
import java.util.List;


public interface IMesasDAO {
    
    public abstract Mesa consultarMesaId(Long idMesa) 
            throws MesaNoExisteException, 
            ConsultarMesaSinIdException ;
    
    public abstract List<Mesa> consultarMesasDisponibles();
}
