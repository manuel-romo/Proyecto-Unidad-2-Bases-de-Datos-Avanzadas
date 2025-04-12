
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
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
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaExistenciasSinIdIngredienteException;
import itson.sistemarestaurantepersistencia.excepciones.ProductosHabilitadosException;
import itson.sistemarestaurantepersistencia.excepciones.EliminacionIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinHabilitadoException;
import java.util.List;

/**
 * 
 * Interfaz de DAO que declara métodos que involucran lógica de acceso a datos para
 * objetos de tipo Ingrediente
 * 
 * @author Manuel Romo López
 */
public interface IIngredientesDAO {
    
    /**
     * Método que permite el registro de un nuevo objeto de tipo Ingrediente.
     * @param nuevoIngredienteDTO Objeto DTO que contiene la información necesaria
     * para el registro del nuevo Ingrediente.
     * @return Objeto Ingrediente, es el nuevo objeto Ingrediente que fue persistido
     * en la base de datos.
     * @throws IngredienteMismoNombreUnidadExistenteException Se lanza si tanto el
     * nombre como la unidad del nuevo Ingrediente a registrar, tienen los mismos
     * valores que los mismos atributos de otro Ingrediente ya registrado.
     * @throws RegistroIngredienteSinUnidadException Se lanza si el objeto UnidadIngrediente
     * que tendrá el nuevo objeto Ingrediente, tiene valor nulo.
     * @throws RegistroIngredienteSinNombreException Se lanza si el objeto String que representa 
     * el nombre que tendrá el nuevo Ingrediente a registrar tiene valor nulo.
     * @throws RegistroIngredienteSinHabilitadoException Se lanza si el objeto Boolean que representa 
     * el estado de habilitado que tendrá el nuevo Ingrediente a registrar, tiene valor nulo.
     * @throws RegistroIngredienteSinCantidadException Se lanza si el objet Float
     * cantidad que tendrá el nuevo Ingrediente a registrar tiene valor nulo.
     * @throws RegistroIngredienteSinDireccionImagenException Se lanza si el objeto
     * String que contiene la dirección de imagen del nuevo Ingrediente, tiene valor
     * nulo.
     * 
     */
    public abstract Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws IngredienteMismoNombreUnidadExistenteException,
            RegistroIngredienteSinUnidadException,
            RegistroIngredienteSinNombreException,
            RegistroIngredienteSinHabilitadoException,
            RegistroIngredienteSinCantidadException,
            RegistroIngredienteSinDireccionImagenException;
    
    /**
     * Método que permite obtener la lista de objetos Ingrediente alamcenados.
     * @return Objeto {@literal List<Ingrediente>} que contiene la lista de 
     * ojbetos Ingrediente almacenados.
     */
    public abstract List<Ingrediente> consultarIngredientes();
    
    /**
     * Método que permite obtener un objeto Ingrediente almacenado, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * @param idIngrediente Objeto Long que representa el id del Ingrediente buscado.
     * @return Objeto Ingrediente que tiene el id del paráemetro.
     * @throws IngredienteNoExisteException Se lanza si no existe un objeto Ingrediente
     * almacenado con el valor de id del parámetrto.
     * @throws ConsultaIngredienteSinIdException Se lanza si el valor del parámetro
     * idIngrediente es nulo.
     */
    public abstract Ingrediente consultarIngrediente(Long idIngrediente) 
            throws IngredienteNoExisteException,
            ConsultaIngredienteSinIdException;
    
    /**
     * Método que permite obtener la lista de Ingredientes que incluyan
     * en el valor de su campo nombre, el valor de nombre del parámtro.
     * @param nombreIngrediente Objeto String que representa el nombre de los
     * Ingredientes buscados.
     * @return Objeto {@literal List<Ingrediente>}, es la lista de objetos
     * Ingrediente que incluyen el valor del parámetro nombreIngrediente dentro
     * del valor de su atributo nombre.
     * @throws ConsultaIngredienteSinNombreException Se lanza si el nombre 
     * recibido tiene valor nulo.
     */
    public abstract List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente) 
            throws ConsultaIngredienteSinNombreException;
    
     /**
     * Método que permite obtener la lista de Ingredientes cuyo valor
     * en el atributo unidad incluya valor del parámetro.
     * @param unidadIngrediente Objeto String que representa el valor
     * de los Ingredientes a buscar.
     * @return Objeto {@literal List<Ingrediente>} que representa la lista de 
     * objetos Ingrediente cuyo valor en el atributo unidad incluya el valor del
     * del parámetro.
     * @throws ConsultaIngredienteSinUnidadException Se lanza si el objeto recibido
     * que representa la unidad de los Ingredientes a buscar tiene valor nulo.
     */
    public abstract List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente)
            throws ConsultaIngredienteSinUnidadException;
    
    /**
     * Método que permite actualizar los valores de los atributos de un objeto Ingrediente almacenado.
     * @param ingredienteActualizadoDTO Objeto DTO que contiene los nuevos valores
     * de los atributos del objeto Ingrediente a modificar.
     * @throws ActualizacionIngredienteSinNombreException Se lanza si el valor del
     * Objeto Sring que representa el nuevo nombre de Ingrediente recibido es nulo.
     * @throws ActualizacionIngredienteSinUnidadException Se lanza si el valor del
     * Objeto UnidadIngrediente que representa la nueva unidad de Ingrediente recibido es nulo.
     * @throws ActualizacionIngredienteSinCantidadException Se lanza si el valor del
     * Objeto Float que representa la nueva cantidad de Ingrediente recibido es nulo.
     * @throws ActualizacionIngredienteSinDireccionImagenException Se lanza si el valor del
     * Objeto String que representa la nueva direccion de Ingrediente recibido es nulo.
     * @throws ActualizacionIngredienteSinIdException Se lanza si el id de Ingrediente
     * a actualizar recibido tiene valor nulo.
     * @throws ActualizacionIngredienteSinHabilitadoException
     * @throws IngredienteNoExisteException Se lanza si el valor del
     * Objeto Boolean que indica el estado de habilitado del nuevo Ingrediente 
     * recibido es nulo.
     * @throws IngredienteMismoNombreUnidadExistenteException Se lanza si existe 
     * otro ingrediente que tiene los mismos valores de nombre y unidad a los
     * recibidos.
     */
    public abstract void actualizarIngrediente(IngredienteActualizadoDTO ingredienteActualizadoDTO)
            throws ActualizacionIngredienteSinNombreException, 
            ActualizacionIngredienteSinUnidadException,
            ActualizacionIngredienteSinCantidadException,
            ActualizacionIngredienteSinDireccionImagenException,
            ActualizacionIngredienteSinIdException,
            ActualizacionIngredienteSinHabilitadoException,
            IngredienteNoExisteException,
            IngredienteMismoNombreUnidadExistenteException;   
    
    /**
     * Método que permite obtener la cantidad en inventario del Ingrediente con el id del parámetro.
     * @param idIngrediente Objeto Long que representa el id del ingrediente a 
     * obtener su disponibilidad.
     * @return Objeto Float, la cantidad del ingrediente disponible en inventario.
     * @throws ConsultaExistenciasSinIdIngredienteException Se lanza
     * si el id del Ingrediente recibido es nulo.
     * @throws IngredienteNoExisteException Se lanza si no existe un Ingrediente
     * con el id del parámetro.
     */
    public abstract Float consultarDisponibilidadIngrediente(Long idIngrediente) 
            throws ConsultaExistenciasSinIdIngredienteException,
            IngredienteNoExisteException;
    
    /**
     * Método que permite deshabilitar el Ingrediente con el id
     * del parámetro
     * @param idIngrediente Objeto Long que representa el id del Ingrediente
     * a deshabilitar
     * @throws EliminacionIngredienteSinIdException Se lanza si el id recibido
     * tiene valor nulo,
     * @throws ProductosHabilitadosException Se lanza 
     * si el Ingrediente a deshabilitar es utilizado por objetos Producto registrados.
     */
    public void deshabilitarIngrediente(Long idIngrediente)
            throws EliminacionIngredienteSinIdException, 
            ProductosHabilitadosException;
    
    /**
     * Método utilizado para eliminar todos los registros de Ingrediente accedidospor el sistema.
     * Utilizado sólo para realizar pruebas.
     */
    public abstract void eliminarTodosIngredientes();
}
