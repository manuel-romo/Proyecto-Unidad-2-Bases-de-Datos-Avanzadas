
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import java.util.List;


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

    
    
    
}
