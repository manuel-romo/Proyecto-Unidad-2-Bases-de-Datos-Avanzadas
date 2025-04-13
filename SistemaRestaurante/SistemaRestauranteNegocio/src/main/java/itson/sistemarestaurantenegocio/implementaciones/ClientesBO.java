package itson.sistemarestaurantenegocio.implementaciones;

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
import itson.sistemarestaurantenegocio.interfaces.IClientesBO;
import itson.sistemarestaurantepersistencia.IClientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoCorreoExistenteException;
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
 * Clase BO que representa la lógica de negocio y validaciones
 * Para cada método del módulo de clientes
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class ClientesBO implements IClientesBO {

    /**
     * Declaración de variables de utilidad para la clase BO y validaciones
     */
    private IClientesDAO clientesDAO;
    private final int LONGITUD_TELEFONO = 10;
    private final int LONGITUD_CORRE0_ELECTRONICO = 320;
    private final int LONGITUD_NOMBRE = 50;
    private final int LONGITUD_APELLIDO_PATERNO = 50;
    private final int LONGITUD_APELLIDO_MATERNO = 50;

    /**
     * Método constructor que recibe un clienteDAO
     * @param clientesDAO Representa un clienteDAO
     */
    public ClientesBO(IClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    /**
     * Método para registrar un nuevo cliente
     * @param nuevoClienteDTO Representa un objeto de tipo nuevoClienteDTO para el registro de un cliente
     * @return Objeto de tipo cliente
     */
    @Override
    public Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO) 
            throws ClienteSinNombreException, 
            NombreClienteInvalidoException,
            ClienteSinCorreoException, 
            ClienteSinTelefonoException, 
            CorreoClienteYaExisteException,
            TelefonoClienteYaExisteException,
            FormatoRegistroClienteException,
            RegistroClienteMismoTelefonoCorreoExistenteException{
        
        if(nuevoClienteDTO.getNombre() == null || nuevoClienteDTO.getNombre().isBlank()){
            throw new NombreClienteInvalidoException("Debe ingresar un nombre para el cliente.");
        }
        
        if(nuevoClienteDTO.getApellidoPaterno() == null || nuevoClienteDTO.getApellidoPaterno().isBlank()){
            throw new NombreClienteInvalidoException("Debe ingresar el apellido paterno del cliente");
        }
        
        if(nuevoClienteDTO.getApellidoMaterno() == null || nuevoClienteDTO.getApellidoMaterno().isBlank()){
            throw new NombreClienteInvalidoException("Debe ingresar el apellido materno del cliente");
        }
        
        if(nuevoClienteDTO.getCorreoElectronico()== null || nuevoClienteDTO.getCorreoElectronico().isBlank()){
            throw new ClienteSinCorreoException("Debe ingresar un correo para el cliente cliente.");
        }
        
        if(nuevoClienteDTO.getTelefono()== null || nuevoClienteDTO.getTelefono().isBlank()){
            throw new ClienteSinTelefonoException("Debe ingresar un numero de telefono para el cliente.");
        }
        
        if(nuevoClienteDTO.getNombre().length() > LONGITUD_NOMBRE){
            throw new FormatoRegistroClienteException("La longitud del nombre del cliente excede los limites.");
        }
        
        if(nuevoClienteDTO.getApellidoPaterno().length() > LONGITUD_APELLIDO_PATERNO){
            throw new FormatoRegistroClienteException("La longitud del apellido paterno del cliente excede los limites.");
        }
        
        if(nuevoClienteDTO.getApellidoMaterno().length() > LONGITUD_APELLIDO_MATERNO){
            throw new FormatoRegistroClienteException("La longitud del apellido materno del cliente excede los limites.");
        }
        
        if(nuevoClienteDTO.getCorreoElectronico().length() > LONGITUD_CORRE0_ELECTRONICO){
            throw new FormatoRegistroClienteException("La longitud del correo electronico excede los limites.");
        }
        
        if(nuevoClienteDTO.getTelefono().length() > LONGITUD_TELEFONO){
            throw new FormatoRegistroClienteException("La longitud del correo electronico excede los limites.");
        }

        try {
            return clientesDAO.registrarCliente(nuevoClienteDTO);
        } catch (ClienteMismoCorreoExistenteException ex) {
            throw new CorreoClienteYaExisteException("El correo ingresado ya se encuentra registrado.");
        } catch (ClienteMismoTelefonoExistenteException ex) {
            throw new TelefonoClienteYaExisteException("El telefono ingresado ya se encuentra registrado.");
        } catch (RegistroClienteSinCorreoException ex) {
            throw new ClienteSinCorreoException("El cliente no tiene un correo.");
        } catch (RegistroClienteSinNombreException ex) {
            throw new ClienteSinNombreException("El cliente no tiene un nombre.");
        } catch (RegistroClienteSinTelefonoException ex) {
            throw new ClienteSinTelefonoException("El cliente no tiene teléfono.");
        } catch (RegistroClienteFormatoInvalidoException ex) {
            throw new FormatoRegistroClienteException("El formato de registro no es válido");
        }      
    }

    /**
     * Método para consultar todos los clientes
     * @return Lista de clientes
     */
    @Override
    public List<Cliente> consultarClientes() {
        List<Cliente> listaClientesConsultados = clientesDAO.consultarClientes();
        
        return listaClientesConsultados;
    }
    
    /**
     * Método para consultar el número de visitas que a tenido un cliente al restaurante
     * @param idCliente Representa el Id del cliente
     * @return Número de visitas del clinte
     * @throws ClienteSinIdException
     * @throws ConsultaClienteSinIdException 
     */
    @Override
    public int consultarVisitasCliente(Long idCliente)
            throws ClienteSinIdException,
            ConsultaClienteSinIdException{
            
        int numeroVisitasCliente;
        
        try{
            numeroVisitasCliente = clientesDAO.consultarVisitasCliente(idCliente);
        } catch(ConsultaClienteSinIdException ex){
            throw new ClienteSinIdException("El Id del cliente tiene valor nulo.");
        }
        
        return numeroVisitasCliente;
    }

    /**
     * Método para calcular el gasto total en comandas del cliente
     * @param idCliente Representa el id del cliente
     * @return Gasto total en comandas del cliente
     * @throws ClienteSinIdException
     * @throws ConsultaClienteSinIdException 
     */
    @Override
    public float calcularGastoTotalComandasCliente(Long idCliente)
            throws ClienteSinIdException,
            ConsultaClienteSinIdException{
        
        float gastoTotalComandasCliente;
        
        try{
            gastoTotalComandasCliente = clientesDAO.obtenerGastoTotalComandasCliente(idCliente);
        } catch(ConsultaClienteSinIdException ex){
            throw new ClienteSinIdException("El Id del cliente tiene valor nulo.");
        }
        
        return gastoTotalComandasCliente;
    }
    
    /**
     * Método que permite consultar clientes por Id
     * @param idCliente Representa el id del cliente
     * @return El cliente consultado en cuestión en base a su Id
     * @throws ClienteConsultadoNoExisteException
     * @throws IdClienteNuloException 
     */
    @Override
    public Cliente consultarCliente(Long idCliente) 
            throws ClienteConsultadoNoExisteException, IdClienteNuloException {
        
        Cliente clienteConsultado;
        try {
            clienteConsultado = clientesDAO.consultarCliente(idCliente);
        } catch (ClienteNoExisteException ex) {
            throw new ClienteConsultadoNoExisteException("No existe un cliente con el Id recibido.");
        } catch (ConsultaClienteSinIdException ex) {
            throw new IdClienteNuloException("El Id del cliente tiene valor nulo.");
        }
        
        return clienteConsultado;
    }

    /**
     * Método para consultar clientes por nombre
     * @param nombreCliente Representa el nombre del cliente
     * @return lista con los clientes encontrador por nombre
     * @throws NombreClienteNuloException 
     */
    @Override
    public List<Cliente> consultarClientesNombre(String nombreCliente) 
            throws NombreClienteNuloException {
        
        try {
            List<Cliente> listaClientesConsultados = clientesDAO.consultarClientesNombre(nombreCliente);
            
            return listaClientesConsultados;
        } catch (ConsultaClienteSinNombreException ex) {
            throw new NombreClienteNuloException("El nombre del cliente tiene valor nulo.");
        }
    }

    /**
     * Método para consultar clientes por teléfono
     * @param telefonoCliente Representa el teléfono del cliente
     * @return Lista de los clientes encontrados por teléfono
     * @throws TelefonoClienteNuloException 
     */
    @Override
    public Cliente consultarClientesTelefono(String telefonoCliente) 
            throws TelefonoClienteNuloException,
            ClienteInexistenteException{
        
        try{
            Cliente clienteConsultado = clientesDAO.consultarClientesTelefono(telefonoCliente);
            return clienteConsultado;
        } catch(ConsultaClienteSinTelefonoException ex){
            throw new TelefonoClienteNuloException("El telefono del cliente tiene valor nulo");
        } catch (ClienteNoExisteException ex) {
            throw new ClienteInexistenteException("");
        }
    }

    /**
     * Método para consultar clientes por correo electrónico
     * @param correoCliente Representa el correo del cliente
     * @return Lista de clientes encontrados por correo electrónico
     * @throws CorreoClienteNuloException 
     */
    @Override
    public Cliente consultarClientesCorreo(String correoCliente) 
            throws CorreoClienteNuloException ,
            ClienteInexistenteException{
        
        try{
            Cliente clienteConsultado = clientesDAO.consultarClientesCorreo(correoCliente);
            return clienteConsultado;
        } catch(ConsultaClienteSinCorreoException ex){
            throw new CorreoClienteNuloException("El correo electronico del cliente tiene valor nulo");
        } catch (ClienteNoExisteException ex) {
            throw new ClienteInexistenteException("No existe cliente con el correo electronico proporcionado.");
        }
    }

    /**
     * Método para actualizar la información de un cliente
     * @param clienteActualizadoDTO Representa un objeto de tipo ClienteActualizadoDTO
     * @throws ClienteSinNombreException
     * @throws ClienteSinCorreoException
     * @throws ClienteSinTelefonoException
     * @throws CorreoClienteYaExisteException 
     * @throws ClienteMismoTelefonoExistenteException
     * @throws MismoTelefonoException
     */
    @Override
    public void actualizarCliente(ClienteActualizadoDTO clienteActualizadoDTO) 
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
            MismoTelefonoException{
        
        if(clienteActualizadoDTO.getNombre() == null || clienteActualizadoDTO.getNombre().isBlank()){
            throw new NombreClienteInvalidoException("Debe ingresar un nombre para el cliente.");
        }
        
        if(clienteActualizadoDTO.getApellidoPaterno() == null || clienteActualizadoDTO.getApellidoPaterno().isBlank()){
            throw new NombreClienteInvalidoException("Debe ingresar el apellido paterno del cliente");
        }
        
        if(clienteActualizadoDTO.getApellidoMaterno() == null || clienteActualizadoDTO.getApellidoMaterno().isBlank()){
            throw new NombreClienteInvalidoException("Debe ingresar el apellido materno del cliente");
        }
        
        if(clienteActualizadoDTO.getCorreoElectronico()== null || clienteActualizadoDTO.getCorreoElectronico().isBlank()){
            throw new ClienteSinCorreoException("Debe ingresar un correo para el cliente cliente.");
        }
        
        if(clienteActualizadoDTO.getTelefono()== null || clienteActualizadoDTO.getTelefono().isBlank()){
            throw new ClienteSinTelefonoException("Debe ingresar un numero de telefono para el cliente.");
        }
        
        if(clienteActualizadoDTO.getNombre().length() > LONGITUD_NOMBRE){
            throw new FormatoRegistroClienteException("La longitud del nombre del cliente excede los limites.");
        }
        
        if(clienteActualizadoDTO.getApellidoPaterno().length() > LONGITUD_APELLIDO_PATERNO){
            throw new FormatoRegistroClienteException("La longitud del apellido paterno del cliente excede los limites.");
        }
        
        if(clienteActualizadoDTO.getApellidoMaterno().length() > LONGITUD_APELLIDO_MATERNO){
            throw new FormatoRegistroClienteException("La longitud del apellido materno del cliente excede los limites.");
        }
        
        if(clienteActualizadoDTO.getCorreoElectronico().length() > LONGITUD_CORRE0_ELECTRONICO){
            throw new FormatoRegistroClienteException("La longitud del correo electronico excede los limites.");
        }
        
        if(clienteActualizadoDTO.getTelefono().length() > LONGITUD_TELEFONO){
            throw new FormatoRegistroClienteException("La longitud del correo electronico excede los limites.");
        }

        try {
            clientesDAO.actualizarCliente(clienteActualizadoDTO);
        } catch (ClienteNoExisteException ex) {
            throw new ClienteNoExisteException("El cliente que se intentó actualizar no existe.");
        } catch (ActualizacionClienteSinIdException ex) {
            throw new ClienteSinIdException("El cliente no tiene id");
        } catch (RegistroClienteSinCorreoException ex) {
            throw new ClienteSinCorreoException("El cliente no tiene un correo.");
        } catch (RegistroClienteSinNombreException ex) {
            throw new ClienteSinNombreException("El cliente no tiene un nombre.");
        } catch (RegistroClienteSinTelefonoException ex) {
            throw new ClienteSinTelefonoException("El cliente no tiene teléfono.");
        } catch (ClienteMismoCorreoExistenteException ex) {
            throw new MismoCorreoException("El correo ingresado ya esta registrado.");
        } catch (RegistroClienteSinIdException ex) {
            throw new ClienteRegistroSinIDException("El cliente no tiene id.");
        } catch (ClienteMismoTelefonoExistenteException ex){
            throw new MismoTelefonoException("El teléfono ingresado ya está registrado.");
        }       
    }

    /**
     * Método para eliminar clientes
     * @param idCliente
     * @throws ClienteSinIdException
     * @throws ConsultaClienteSinIdException 
     */
    @Override
    public void eliminarCliente(Long idCliente) 
            throws ClienteSinIdException,
            ConsultaClienteSinIdException{
        
        try{
            clientesDAO.eliminarCliente(idCliente);
        }catch(ConsultaClienteSinIdException ex){
           throw new ClienteSinIdException("El cliente no tiene id");
        }
    }
   
}