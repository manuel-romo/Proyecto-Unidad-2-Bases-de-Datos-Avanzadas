package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.dtos.ClienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoClienteDTO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoCorreoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoCorreoTelefonoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoTelefonoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinCorreoException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinTelefonoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteFormatoInvalidoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinCorreoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinTelefonoException;
import java.util.List;
/**
 * Clase Interfaz DAO que incluye los métodos necesarios
 * Para el módulo de clientes
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public interface IClientesDAO {
    
    /**
     * Método abstracto para registrar un nuevo cliente
     * @param nuevoClienteDTO Representa un objeto de tipo nuevoClienteDTO
     * @return Objeto de tipo cliente
     * @throws RegistroClienteSinNombreException
     * @throws RegistroClienteSinTelefonoException
     * @throws RegistroClienteSinCorreoException
     * @throws ClienteMismoCorreoExistenteException
     * @throws ClienteMismoTelefonoExistenteException 
     * @throws RegistroClienteFormatoInvalidoException
     */
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO)
            throws RegistroClienteSinNombreException,
            RegistroClienteSinTelefonoException,
            RegistroClienteSinCorreoException,
            RegistroClienteFormatoInvalidoException,
            ClienteMismoCorreoExistenteException,
            ClienteMismoTelefonoExistenteException;
    
    /**
     * Método abstracto para consultar a todos los clientes
     * @return Lista de clientes
     */
    public abstract List<Cliente> consultarClientes();
    
    /**
     * Método abstracto para consultar clientes por id
     * @param idCliente Representa el id del cliente
     * @return Objeto de tipo cliente
     * @throws ClienteNoExisteException
     * @throws ConsultaClienteSinIdException 
     */
    public abstract Cliente consultarCliente(Long idCliente)
            throws ClienteNoExisteException,
            ConsultaClienteSinIdException;
    
    /**
     * Método abstracto para consultar clientes por nombre
     * @param nombreCliente Representa el nombre del cliente
     * @return Lista de clientes
     * @throws ConsultaClienteSinNombreException 
     */
    public abstract List<Cliente> consultarClientesNombre(String nombreCliente)
            throws ConsultaClienteSinNombreException;
    
    /**
     * Método abstracto para consultar clientes por teléfono
     * @param telefonoCliente Representa el teléfono del cliente
     * @return Objeto de tipo cliente
     * @throws ConsultaClienteSinTelefonoException 
     * @throws ClienteNoExisteException
     */
    public abstract Cliente consultarClientesTelefono(String telefonoCliente)
            throws ConsultaClienteSinTelefonoException,
            ClienteNoExisteException;
    
    /**
     * Método abstracto para consultar clientes por correo electrónico
     * @param correoCliente Representa el correo electrónico del cliente
     * @return Objeto de tipo cliente
     * @throws ConsultaClienteSinCorreoException 
     * @throws ClienteNoExisteException
     */
    public abstract Cliente consultarClientesCorreo(String correoCliente)
            throws ConsultaClienteSinCorreoException,
            ClienteNoExisteException;
    
    public abstract int consultarVisitasCliente(Long idCliente)
            throws ConsultaClienteSinIdException;
    
    public abstract float obtenerGastoTotalComandasCliente(Long idCliente)
            throws ConsultaClienteSinIdException;
    
    /**
     * Método abstracto para actualizar los datos de un cliente
     * @param clienteActualizadoDTO Representa un objeto de tipo clienteActualizadoDTO
     * @throws RegistroClienteSinNombreException
     * @throws RegistroClienteSinCorreoException
     * @throws RegistroClienteSinTelefonoException
     * @throws ClienteMismoCorreoExistenteException
     * @throws ClienteNoExisteException
     * @throws RegistroClienteSinIdException
     * @throws ActualizacionClienteSinIdException 
     * @throws ClienteMismoTelefonoExistenteException
     * @throws RegistroClienteFormatoInvalidoException
     */
    public abstract void actualizarCliente(ClienteActualizadoDTO clienteActualizadoDTO)
            throws RegistroClienteSinNombreException,
            RegistroClienteSinCorreoException,
            RegistroClienteSinTelefonoException,
            ClienteMismoCorreoExistenteException,
            ClienteNoExisteException,
            RegistroClienteSinIdException,
            ActualizacionClienteSinIdException,
            ClienteMismoTelefonoExistenteException,
            RegistroClienteFormatoInvalidoException;
    
    /**
     * Método abstracto para eliminar clientes
     * @param idCliente Representa el id del cliente
     * @throws ConsultaClienteSinIdException
     */
    public abstract void eliminarCliente(Long idCliente)
            throws ConsultaClienteSinIdException;
    
}