
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteBuscadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinCantidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinUnidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import java.util.List;


public class IngredientesBO implements IIngredientesBO{
    
    private IIngredientesDAO ingredientesDAO;
    
    public IngredientesBO(IIngredientesDAO ingredientesDAO) {
        this.ingredientesDAO = ingredientesDAO;
    }

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreIngredienteInvalidoException, 
            CantidadIngredienteInvalidaException, 
            IngredienteYaExisteException,
            IngredienteSinUnidadException,
            IngredienteSinNombreException,
            IngredienteSinDireccionImagenException,
            IngredienteSinCantidadException{
        
        if(nuevoIngredienteDTO.getNombre() == null || nuevoIngredienteDTO.getNombre().isEmpty()){
            throw new NombreIngredienteInvalidoException("Debe ingresar un nombre de ingrediente.");
        }
        
        if(nuevoIngredienteDTO.getCantidad() == null){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        if(nuevoIngredienteDTO.getUnidad().equals(UnidadIngrediente.PIEZA.toString()) && 
                nuevoIngredienteDTO.getCantidad() == Math.floor(nuevoIngredienteDTO.getCantidad())){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad entera para la unidad de piezas.");
        }
        
        try{
            return ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO);
            
        } catch(IngredienteMismoNombreUnidadExistenteException ex){
            throw new IngredienteYaExisteException("Ya existe en ingrediente con el mismo nombre y unidad.");
        } catch (RegistroIngredienteSinUnidadException ex) {
            throw new IngredienteSinUnidadException("El ingrediente no tiene una unidad.");
        } catch (RegistroIngredienteSinNombreException ex) {
            throw new IngredienteSinNombreException("El ingrediente no tiene nombre.");
        } catch (RegistroIngredienteSinDireccionImagenException ex) {
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección de imagen.");
        } catch (RegistroIngredienteSinCantidadException ex) {
            throw new IngredienteSinCantidadException("El ingrediente no tiene cantidad.");
        }
        
     
    }

    @Override
    public List<Ingrediente> consultarIngredientes() {
        
        List<Ingrediente> listaIngredientesConsultados = ingredientesDAO.consultarIngredientes();
        
        return listaIngredientesConsultados;
    }
    
    @Override
    public Ingrediente consultarIngrediente(Long idIngrediente) throws IngredienteBuscadoNoExisteException{
        
        Ingrediente ingredienteConsultado;
        try {
            ingredienteConsultado = ingredientesDAO.consultarIngrediente(idIngrediente);
        } catch (IngredienteNoExisteException ex) {
            throw new IngredienteBuscadoNoExisteException("No existe el Id del ingrediente buscado.");
        }
        
        return ingredienteConsultado;
    }
    
    
    
    
    
    
}
