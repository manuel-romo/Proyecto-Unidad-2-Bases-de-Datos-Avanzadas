
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantepersistencia.IComandasDAO;
import java.util.List;

public class ComandasBO implements IComandasBO{
    private IComandasDAO comandasDAO;

    public ComandasBO(IComandasDAO comandasDAO) {
        this.comandasDAO = comandasDAO;
    }

    @Override
    public List<Comanda> consultarComandas() {
        
        return comandasDAO.consultarComandas();
        
    }
    
    
    
    
}
