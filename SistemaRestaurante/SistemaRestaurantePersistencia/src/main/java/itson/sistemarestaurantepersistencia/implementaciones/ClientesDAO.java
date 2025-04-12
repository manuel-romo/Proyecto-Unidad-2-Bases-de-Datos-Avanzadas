package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.dtos.ClienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoClienteDTO;
import itson.sistemarestaurantepersistencia.IClientesDAO;
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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
/**
 * Clase DAO que representa la lógica de persistencia para los clientes
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class ClientesDAO implements IClientesDAO{

    /**
     * Declaración de variables de utilidad para la clase DAO y validaciones
     */
    private final int LONGITUD_TELEFONO = 10;
    private final int LONGITUD_CORRE0_ELECTRONICO = 320;
    private final int LONGITUD_NOMBRE = 50;
    private final int LONGITUD_APELLIDO_PATERNO = 50;
    private final int LONGITUD_APELLIDO_MATERNO = 50;
    
    //TODO método de registrarCliente pendiente
    
    
    /**
     * Método para registrar un nuevo cliente
     * @param nuevoClienteDTO Representa un objeto de tipo nuevoClienteDTO
     * @return Objeto de tipo cliente
     * @throws RegistroClienteSinNombreException
     * @throws RegistroClienteSinTelefonoException
     * @throws RegistroClienteSinCorreoException
     * @throws ClienteMismoCorreoExistenteException
     * @throws ClienteMismoTelefonoExistenteException
     * @throws RegistroClienteFormatoInvalidoException
     * @throws ClienteMismoCorreoTelefonoExistenteException 
     */
    @Override
    public Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO) 
            throws RegistroClienteSinNombreException,
            RegistroClienteSinTelefonoException,
            RegistroClienteSinCorreoException,
            ClienteMismoCorreoExistenteException,
            ClienteMismoTelefonoExistenteException,
            RegistroClienteFormatoInvalidoException,
            ClienteMismoCorreoTelefonoExistenteException{
        
        //Se valida que el nombre del cliente no sea nulo
        if(nuevoClienteDTO.getNombre() == null || nuevoClienteDTO.getNombre().isBlank()){
            throw new RegistroClienteSinNombreException("El cliente que se intentó registrar no tiene Nombre.");
        }
        
        //Se valida que el apellido paterno del cliente no sea nulo
        if(nuevoClienteDTO.getApellidoPaterno()== null || nuevoClienteDTO.getApellidoPaterno().isBlank()){
            throw new RegistroClienteSinNombreException("El cliente que se intentó registrar no tiene Apellido Paterno.");
        }
        
        //Se valiad que el apellido materno del cliente no sea nulo
        if(nuevoClienteDTO.getApellidoMaterno() == null || nuevoClienteDTO.getApellidoMaterno().isBlank()){
            throw new RegistroClienteSinNombreException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        //Se valida que el correo electrónico del cliente no sea nulo
        if(nuevoClienteDTO.getCorreoElectronico() == null || nuevoClienteDTO.getCorreoElectronico().isBlank()){
            throw new RegistroClienteSinCorreoException("El cliente que se intentó registrar no tiene Correo Electrónico.");
        }
        
        //Se valida que el teléfono del cliente no sea nulo
        if(nuevoClienteDTO.getCorreoElectronico() == null || nuevoClienteDTO.getCorreoElectronico().isBlank()){
            throw new RegistroClienteSinCorreoException("El cliente que se intentó registrar no tiene Correo Electrónico.");
        }
        
        //Se valida que la longitud del nombre no exceda los límites
        if(nuevoClienteDTO.getNombre().length() > LONGITUD_NOMBRE){
            throw new RegistroClienteFormatoInvalidoException("La longitud del Nombre del cliente que se intentó registrar excede los límites.");
        }
        
        //Se valida que la longitud del apellido paterno no exceda los límites
        if(nuevoClienteDTO.getApellidoPaterno().length() > LONGITUD_APELLIDO_PATERNO){
            throw new RegistroClienteFormatoInvalidoException("La longitud del Apellido Paterno del cliente que se intentó registrar excede los límites.");
        }
        
        //Se valida que la longitud del apellido materno no exceda los límites
        if(nuevoClienteDTO.getApellidoMaterno().length() > LONGITUD_APELLIDO_MATERNO){
            throw new RegistroClienteFormatoInvalidoException("La longitud del Apellido Materno del cliente que se intentó registrar excede los límites.");
        }
        
        //Se valida que la longitud del correo electrónico del cliente no exceda los límites
        if(nuevoClienteDTO.getCorreoElectronico().length() > LONGITUD_CORRE0_ELECTRONICO){
            throw new RegistroClienteFormatoInvalidoException("La longitud del Correo Electrónico del cliente que se intentó registrar excede los límites.");
        }
        
        //Se valida que la longitud del teléfono del cliente no exceda los límites
        if(nuevoClienteDTO.getTelefono().length() > LONGITUD_TELEFONO){
            throw new RegistroClienteFormatoInvalidoException("La longitud del Teléfono del cliente que se intentó registrar excede los límites.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                        SELECT c FROM Cliente c
                        WHERE c.correo_electronico = :correo_electronico
                        AND c.telefono = :telefono
                        """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("correo_electronico", nuevoClienteDTO.getCorreoElectronico());
        query.setParameter("telefono", nuevoClienteDTO.getTelefono());
        
        int cantidadClientesMismoCorreoTelefono = query.getResultList().size();
        
        if(cantidadClientesMismoCorreoTelefono > 0){
            throw new ClienteMismoCorreoTelefonoExistenteException("Ya existe un cliente con el mismo Correo Electrónico y Teléfono.");
        }
        
        entityManager.getTransaction().begin();
        
        Cliente cliente = new Cliente(
                nuevoClienteDTO.getNombre(), 
                nuevoClienteDTO.getApellidoPaterno(), 
                nuevoClienteDTO.getApellidoMaterno(), 
                nuevoClienteDTO.getTelefono(), 
                nuevoClienteDTO.getCorreoElectronico(), 
                nuevoClienteDTO.getFechaRegistro(), 
                Boolean.FALSE); //Por defecto un cliente nuevo no será frecuente hasta que se registre como uno
        
        entityManager.persist(cliente);
        
        entityManager.getTransaction().commit();
        
        return cliente;
        
    }

    /**
     * Método para consultar a todos los clientes
     * @return Lista de clientes
     */
    @Override
    public List<Cliente> consultarClientes() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Cliente> criteriaQuery = criteraBuilder.createQuery(Cliente.class);
        
        Root<Cliente> entidadCliente = criteriaQuery.from(Cliente.class);
        
        criteriaQuery.select(entidadCliente).orderBy(criteraBuilder.asc(entidadCliente.get("nombre")));
        
        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        
        List<Cliente> clientes = query.getResultList();
        
        return clientes;
    }

    /**
     * Método para consultar a clientes por id
     * @param idCliente Representa el id del cliente
     * @return Objeto de tipo cliente
     * @throws ClienteNoExisteException
     * @throws ConsultaClienteSinIdException 
     */
    @Override
    public Cliente consultarCliente(Long idCliente) 
            throws ClienteNoExisteException,
            ConsultaClienteSinIdException{
        
        if(idCliente == null){
            throw new ConsultaClienteSinIdException("El Id utilizado para la consulta del cliente tiene valor nulo.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Cliente> criteriaQuery = criteraBuilder.createQuery(Cliente.class);

        Root<Cliente> entidadIngrediente = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(entidadIngrediente).where(criteraBuilder.equal(entidadIngrediente.get("id_cliente"), idCliente));

        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        try{
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            throw new ClienteNoExisteException("No existe un cliente con Id especificado.");
        }
        
    }

    /**
     * Método para consultar a clientes por nombre
     * @param nombreCliente Representa el nombre del cliente
     * @return Lista de clientes
     * @throws ConsultaClienteSinNombreException 
     */
    @Override
    public List<Cliente> consultarClientesNombre(String nombreCliente) 
            throws ConsultaClienteSinNombreException{
        
        if(nombreCliente == null){
            throw new ConsultaClienteSinNombreException("El nombre utilizado para la consulta de cliente tiene valor nulo.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        
        Root<Cliente> entidadCliente = criteriaQuery.from(Cliente.class);
        
        criteriaQuery
                .select(entidadCliente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadCliente.get("nombre")), "%" + nombreCliente.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadCliente.get("nombre")));
        
        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        
        List<Cliente> clientes = query.getResultList();
        
        return clientes;
    }

    /**
     * Método para consultar a clientes por teléfono
     * @param telefonoCliente Representa el teléfono del cliente
     * @return Lista de clientes
     * @throws ConsultaClienteSinTelefonoException 
     */
    @Override
    public List<Cliente> consultarClientesTelefono(String telefonoCliente) 
            throws ConsultaClienteSinTelefonoException{
        
        if(telefonoCliente == null){
            throw new ConsultaClienteSinTelefonoException("El telefono utilizado para la consulta de cliente tiene valor nulo.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        
        Root<Cliente> entidadCliente = criteriaQuery.from(Cliente.class);
        
        criteriaQuery
                .select(entidadCliente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadCliente.get("telefono")), "%" + telefonoCliente + "%"))
                .orderBy(criteriaBuilder.asc(entidadCliente.get("nombre")));
        
        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        
        List<Cliente> clientes = query.getResultList();
        
        return clientes;
    }

    /**
     * Método para consultar a clientes por correo electrónico
     * @param correoCliente Representa el correo electrónico del cliente
     * @return Lista de clientes
     * @throws ConsultaClienteSinCorreoException 
     */
    @Override
    public List<Cliente> consultarClientesCorreo(String correoCliente) 
            throws ConsultaClienteSinCorreoException{
        
        if(correoCliente == null){
            throw new ConsultaClienteSinCorreoException("El correo utilizado para la consulta de cliente tiene valor nulo");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        
        Root<Cliente> entidadCliente = criteriaQuery.from(Cliente.class);
        
        criteriaQuery
                .select(entidadCliente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadCliente.get("correo_electronico")), "%" + correoCliente.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadCliente.get("nombre")));
        
        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        
        List<Cliente> clientes = query.getResultList();
        
        return clientes;
    }

    /**
     * Método para actualizar a clientes
     * @param clienteActualizadoDTO Representa un objeto de tipo clienteActualizadoDTO
     * @throws RegistroClienteSinNombreException
     * @throws RegistroClienteSinCorreoException
     * @throws RegistroClienteSinTelefonoException
     * @throws ClienteMismoCorreoExistenteException
     * @throws ClienteNoExisteException
     * @throws RegistroClienteSinIdException 
     */
    @Override
    public void actualizarCliente(ClienteActualizadoDTO clienteActualizadoDTO) 
            throws RegistroClienteSinNombreException,
            RegistroClienteSinCorreoException,
            RegistroClienteSinTelefonoException,
            ClienteMismoCorreoExistenteException,
            ClienteNoExisteException,
            RegistroClienteSinIdException{
        
        if(clienteActualizadoDTO.getNombre() == null){
            throw new RegistroClienteSinNombreException("El cliente que se intentó registrar no tiene Nombre.");
        }
        
        if(clienteActualizadoDTO.getCorreoElectronico() == null){
            throw new RegistroClienteSinCorreoException("El cliente que se intentó registrar no tiene Correo Electronico.");
        }
        
        if(clienteActualizadoDTO.getTelefono() == null){
            throw new RegistroClienteSinTelefonoException("El cliente que se intentó registrar no tiene Telefono.");
        }
        
        if(clienteActualizadoDTO.getId() == null){
            throw new RegistroClienteSinIdException("El cliente que se intentó registrar no tiene Nombre.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        //Comprobamos que el cliente
        String jpqlQueryValidarId = """
                        SELECT c FROM Cliente c
                        WHERE c.id_cliente = :id_cliente
                        """;
        
        TypedQuery<Cliente> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Cliente.class);
        
        queryValidarId.setParameter("id_cliente", clienteActualizadoDTO.getId());
        
        try{
            queryValidarId.getSingleResult();
        } catch(NoResultException ex){
            throw new ClienteNoExisteException("No existe un cliente con Id especificado.");
        }

        //Comprobamos si existe otro cliente con el mismo correo electrónico
        
        String jpqlQuery = """
                        SELECT c FROM Cliente c
                        WHERE c.correo_electronico = :correo_electronico 
                        AND c.id_cliente != :id_cliente
                        """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("nombre", clienteActualizadoDTO.getNombre());
        query.setParameter("apellido_paterno", clienteActualizadoDTO.getApellidoPaterno());
        query.setParameter("apellido_materno", clienteActualizadoDTO.getApellidoMaterno());
        query.setParameter("telefono", clienteActualizadoDTO.getTelefono());
        query.setParameter("correo_electronico", clienteActualizadoDTO.getCorreoElectronico());
        query.setParameter("id_cliente", clienteActualizadoDTO.getId());
        
        int cantidadClientesMismoCorreoElectronico = query.getResultList().size();
        
        if(cantidadClientesMismoCorreoElectronico > 0){
            throw new ClienteMismoCorreoExistenteException("Ya existe un cliente con el mismo Correo Electronico.");
        }
        
        entityManager.getTransaction().begin();
 
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Cliente> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Cliente.class);

        Root<Cliente> root = criteriaUpdate.from(Cliente.class);

        criteriaUpdate.set("nombre", clienteActualizadoDTO.getNombre());
        criteriaUpdate.set("apellido_paterno", clienteActualizadoDTO.getApellidoPaterno());
        criteriaUpdate.set("apellido_materno", clienteActualizadoDTO.getApellidoMaterno());
        criteriaUpdate.set("telefono", clienteActualizadoDTO.getTelefono());
        criteriaUpdate.set("correo_electronico", clienteActualizadoDTO.getCorreoElectronico());

        criteriaUpdate.where(criteriaBuilder.equal(root.get("id_cliente"), clienteActualizadoDTO.getId()));

        entityManager.createQuery(criteriaUpdate).executeUpdate();

        entityManager.getTransaction().commit();
    }

    /**
     * Método para eliminar a clientes
     */
    @Override
    public void eliminarCliente(Long idCliente) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaDelete<Cliente> criteriaDelete = criteriaBuilder.createCriteriaDelete(Cliente.class);
        
        Root<Cliente> entidadCliente = criteriaDelete.from(Cliente.class);

        criteriaDelete.where(criteriaBuilder.equal(entidadCliente.get("id_cliente"), idCliente));
        
        entityManager.createQuery(criteriaDelete).executeUpdate();
        
        entityManager.getTransaction().commit();
    }
       
}