
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import java.util.List;


public interface IIngredientesDAO {
    
    public abstract Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws IngredienteMismoNombreUnidadExistenteException,
            RegistroIngredienteSinUnidadException,
            RegistroIngredienteSinNombreException,
            RegistroIngredienteSinCantidadException,
            RegistroIngredienteSinDireccionImagenException;
    
    public abstract List<Ingrediente> consultarIngredientes();
    
    public abstract Ingrediente consultarIngrediente(Long idIngrediente) throws IngredienteNoExisteException;
    
    public abstract void actualizarIngrediente(IngredienteActualizadoDTO ingredienteActualizadoDTO)
            throws RegistroIngredienteSinNombreException,
            IngredienteMismoNombreUnidadExistenteException, 
            RegistroIngredienteSinUnidadException,
            RegistroIngredienteSinCantidadException,
            RegistroIngredienteSinDireccionImagenException,
            IngredienteNoExisteException,
            ActualizacionIngredienteSinIdException;
    
    
    public abstract List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente);
    
    public abstract List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente);

}
