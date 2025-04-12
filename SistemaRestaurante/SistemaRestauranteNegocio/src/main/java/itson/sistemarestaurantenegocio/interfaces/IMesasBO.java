
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.IdMesaNuloException;
import itson.sistemarestaurantenegocio.excepciones.MesaConsultadaNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.MesaSinNumeroException;
import itson.sistemarestaurantenegocio.excepciones.NumeroMesaInvalidoException;
import java.util.List;


public interface IMesasBO {
    
    public abstract List<Mesa> consultarMesas();
    public abstract List<Mesa> consultarMesasDisponibles();
    
    public abstract Mesa consultarMesaId(Long idMesa) 
            throws MesaConsultadaNoExisteException,
            IdMesaNuloException;
    
    public abstract Mesa registrarMesa(Integer numero)
            throws NumeroMesaInvalidoException,
            MesaSinNumeroException;
}
