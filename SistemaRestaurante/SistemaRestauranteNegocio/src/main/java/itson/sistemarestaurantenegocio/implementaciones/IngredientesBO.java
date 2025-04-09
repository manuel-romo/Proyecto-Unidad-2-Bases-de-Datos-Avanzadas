
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteBuscadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinCantidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinUnidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        
        if(nuevoIngredienteDTO.getUnidad() == null){
            throw new IngredienteSinUnidadException("El ingrediente no tiene una unidad.");
        }
        
        if(nuevoIngredienteDTO.getCantidad() == null){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        if(nuevoIngredienteDTO.getDireccionImagen() == null){
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección de imagen.");
        }
        
        if(nuevoIngredienteDTO.getUnidad().equals(UnidadIngrediente.PIEZA.toString()) && 
                nuevoIngredienteDTO.getCantidad() == Math.floor(nuevoIngredienteDTO.getCantidad())){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad entera para la unidad de piezas.");
        }
        
        try{
            return ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO);
            
        } catch(IngredienteMismoNombreUnidadExistenteException ex){
            throw new IngredienteYaExisteException("Ya existe un ingrediente con el mismo nombre y unidad.");
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
    public List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente){
        
        List<Ingrediente> listaIngredientesConsultados = ingredientesDAO.consultarIngredientesNombre(nombreIngrediente);
        
        return listaIngredientesConsultados;
    }
    
    @Override
    public List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente){
        
        List<Ingrediente> listaIngredientesConsultados = ingredientesDAO.consultarIngredientesUnidad(unidadIngrediente);
        
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
    
    
    @Override
    public void actualizarIngrediente(IngredienteActualizadoDTO ingredienteActualizadoDTO) 
            throws NombreIngredienteInvalidoException,
            CantidadIngredienteInvalidaException,
            NombreIngredienteInvalidoException,
            IngredienteSinUnidadException,
            IngredienteSinDireccionImagenException,
            IngredienteYaExisteException,
            IngredienteNoExisteException,
            IngredienteSinIdException,
            IngredienteSinNombreException,
            IngredienteSinCantidadException{
        
        
        if(ingredienteActualizadoDTO.getNombre() == null || ingredienteActualizadoDTO.getNombre().isEmpty()){
            throw new NombreIngredienteInvalidoException("Debe ingresar un nombre de ingrediente.");
        }
        
        if(ingredienteActualizadoDTO.getUnidad() == null){
            throw new IngredienteSinUnidadException("El ingrediente no tiene una unidad.");
        }
        
        if(ingredienteActualizadoDTO.getCantidad() == null){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        if(ingredienteActualizadoDTO.getDireccionImagen() == null){
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección de imagen.");
        }
        
        if(ingredienteActualizadoDTO.getUnidad().equals(UnidadIngrediente.PIEZA.toString()) && 
                ingredienteActualizadoDTO.getCantidad() == Math.floor(ingredienteActualizadoDTO.getCantidad())){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad entera para la unidad de piezas.");
        }
        
        
        try {
            ingredientesDAO.actualizarIngrediente(ingredienteActualizadoDTO);
        } catch (IngredienteMismoNombreUnidadExistenteException ex) {
            throw new IngredienteYaExisteException("Ya existe un ingrediente con el mismo nombre y unidad.");
        } catch (IngredienteNoExisteException ex) {
            throw new IngredienteNoExisteException("El ingrediente que se intentó actualizar no existe.");
        } catch (ActualizacionIngredienteSinIdException ex) {
            throw new IngredienteSinIdException("El ingrediente no tiene id");
        } catch (RegistroIngredienteSinNombreException ex) {
            throw new IngredienteSinNombreException("El ingrediente no tiene un nombre.");
        } catch (RegistroIngredienteSinUnidadException ex) {
            throw new IngredienteSinUnidadException("El ingrediente no tiene unidad.");
        } catch (RegistroIngredienteSinCantidadException ex) {
            throw new IngredienteSinCantidadException("El ingrediente no tiene cantidad.");
        } catch (RegistroIngredienteSinDireccionImagenException ex) {
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección.");
        }    
    }
    
    
    
    
    
    
}
