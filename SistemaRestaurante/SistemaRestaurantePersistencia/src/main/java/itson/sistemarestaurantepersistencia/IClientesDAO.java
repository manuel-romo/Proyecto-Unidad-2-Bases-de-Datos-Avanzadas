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
    //TODO revisar actualizarCliente y terminar eliminarCliente
    
    /**
     * Método abstracto para registrar un nuevo cliente
     * @param nuevoClienteDTO Representa un objeto de tipo nuevoClienteDTO
     * @return Objeto de tipo cliente
     * @throws RegistroClienteSinNombreException
     * @throws RegistroClienteSinTelefonoException
     * @throws RegistroClienteSinCorreoException
     * @throws ClienteMismoCorreoExistenteException
     * @throws ClienteMismoTelefonoExistenteException 
     */
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO)
            throws RegistroClienteSinNombreException,
            RegistroClienteSinTelefonoException,
            RegistroClienteSinCorreoException,
            ClienteMismoCorreoExistenteException,
            ClienteMismoTelefonoExistenteException,
            RegistroClienteFormatoInvalidoException,
            ClienteMismoCorreoTelefonoExistenteException;
    
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
     * @return Lista de clientes
     * @throws ConsultaClienteSinTelefonoException 
     */
    public abstract List<Cliente> consultarClientesTelefono(String telefonoCliente)
            throws ConsultaClienteSinTelefonoException;
    
    /**
     * Método abstracto para consultar clientes por correo electrónico
     * @param correoCliente Representa el correo electrónico del cliente
     * @return Lista de clientes
     * @throws ConsultaClienteSinCorreoException 
     */
    public abstract List<Cliente> consultarClientesCorreo(String correoCliente)
            throws ConsultaClienteSinCorreoException;
    
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
     */
    public abstract void actualizarCliente(ClienteActualizadoDTO clienteActualizadoDTO)
            throws RegistroClienteSinNombreException,
            RegistroClienteSinCorreoException,
            RegistroClienteSinTelefonoException,
            ClienteMismoCorreoExistenteException,
            ClienteNoExisteException,
            RegistroClienteSinIdException,
            ActualizacionClienteSinIdException;
    
    /**
     * Método abstracto para eliminar clientes
     * @param idCliente Representa el id del cliente
     */
    public abstract void eliminarCliente(Long idCliente);
    
}