package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.dtos.ClienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoClienteDTO;
import itson.sistemarestaurantenegocio.excepciones.ClienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.ClienteInexistenteException;
import itson.sistemarestaurantenegocio.excepciones.ClienteRegistroSinIDException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinCorreoException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.ClienteSinTelefonoException;
import itson.sistemarestaurantenegocio.excepciones.CorreoClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.CorreoClienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoRegistroClienteException;
import itson.sistemarestaurantenegocio.excepciones.IdClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.MismoCorreoException;
import itson.sistemarestaurantenegocio.excepciones.MismoTelefonoException;
import itson.sistemarestaurantenegocio.excepciones.NombreClienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.NombreClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.RegistroClienteMismoTelefonoCorreoExistenteException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoClienteNuloException;
import itson.sistemarestaurantenegocio.excepciones.TelefonoClienteYaExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoTelefonoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinIdException;
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
     * @throws RegistroClienteMismoTelefonoCorreoExistenteException
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
     * @throws ClienteInexistenteException
     */
    public abstract Cliente consultarClientesTelefono(String telefonoCliente)
            throws TelefonoClienteNuloException,
            ClienteInexistenteException;
    
    /**
     * Método abstracto para consultar clientes por correo electrónico
     * @param correoCliente Representa el correo del cliente
     * @return Lista de clientes
     * @throws CorreoClienteNuloException 
     * @throws ClienteInexistenteException
     */
    public abstract Cliente consultarClientesCorreo(String correoCliente)
            throws CorreoClienteNuloException,
            ClienteInexistenteException;
    
    /**
     * Método abstracto para consultar el número de visitas del cliente al restaurante
     * @param idCliente Representa el id del cliente
     * @return Número de visitas del cliente
     * @throws ClienteSinIdException
     * @throws ConsultaClienteSinIdException 
     */
    public abstract int consultarVisitasCliente(Long idCliente)
            throws ClienteSinIdException,
            ConsultaClienteSinIdException;
    
    /**
     * Método abstracto para calcular el gasto total en comandas de un cliente
     * @param idCliente Representa el id del cliente
     * @return Gasto total en comandas del cliente
     * @throws ClienteSinIdException
     * @throws ConsultaClienteSinIdException
     */
    public abstract float calcularGastoTotalComandasCliente(Long idCliente)
            throws ClienteSinIdException,
            ConsultaClienteSinIdException;
    
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
     * @throws ClienteMismoTelefonoExistenteException
     * @throws MismoTelefonoException
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
            FormatoRegistroClienteException,
            ClienteMismoTelefonoExistenteException,
            MismoTelefonoException;
    
    /**
     * Método abstracto para la eliminación de un cliente
     * @param idCliente
     * @throws ClienteSinIdException
     * @throws ConsultaClienteSinIdException 
     */
    public abstract void eliminarCliente(Long idCliente)
            throws ClienteSinIdException,
            ConsultaClienteSinIdException;
}