
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantenegocio.excepciones.ComandaSinMesaException;
import itson.sistemarestaurantenegocio.excepciones.MesaNuevaComandaNulaException;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantepersistencia.IComandasDAO;
import itson.sistemarestaurantepersistencia.excepciones.RegistroComandaSinMesaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComandasBO implements IComandasBO{
    private IComandasDAO comandasDAO;

    public ComandasBO(IComandasDAO comandasDAO) {
        this.comandasDAO = comandasDAO;
    }
    
    @Override
    public Comanda registrarComanda(Mesa mesaNuevaComanda) throws MesaNuevaComandaNulaException{
        
        if(mesaNuevaComanda == null){
            throw new MesaNuevaComandaNulaException("La mesa recibida tiene valor nulo.");
        }

        try {
            
            Comanda comanda = comandasDAO.registrarComanda(mesaNuevaComanda);
            
            return comanda;
            
        } catch (RegistroComandaSinMesaException ex) {
            throw new MesaNuevaComandaNulaException("La mesa recibida tiene valor nulo.");
        }

        
    }
    
    @Override
    public List<Comanda> consultarComandas() {
        
        return comandasDAO.consultarComandas();
        
    }

    
    
}
