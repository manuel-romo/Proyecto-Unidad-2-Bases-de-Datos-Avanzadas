
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import java.util.List;


public class IngredientesBO implements IIngredientesBO{
    
    private IIngredientesDAO ingredientesDAO;

    public IngredientesBO(IIngredientesDAO ingredientesDAO) {
        this.ingredientesDAO = ingredientesDAO;
    }

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreIngredienteInvalidoException, CantidadIngredienteInvalidaException, IngredienteYaExisteException {
        
        if(nuevoIngredienteDTO.getNombre() == null || nuevoIngredienteDTO.getNombre().isEmpty()){
            throw new NombreIngredienteInvalidoException("Debe ingresar un nombre de ingrediente.");
        }
        
        if(nuevoIngredienteDTO.getCantidadCadena() == null || nuevoIngredienteDTO.getCantidadCadena().isEmpty()){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        try{
            Float cantidadFloat = Float.valueOf(nuevoIngredienteDTO.getCantidadCadena());
            nuevoIngredienteDTO.setCantidadFloat(cantidadFloat);
            
        } catch(NumberFormatException ex){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        
        try{
            return ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO);
            
        } catch(IngredienteMismoNombreUnidadExistenteException ex){
            throw new IngredienteYaExisteException("Ya existe en ingrediente con el mismo nombre y unidad.");
        }
        
     
    }

    @Override
    public List<Ingrediente> consultarIngredientes() {
        return null;
    }
    
    
    
    
    
    
}
