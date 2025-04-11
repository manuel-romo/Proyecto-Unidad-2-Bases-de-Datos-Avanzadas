
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.IdMesaNuloException;
import itson.sistemarestaurantenegocio.excepciones.MesaConsultadaNoExisteException;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import itson.sistemarestaurantepersistencia.excepciones.ConsultarMesaSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.MesaNoExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MesasBO implements IMesasBO{

    private IMesasDAO mesasDAO;
    
    public MesasBO(IMesasDAO mesasDAO) {
        this.mesasDAO = mesasDAO;
    }
 
    
    @Override
    public List<Mesa> consultarMesasDisponibles() {
        List<Mesa> mesasDisponibles = mesasDAO.consultarMesasDisponibles();
        
        return mesasDisponibles;
    }

    @Override
    public Mesa consultarMesaId(Long idMesa) 
            throws IdMesaNuloException,
            MesaConsultadaNoExisteException,
            IdMesaNuloException{
        
        if(idMesa == null){
            throw new IdMesaNuloException("El Id de la mesa es nulo.");
        }
        
        try {
            Mesa mesaConsultada = mesasDAO.consultarMesaId(idMesa);
            
            return mesaConsultada;
            
        } catch (MesaNoExisteException ex) {
            throw new MesaConsultadaNoExisteException("No existe una mesa con el Id recibido.");
        } catch (ConsultarMesaSinIdException ex) {
            throw new IdMesaNuloException("El Id de la mesa tiene valor nulo.");
        }
    }

    
    
    
}
