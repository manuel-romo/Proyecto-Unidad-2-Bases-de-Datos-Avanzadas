package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.dtos.ClienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoClienteDTO;
import itson.sistemarestaurantenegocio.excepciones.ClienteConsultaSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ClienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.ClienteRegistroSinIDException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinCorreoException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinTelefonoException;
import itson.sistemarestaurantenegocio.excepciones.CorreoClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.CorreoClienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoRegistroClienteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoRegistroClienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.IdClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.MismoCorreoException;
import itson.sistemarestaurantenegocio.excepciones.NombreClienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.NombreClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.RegistroClienteMismoTelefonoCorreoExistenteException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoClienteYaExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteNoExisteException;
import java.util.List;
/**
 * Clase Interfaz BO que incluye los métodos necesarios
 * Para el módulo de clientes
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public interface IClientesBO {
    //TODO terminar eliminarCliente

    /**
     * Método abstracto para registrar un nuevo cliente
     * @param nuevoClienteDTO Representa un objeto de tipo nuevoClienteDTO para su registro
     * @return Objeto de tipo cliente
     * @throws ClienteSinNombreException
     * @throws NombreClienteInvalidoException
     * @throws ClienteSinCorreoException
     * @throws ClienteSinTelefonoException
     * @throws CorreoClienteYaExisteException
     * @throws TelefonoClienteYaExisteException
     * @throws FormatoRegistroClienteException 
     */
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO)
            throws ClienteSinNombreException, 
            NombreClienteInvalidoException,
            ClienteSinCorreoException, 
            ClienteSinTelefonoException, 
            CorreoClienteYaExisteException,
            TelefonoClienteYaExisteException,
            FormatoRegistroClienteException,
            RegistroClienteMismoTelefonoCorreoExistenteException;
    
    /**
     * Método abstracto para consultar todos los clientes
     * @return Lista de clientes
     */
    public abstract List<Cliente> consultarClientes();
    
    /**
     * Método abstracto para consultar cliente por id
     * @param idCliente Representa el id del cliente
     * @return Objeto de tipo cliente
     * @throws ClienteConsultadoNoExisteException
     * @throws IdClienteNuloException 
     */
    public abstract Cliente consultarCliente(Long idCliente)
            throws ClienteConsultadoNoExisteException,
            IdClienteNuloException;
    
    /**
     * Método abstracto para consultar clientes por nombre
     * @param nombreCliente Representa el nombre del cliente
     * @return Lista de clientes
     * @throws NombreClienteNuloException 
     */
    public abstract List<Cliente> consultarClientesNombre(String nombreCliente)
            throws NombreClienteNuloException;
    
    /**
     * Método abstracto para consultar clientes por teléfono
     * @param telefonoCliente Representa el teléfono del cliente
     * @return Lista de clientes
     * @throws TelefonoClienteNuloException 
     */
    public abstract List<Cliente> consultarClientesTelefono(String telefonoCliente)
            throws TelefonoClienteNuloException;
    
    /**
     * Método abstracto para consultar clientes por correo electrónico
     * @param correoCliente Representa el correo del cliente
     * @return Lista de clientes
     * @throws CorreoClienteNuloException 
     */
    public abstract List<Cliente> consultarClientesCorreo(String correoCliente)
            throws CorreoClienteNuloException;
    
    /**
     * Método abstracto para actualizar la información de un cliente
     * @param clienteActualizadoDTO Representa un objeto de tipo clienteActualizadoDTO para realizar su actualización
     * @throws ClienteSinNombreException
     * @throws NombreClienteInvalidoException
     * @throws ClienteSinCorreoException
     * @throws ClienteSinTelefonoException
     * @throws CorreoClienteYaExisteException
     * @throws ClienteNoExisteException
     * @throws ActualizacionClienteSinIdException
     * @throws ClienteSinIdException
     * @throws MismoCorreoException
     * @throws ClienteRegistroSinIDException
     * @throws FormatoRegistroClienteException 
     */
    public abstract void actualizarCliente(ClienteActualizadoDTO clienteActualizadoDTO)
            throws ClienteSinNombreException, 
            NombreClienteInvalidoException,
            ClienteSinCorreoException, 
            ClienteSinTelefonoException, 
            CorreoClienteYaExisteException,
            ClienteNoExisteException,
            ActualizacionClienteSinIdException,
            ClienteSinIdException,
            MismoCorreoException,
            ClienteRegistroSinIDException,
            FormatoRegistroClienteException;
    
    /**
     * Método abstracto para la eliminación de un cliente
     */
    public abstract void eliminarCliente();
}