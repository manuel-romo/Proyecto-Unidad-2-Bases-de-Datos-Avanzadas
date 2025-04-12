
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IdIngredienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinCantidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinHabilitadoException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinUnidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.UnidadIngredienteNulaException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import java.util.List;


public interface IIngredientesBO{
    
    /**
     * Método que permite registrar un nuevo ingrediente.
     * 
     * @param nuevoIngredienteDTO Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo ingrediente.
     * 
     * @return Objeto Ingrediente, representa el ingrediente registrado.
     * 
     * @throws NombreIngredienteInvalidoException Se lanza si se comprueba que el
     * nombre recibido es nulo o tiene formato inválido.
     * 
     * @throws CantidadIngredienteInvalidaException Se lanza si se comprueba que la
     * cantidad de ingrediente es nula o tiene formato inválido.
     * 
     * @throws IngredienteYaExisteException Se lanza si se comprueba que ya existe
     * un ingrediente con el mismo nombre y unidad de medida.
     * 
     * @throws IngredienteSinUnidadException Se lanza si se comprueba que el valor
     * de la unidad de medidad del nuevo ingrediente es nula.
     * 
     * @throws IngredienteSinNombreException Se lanza si se comprueba que el valor
     * del nombre de la unidad de medida del nuevo ingrediente es nula.
     * 
     * @throws IngredienteSinDireccionImagenException Se lanza si se comprueba 
     * que la dirección del nuevo ingrediente es nula.
     * 
     * @throws IngredienteSinCantidadException Se lanza si se comprueba 
     * que la cantidad de ingrediente en inventario del nuveo ingrediente es nula.
     * 
     * @throws IngredienteSinHabilitadoException Se lanza si se comprueba que el 
     * valor del estado habilitado del nuevo ingrediente es nulo.
     */
    public abstract Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreIngredienteInvalidoException, 
            CantidadIngredienteInvalidaException, 
            IngredienteYaExisteException,
            IngredienteSinUnidadException,
            IngredienteSinNombreException,
            IngredienteSinDireccionImagenException,
            IngredienteSinCantidadException,
            IngredienteSinHabilitadoException;
            
    /**
     * Método que permite obtener la lista de Ingrediente registrados.
     * 
     * @return Objeto {@literal List<Ingrediente>} que contiene los objetos Ingrediente
     * consultados.
     */
    public abstract List<Ingrediente> consultarIngredientes();
    
     /**
     * Método que permite obtener un objeto Ingrediente registrado que tenga el id
     * del parámetro.
     * 
     * @param idIngrediente Objeto Long que representa el id del Ingrediente
     * a consultar.
     * 
     * @return Objeto Integer que contiene el id del parámetro.
     * 
     * @throws IngredienteConsultadoNoExisteException Se lanza si se comprueba que
     * no existe un Ingrediente con el id del parámetro.
     * 
     * @throws IdIngredienteNuloException Se lanza si el valor del id del parámetro
     * es nulo.
     */
    public abstract Ingrediente consultarIngrediente(Long idIngrediente) 
            throws IngredienteConsultadoNoExisteException,
            IdIngredienteNuloException;
    
    /**
     * Método que permite obtener una lista de objetos Ingrediente
     * que incluyen en el valor de su atributo nombre, el valor del nobre del 
     * parámetro.
     * 
     * @param nombreIngrediente Objeto String que representa el nombre del
     * ingrediente que se utilizará para obtener los Ingredientes.
     * 
     * @return Objeto {@literal List<Ingrediente>} con la lista de objetos
     * Ingrediente consultados.
     * 
     * @throws NombreIngredienteNuloException Se lanza si el nombre utilizado
     * para la consulta recibido tiene valor nulo.
     */
    public abstract List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente)
            throws NombreIngredienteNuloException;
   
    /**
     * Método que permite obtener una lista de objetos Ingrediente que contienen en su valor del
     * atributo unidad, el valor de la unidad del parámetro.
     * 
     * @param unidadIngrediente Objeto String que representa la unidad que se utilizará
     * para relizar la consulta de Ingredientes.
     * 
     * @return Objeto {@literal List<Ingrediente>},s es la lista de objetos 
     * de tipo Ingrediente consultados.
     * 
     * @throws UnidadIngredienteNulaException  Se lanza si la unidad del parámetro
     * tiene valor nulo.
     */
    public abstract List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente)
            throws UnidadIngredienteNulaException;
    
    /**
     * Método que permite actualizar los valores de los atributos de un Ingrediente registrado.
     * 
     * @param ingredienteActualizadoDTO Objeto DTO que contiene los valores actualizados para 
     * los atributos del objeto Ingrediente a actualizar. 
     * 
     * @throws NombreIngredienteInvalidoException Se lanza si el nuevo nombre para
     * el ingrediente tiene formato inválido.
     * 
     * @throws CantidadIngredienteInvalidaException Se lanza si el la nueva cantidad
     * para el ingrediente tiene formato inválido.
     * 
     * @throws IngredienteSinUnidadException Se lanza si el valor de la nueva unidad
     * tiene valor nulo.
     * 
     * @throws IngredienteSinDireccionImagenException Se lanza si el valor de la
     * direccion de imagen tiene valor nulo.
     * 
     * @throws IngredienteYaExisteException Se lanza si se comprueba que ya existe 
     * un ingrediente registrado con la misma cantidad y unidad a las ingresadas.
     * 
     * @throws IngredienteNoExisteException Se lanza si se comprueba que no existe
     * un ingrediente con el id recibido.
     * 
     * @throws IngredienteSinIdException Se lanza si se comprueba que el id del ingrediente
     * a actualizar es nulo.
     * 
     * @throws IngredienteSinNombreException Se lanza si el nuevo
     * nombre tiene valor nulo.
     * 
     * @throws IngredienteSinCantidadException Se lanza si la nueva cantidad tiene
     * valor nulo.
     * 
     * @throws IngredienteSinHabilitadoException Se lanza si el valor del estado
     * de habilitado tiene valor nulo.
     */
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
            IngredienteSinCantidadException,
            IngredienteSinHabilitadoException;
    
}
