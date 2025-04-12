
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.IdMesaNuloException;
import itson.sistemarestaurantenegocio.excepciones.MesaConsultadaNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.MesaSinNumeroException;
import itson.sistemarestaurantenegocio.excepciones.NumeroMesaInvalidoException;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import itson.sistemarestaurantepersistencia.excepciones.ConsultarMesaSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.MesaMismoNumeroException;
import itson.sistemarestaurantepersistencia.excepciones.MesaNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroMesaSinNumeroException;
import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;


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

    @Override
    public Mesa registrarMesa(Integer numero) 
            throws NumeroMesaInvalidoException,
            MesaSinNumeroException{
        
        try {
            Mesa mesa = mesasDAO.registrarMesa(numero);
            
            return mesa;
            
        } catch (MesaMismoNumeroException ex) {
            throw new NumeroMesaInvalidoException("Ya existe una mesa con el número recibido.");
            
        } catch (RegistroMesaSinNumeroException ex) {
            throw new MesaSinNumeroException("La mesa a registrar no tiene un número.");
        }
        
        
        
    }

    @Override
    public List<Mesa> consultarMesas() {
       
        List<Mesa> mesas = mesasDAO.consultarMesas();
        
        return mesas;
    }
    
    

}
