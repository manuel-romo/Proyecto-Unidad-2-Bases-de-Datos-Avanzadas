
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IdIngredienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinCantidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinUnidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.UnidadIngredienteNulaException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import java.util.List;


public interface IIngredientesBO{
    
    public abstract Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreIngredienteInvalidoException, 
            CantidadIngredienteInvalidaException, 
            IngredienteYaExisteException,
            IngredienteSinUnidadException,
            IngredienteSinNombreException,
            IngredienteSinDireccionImagenException,
            IngredienteSinCantidadException;
            
    public abstract List<Ingrediente> consultarIngredientes();
    
    public abstract Ingrediente consultarIngrediente(Long idIngrediente) 
            throws IngredienteConsultadoNoExisteException,
            IdIngredienteNuloException;
    
    public abstract List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente)
            throws NombreIngredienteNuloException;
   
    public abstract List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente)
            throws UnidadIngredienteNulaException;
    
    public abstract void actualizarIngrediente(IngredienteActualizadoDTO ingredienteActualizadoDTO) 
            throws NombreIngredienteInvalidoException,
            CantidadIngredienteInvalidaException,
            NombreIngredienteInvalidoException,
            IngredienteSinUnidadException,
            IngredienteSinDireccionImagenException,
            IngredienteYaExisteException,
            IngredienteNoExisteException,
            IngredienteSinIdException,
            IngredienteSinNombreException,
            IngredienteSinCantidadException;
    
}
