
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
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
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinHabilitadoException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinUnidadException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinUnidadException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinHabilitadoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import java.util.List;

/**
 * Clase BO que implementa la interfaz {@link IIngredientesBO}, contiene la 
 * lógica de negocio necesaria para realizar operaciones con objetos de la clase
 * Ingrediente.
 * 
 * @author Manuel Romo López
 * 
 */
public class IngredientesBO implements IIngredientesBO{
    
    /**
     * Objeto que implementa la interfaz IIngredientesDAO, permite el acceso a
     * datos para objetos de la clase Ingredientes.
     */
    private IIngredientesDAO ingredientesDAO;
    /**
     * Dato int que representa la longitud máxima permitida para el nombre de un
     * ingrediente.
     */
    private int MAXIMO_CARACTERES_NOMBRE_INGREDIENTE = 100;
    
    /**
     * Contructor de la clase que recibe un objeto que implementa la interfaz
     * IIngredientesDAO.
     * @param ingredientesDAO Objeto que implementa la interfaz IIngredientesDAO,
     * permite el acceso a datos para objetos de la clase Ingredientes.
     */
    public IngredientesBO(IIngredientesDAO ingredientesDAO) {
        this.ingredientesDAO = ingredientesDAO;
    }

    /**
     * Implementación del método registrarIngrediente(), de la interfaz {@link IIngredientesBO}
     * que permite registrar un nuevo ingrediente.
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
    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws NombreIngredienteInvalidoException, 
            CantidadIngredienteInvalidaException, 
            IngredienteYaExisteException,
            IngredienteSinUnidadException,
            IngredienteSinNombreException,
            IngredienteSinDireccionImagenException,
            IngredienteSinCantidadException,
            IngredienteSinHabilitadoException{
        
        // Se valida que el nombre no sea nulo y no sean solo espacios en blanco.
        if(nuevoIngredienteDTO.getNombre() == null || nuevoIngredienteDTO.getNombre().isBlank()){
            throw new NombreIngredienteInvalidoException("Debe ingresar un nombre de ingrediente.");
        }
        
        if(nuevoIngredienteDTO.getNombre().length() > MAXIMO_CARACTERES_NOMBRE_INGREDIENTE){
            throw new NombreIngredienteInvalidoException("El nombde de ingrediente no debe sobrepasar los 100 caracteres.");
        }
        
        // Se valida que la unidad no sea nula.
        if(nuevoIngredienteDTO.getUnidad() == null){
            throw new IngredienteSinUnidadException("El ingrediente no tiene una unidad.");
        }
        
        // Se valida que la cantidad no sea nula.
        if(nuevoIngredienteDTO.getCantidad() == null){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        // Se valida que la dirección de imagen no sea nula.
        if(nuevoIngredienteDTO.getDireccionImagen() == null){
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección de imagen.");
        }
        
        // Se valiad que la cantidad ingresada no sea un número flotante si 
        // el tipo de ingrediente ingresado fue Pieza.
        if(nuevoIngredienteDTO.getUnidad().equals(UnidadIngrediente.PIEZA.toString()) && 
                nuevoIngredienteDTO.getCantidad() == Math.floor(nuevoIngredienteDTO.getCantidad())){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad entera para la unidad de piezas.");
        }
        
        try{
            // Se registra el ingrediente utilizando el objeto que implementa la interfaz
            // IIngredientesDAO.
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
        } catch (RegistroIngredienteSinHabilitadoException ex) {
            throw new IngredienteSinHabilitadoException("El ingrediente no tiene cantidad.");
        }
     
    }

    /**
     * Implementación del método consultarIngredientes() de la interfaz {@link IIngredientesBO},
     * permite obtener la lista de Ingrediente registrados.
     * 
     * @return Objeto {@literal List<Ingrediente>} que contiene los objetos Ingrediente
     * consultados.
     */
    @Override
    public List<Ingrediente> consultarIngredientes() {
        
        List<Ingrediente> listaIngredientesConsultados = ingredientesDAO.consultarIngredientes();
        
        return listaIngredientesConsultados;
    }
    
    /**
     * Implementación del método consultarIngrediente(), de la interfaz {@IIngredientesBO},
     * que permite obtener un objeto Ingrediente registrado que tenga el id
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
    @Override
    public Ingrediente consultarIngrediente(Long idIngrediente) 
            throws IngredienteConsultadoNoExisteException,
            IdIngredienteNuloException{
        
        Ingrediente ingredienteConsultado;
        try {
            ingredienteConsultado = ingredientesDAO.consultarIngrediente(idIngrediente);

        } catch (IngredienteNoExisteException ex) {
            throw new IngredienteConsultadoNoExisteException("No existe un ingrediente con el Id recibido.");
        } catch (ConsultaIngredienteSinIdException ex) {
            throw new IdIngredienteNuloException("El Id del ingrediente tiene valor nulo.");
        }
        
        return ingredienteConsultado;
    }
    
    /**
     * Implementación del método consultarIngredientesNombre(), de la interfaz
     * {@link IIngredientesBO}, que permite obtener una lista de objetos Ingrediente
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
    @Override
    public List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente) 
            throws NombreIngredienteNuloException{
        
        try {
            List<Ingrediente> listaIngredientesConsultados = ingredientesDAO.consultarIngredientesNombre(nombreIngrediente);
            
            return listaIngredientesConsultados;
        } catch (ConsultaIngredienteSinNombreException ex) {
            throw new NombreIngredienteNuloException("El nombre de ingrediente tiene valor nulo.");
        }
    }
    
    /**
     * Implementación del método consultarIngredientesUnidad(), de la interfaz {@link IIngredientesDAO},
     * permite obtener una lista de objetos Ingrediente que contienen en su valor del
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
    @Override
    public List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente) 
            throws UnidadIngredienteNulaException {
        
        try {
            List<Ingrediente> listaIngredientesConsultados = ingredientesDAO.consultarIngredientesUnidad(unidadIngrediente);
            
            return listaIngredientesConsultados;
        } catch (ConsultaIngredienteSinUnidadException ex) {
            throw new UnidadIngredienteNulaException("La unidad del ingrediente tiene valor nulo.");
        }
    }
    
    /**
     * Implementación del método actualizarIngrediente(), de la interfaz {@IIngredientesBO},
     * permite actualizar los valores de los atributos de un Ingrediente registrado.
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
            IngredienteSinCantidadException,
            IngredienteSinHabilitadoException{
        
        
        // Se valida que el nomrbe no sea nulo y que no sean solo espacios en blanco.
        if(ingredienteActualizadoDTO.getNombre() == null || ingredienteActualizadoDTO.getNombre().isBlank()){
            throw new NombreIngredienteInvalidoException("Debe ingresar un nombre de ingrediente.");
        }
        
        // Se valida que el número de caracteres del nombre no sobrepase el máximo especificado.
        if(ingredienteActualizadoDTO.getNombre().length() > MAXIMO_CARACTERES_NOMBRE_INGREDIENTE){
            throw new NombreIngredienteInvalidoException("El nombde de ingrediente no debe sobrepasar los 100 caracteres.");
        }
        
        // Se valida que la unidad no sea nula.
        if(ingredienteActualizadoDTO.getUnidad() == null){
            throw new IngredienteSinUnidadException("El ingrediente no tiene una unidad.");
        }
        
        // Se valida que la cantidad no sea nula.
        if(ingredienteActualizadoDTO.getCantidad() == null){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad para el ingrediente.");
        }
        
        // Se valida que la dirección de imagen no sea nula.
        if(ingredienteActualizadoDTO.getDireccionImagen() == null){
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección de imagen.");
        }
        
        // Se valida que la cantidad no sea un número flotante si el valor de la unidad del ingrediente es Pieza.
        if(ingredienteActualizadoDTO.getUnidad().equals(UnidadIngrediente.PIEZA.toString()) && 
                ingredienteActualizadoDTO.getCantidad() == Math.floor(ingredienteActualizadoDTO.getCantidad())){
            throw new CantidadIngredienteInvalidaException("Debe ingresar una cantidad entera para la unidad de piezas.");
        }
        
        
        try {
            
            // Se actualiza el ingrediente utilizando el objeto que implementa 
            // la interfaz IIngredientesDAO
            ingredientesDAO.actualizarIngrediente(ingredienteActualizadoDTO);
        } catch (IngredienteMismoNombreUnidadExistenteException ex) {
            throw new IngredienteYaExisteException("Ya existe un ingrediente con el mismo nombre y unidad.");
        } catch (IngredienteNoExisteException ex) {
            throw new IngredienteNoExisteException("El ingrediente que se intentó actualizar no existe.");
        } catch (ActualizacionIngredienteSinIdException ex) {
            throw new IngredienteSinIdException("El ingrediente no tiene id");
        } catch (ActualizacionIngredienteSinNombreException ex) {
            throw new IngredienteSinNombreException("El ingrediente no tiene un nombre.");
        } catch (ActualizacionIngredienteSinUnidadException ex) {
            throw new IngredienteSinUnidadException("El ingrediente no tiene unidad.");
        } catch (ActualizacionIngredienteSinCantidadException ex) {
            throw new IngredienteSinCantidadException("El ingrediente no tiene cantidad.");
        } catch (ActualizacionIngredienteSinDireccionImagenException ex) {
            throw new IngredienteSinDireccionImagenException("El ingrediente no tiene dirección.");
        } catch (ActualizacionIngredienteSinHabilitadoException ex) {
            throw new IngredienteSinHabilitadoException("El ingrediente no tiene valor de habilitado.");
        }    
    }
    
}
