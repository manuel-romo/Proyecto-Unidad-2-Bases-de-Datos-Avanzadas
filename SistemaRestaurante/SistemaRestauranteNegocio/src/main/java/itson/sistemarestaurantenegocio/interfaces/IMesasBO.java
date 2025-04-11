
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.IdMesaNuloException;
import itson.sistemarestaurantenegocio.excepciones.MesaConsultadaNoExisteException;
import java.util.List;


public interface IMesasBO {
    
    public abstract List<Mesa> consultarMesasDisponibles();
    
    public abstract Mesa consultarMesaId(Long idMesa) 
            throws MesaConsultadaNoExisteException,
            IdMesaNuloException;
}
