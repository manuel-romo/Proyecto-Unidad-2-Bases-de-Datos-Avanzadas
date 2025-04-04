
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import java.util.List;


public interface IIngredientesBO{
    
    public abstract Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreIngredienteInvalidoException, CantidadIngredienteInvalidaException, IngredienteYaExisteException;
            
    public abstract List<Ingrediente> consultarIngredientes();
}
