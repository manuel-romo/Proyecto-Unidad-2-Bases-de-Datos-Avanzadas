
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
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
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaExistenciasSinIdIngredienteException;
import itson.sistemarestaurantepersistencia.excepciones.ProductosHabilitadosException;
import itson.sistemarestaurantepersistencia.excepciones.EliminacionIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinHabilitadoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * Clase DAO que representa la implementación de la interfaz {@link IIngredientesDAO},
 * contiene la lógica de acceso a datos para los objetos de tipo Ingrediente.
 * Accede a una base de datos que utilzia SGBD MySQL.
 * 
 * @author Manuel Romo López
 */
public class IngredientesDAO implements IIngredientesDAO{

    /**
     * Implementación del método registrarIngrediente() de la interfaz {@link IIngredientesDAO}, 
     * que permite el registro de un nuevo objeto de tipo Ingrediente.
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
    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws IngredienteMismoNombreUnidadExistenteException,
            RegistroIngredienteSinUnidadException,
            RegistroIngredienteSinNombreException,
            RegistroIngredienteSinHabilitadoException,
            RegistroIngredienteSinCantidadException,
            RegistroIngredienteSinDireccionImagenException{
        
        // Se valida que el nombre no sea nulo
        if(nuevoIngredienteDTO.getNombre() == null){
            throw new RegistroIngredienteSinNombreException("El ingrediente que se intentó registrar no tiene nombre.");
        }
        
        // Se valida que la unidad no sea nula
        if(nuevoIngredienteDTO.getUnidad() == null){
            throw new RegistroIngredienteSinUnidadException("El ingrediente que se intentó registrar no tiene unidad.");
        }
        
        // Se valiad que la cantidad no sea nula
        if(nuevoIngredienteDTO.getCantidad() == null){
            throw new RegistroIngredienteSinCantidadException("El ingrediente que se intentó registrar no tiene cantidad.");
        }
        
        // Se valida que la dirección de imagen no sea nula
        if(nuevoIngredienteDTO.getDireccionImagen() == null){
            throw new RegistroIngredienteSinDireccionImagenException("El ingrediente que se intentó registrar no tiene direccion de imagen.");
        }
        
        // Se valiada que el valor de habilitado no sea nulo
        if(nuevoIngredienteDTO.getHabilitado() == null){
            throw new RegistroIngredienteSinHabilitadoException("El ingrediente que se intentó registrar no tiene valor de habilitado.");
        }
        
        // Se crea el objeto EntityManger
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea la consulta JPQL para obtener los registro de la entidad
        // Ingrediente que tenga el mismo nombre y unidad recibidos para el
        // nuevo registro
        String jpqlQuery = """
                        SELECT i FROM Ingrediente i
                        WHERE i.nombre = :nombre 
                        AND i.unidad = :unidad
                        """;
        
        // Se crea el objeto Query con la consulta
        Query query = entityManager.createQuery(jpqlQuery);
        
        // Se establecen los valores de los parámetros nombre y unidad para
        // la consulta.
        query.setParameter("nombre", nuevoIngredienteDTO.getNombre());
        query.setParameter("unidad", nuevoIngredienteDTO.getUnidad());
        
        // Se obtiene la cantidad de Ingredientes obtenidos.
        int cantidadIngredientesMismoNombreUnidad = query.getResultList().size();
        
        // Si la cantidad de ingredientes es mayor a 0, se lanza una excepción.
        if(cantidadIngredientesMismoNombreUnidad > 0){
            throw new IngredienteMismoNombreUnidadExistenteException("Ya existe un ingrediente con el mismo Nombre y Unidad.");
        }
        
        // Se inicia una transacción para registrar el nuevo Ingrediente.
        entityManager.getTransaction().begin();
        
        // Se crea el nuevo objeto Ingrediente con los datos contenidos en
        // el DTO recibido.
        Ingrediente ingrediente = new Ingrediente(
                nuevoIngredienteDTO.getNombre(),
                nuevoIngredienteDTO.getCantidad(),
                nuevoIngredienteDTO.getUnidad(),
                nuevoIngredienteDTO.getDireccionImagen(),
                nuevoIngredienteDTO.getHabilitado());
        
        // Se persiste el nuevo Ingrediente y finaliza la transacción.
        entityManager.persist(ingrediente);
        
        entityManager.getTransaction().commit();
        
        return ingrediente;
    }

    /**
     * Implementación del método consultarIngredientes(), de la interfaz {@link IngredientesBO},
     * permite obtener la lista de objetos Ingrediente alamcenados en la base de datos.
     * @return Objeto {@literal List<Ingrediente>} que contiene la lista de 
     * ojbetos Ingrediente almacenados.
     */
    @Override
    public List<Ingrediente> consultarIngredientes() {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery,que representa la consulta, devolverá
        // objetos de tipo Ingrediente.
        CriteriaQuery<Ingrediente> criteriaQuery = criteraBuilder.createQuery(Ingrediente.class);
        
        // Se obtiene la entidad de la que se obtendrán los objetos, en este caso
        // Ingrediente.
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);
        
        // Se seleccionan todos los atributos de Ingrediente, los resultados se ordenarán
        // en orden alfabético.
        criteriaQuery.select(entidadIngrediente).orderBy(criteraBuilder.asc(entidadIngrediente.get("nombre")));
        
        // Se crea el objeto TypedQuery<Ingrediente>, es la consulta ejecutable.
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        // Se ejecuta la consulta y se obtiene la lista de Ingredientes.
        List<Ingrediente> ingredientes = query.getResultList();
        
        return ingredientes;
        
    }
    
    /**
     * Implementación del método consultarIngrediente(), de la interfaz {@link IIngredientesDAO},
     * permite obtener un objeto Ingrediente almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * @param idIngrediente Objeto Long que representa el id del Ingrediente buscado.
     * @return Objeto Ingrediente que tiene el id del paráemetro.
     * @throws IngredienteNoExisteException Se lanza si no existe un objeto Ingrediente
     * almacenado con el valor de id del parámetrto.
     * @throws ConsultaIngredienteSinIdException Se lanza si el valor del parámetro
     * idIngrediente es nulo.
     */
    @Override
    public Ingrediente consultarIngrediente(Long idIngrediente) 
            throws IngredienteNoExisteException,
            ConsultaIngredienteSinIdException{
        
        // Se valida el id recibido
        if(idIngrediente == null){
            throw new ConsultaIngredienteSinIdException("El Id utilizado para la consulta de ingrediente tiene valor nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Ingrediente>, representa la consulta que 
        // devolverá el objeto de tipo Ingrediente.
        CriteriaQuery<Ingrediente> criteriaQuery = criteraBuilder.createQuery(Ingrediente.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);

        // Se seleccionan todos los atributos de la entidad Ingrediente,
        // luego se obtienen solo los que tiene el valor del id del parámetro
        // como valor de su atributo id.
        criteriaQuery.select(entidadIngrediente).where(criteraBuilder.equal(entidadIngrediente.get("id"), idIngrediente));

        // Se crea el objeto TypedQuery<Ingrediente>, representa la consulta ejecutable.
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        // Estructura try-catch para manejar la excepción NoResultException.
        try{
            // Se devuelve el objeto Ingrediente coincidente.
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            // En caso que no haya Ingredientes coincidentes, se lanza una excepción.
            throw new IngredienteNoExisteException("No existe un ingrediente con Id especificado.");
        }
        
    }
    
    /**
     * Implementación del método consultarIngredientesNombre(), de la interfaz
     * {@link IIngredientesDAO}, permite obtener la lista de Ingredientes que incluyan
     * en el valor de su campo nombre, el valor de nombre del parámtro.
     * @param nombreIngrediente Objeto String que representa el nombre de los
     * Ingredientes buscados.
     * @return Objeto {@literal List<Ingrediente>}, es la lista de objetos
     * Ingrediente que incluyen el valor del parámetro nombreIngrediente dentro
     * del valor de su atributo nombre.
     * @throws ConsultaIngredienteSinNombreException Se lanza si el nombre 
     * recibido tiene valor nulo.
     */
    @Override
    public List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente) 
            throws ConsultaIngredienteSinNombreException{
        
        // Se valida el nombre recibido
        if(nombreIngrediente == null){
            throw new ConsultaIngredienteSinNombreException("El nombre utilizado para la consulta de ingrediente tiene valor nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea la consulta CriteriaQuery, devolverá objetos Ingrediente
        CriteriaQuery<Ingrediente> criteriaQuery = criteriaBuilder.createQuery(Ingrediente.class);
        
        // Se obtiene la entidad Ingrediente, desde donde
        // se realizará la búsqueda
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);
        
        // Se seleccionan de la entidad Ingrediente, aquellos objetos que
        // incluyan dentro del valor de su atributo nombre, el valor del parámetro
        // nombreIngrediente recibido
        // Los resultados se ordenan en orden alfabético por nombre
        criteriaQuery
                .select(entidadIngrediente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadIngrediente.get("nombre")), "%" + nombreIngrediente.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadIngrediente.get("nombre")));
        
        // Objeto TypedQuery<Ingrediente>, es la consulta ejecutable.
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        // Se obtiene la lista de Ingredientes coincidentes.
        List<Ingrediente> ingredientes = query.getResultList();
        
        return ingredientes;
        
    }
    
    /**
     * Implementación del método consultarIngredientesUnidad(), de la interfaz
     * {@link IIngredientesDAO}, permite obtener la lista de Ingredientes cuyo valor
     * en el atributo unidad incluya valor del parámetro.
     * @param unidadIngrediente Objeto String que representa el valor
     * de los Ingredientes a buscar.
     * @return Objeto {@literal List<Ingrediente>} que representa la lista de 
     * objetos Ingrediente cuyo valor en el atributo unidad incluya el valor del
     * del parámetro.
     * @throws ConsultaIngredienteSinUnidadException Se lanza si el objeto recibido
     * que representa la unidad de los Ingredientes a buscar tiene valor nulo.
     */
    @Override
    public List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente) 
            throws ConsultaIngredienteSinUnidadException {
        
        // Se valida la unida del ingrediente
        if(unidadIngrediente == null){
            throw new ConsultaIngredienteSinUnidadException("La unidad utilizada para la consulta de ingrediente tiene valor nulo");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Ingrediente>, es la
        // consulta ejecutable, devolverá objetos Ingrediente
        CriteriaQuery<Ingrediente> criteriaQuery = criteriaBuilder.createQuery(Ingrediente.class);
        
        // Se obtiene la entidad Ingrediente, desde donde se realizará la búsqueda
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);
        
        // Se seleccionan de la entidad Ingrediente, los objetos que incluyan en
        // el valor de su atributo unidad, el valor del parámetro
        criteriaQuery
                .select(entidadIngrediente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadIngrediente.get("unidad")), "%" + unidadIngrediente.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadIngrediente.get("nombre")));
        
        // Se crea el objeto TypedQuery<Ingrediente>, es la consulta ejecutable
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        // Se obtiene la lista de Ingredientes coincidentes
        List<Ingrediente> ingredientes = query.getResultList();
        
        return ingredientes;
        
    }
    
    /**
     * Implementación del método actualizarIngrediente(), de la interfaz {@link IIngredientesDAO},
     * permite actualizar los valores de los atributos de un objeto Ingrediente almacenado.
     * 
     * @param ingredienteActualizadoDTO Objeto DTO que contiene los nuevos valores
     * de los atributos del objeto Ingrediente a modificar.
     * 
     * @throws ActualizacionIngredienteSinNombreException Se lanza si el valor del
     * Objeto Sring que representa el nuevo nombre de Ingrediente recibido es nulo.
     * 
     * @throws ActualizacionIngredienteSinUnidadException Se lanza si el valor del
     * Objeto UnidadIngrediente que representa la nueva unidad de Ingrediente recibido es nulo.
     * 
     * @throws ActualizacionIngredienteSinCantidadException Se lanza si el valor del
     * Objeto Float que representa la nueva cantidad de Ingrediente recibido es nulo.
     * 
     * @throws ActualizacionIngredienteSinDireccionImagenException Se lanza si el valor del
     * Objeto String que representa la nueva direccion de Ingrediente recibido es nulo.
     * 
     * @throws ActualizacionIngredienteSinIdException Se lanza si el id de Ingrediente
     * a actualizar recibido tiene valor nulo.
     * 
     * @throws IngredienteNoExisteException Se lanza si no existe un Ingrediente
     * con el id del parámetro.
     * 
     * @throws IngredienteMismoNombreUnidadExistenteException Se lanza si existe 
     * otro ingrediente que tiene los mismos valores de nombre y unidad a los
     * recibidos.
     */
    @Override
    public void actualizarIngrediente(IngredienteActualizadoDTO ingredienteActualizadoDTO)
            throws ActualizacionIngredienteSinNombreException, 
            ActualizacionIngredienteSinUnidadException,
            ActualizacionIngredienteSinCantidadException,
            ActualizacionIngredienteSinDireccionImagenException,
            ActualizacionIngredienteSinIdException,
            ActualizacionIngredienteSinHabilitadoException,
            IngredienteNoExisteException,
            IngredienteMismoNombreUnidadExistenteException{
        
        if(ingredienteActualizadoDTO.getId() == null){
            throw new ActualizacionIngredienteSinIdException("El id de ingrediente a actualizar tiene valor nulo.");
        }
        // Se valida que el nombre no sea nulo
        if(ingredienteActualizadoDTO.getNombre() == null){
            throw new ActualizacionIngredienteSinNombreException("El ingrediente que se intentó actualizar no tiene nombre.");
        }
        
        // Se valida que la unidad no sea nula
        if(ingredienteActualizadoDTO.getUnidad() == null){
            throw new ActualizacionIngredienteSinUnidadException("El ingrediente que se intentó actualizar no tiene unidad.");
        }
        
        // Se valida que la cantidad no sea nula
        if(ingredienteActualizadoDTO.getCantidad() == null){
            throw new ActualizacionIngredienteSinCantidadException("El ingrediente que se intentó actualizar no tiene cantidad.");
        }
        
        // Se valida que la dirección de imagen no sea nula
        if(ingredienteActualizadoDTO.getDireccionImagen() == null){
            throw new ActualizacionIngredienteSinDireccionImagenException("El ingrediente que se intentó actualizar no tiene dirección de imagen.");
        }
        
        // Se valida que el estado de habilitado no sea nulo
        if(ingredienteActualizadoDTO.getHabilitado()){
            throw new ActualizacionIngredienteSinHabilitadoException("El ingrediente que se intentó actualizar no tiene estado de habilitado.");
        }
                
                
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se comprueba que el Ingrediente exista
        String jpqlQueryValidarId = """
                        SELECT i FROM Ingrediente i
                        WHERE i.id = :id
                        """;
        
        // Se crea el objeto TypedQuery<Ingrediente>, es la
        // consulta ejecutable, devolverá objetos de tipo Ingrediente.
        TypedQuery<Ingrediente> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Ingrediente.class);
        
        // Se establece el valor de id del parámetro a la consulta
        queryValidarId.setParameter("id", ingredienteActualizadoDTO.getId());
        
        // Estructura try catch para determinar si se lanza la excepción IngredienteNoExisteException
        try{
            // Se obtiene el resultado de la consulta
            queryValidarId.getSingleResult();
            
        } catch(NoResultException ex){
            // Se lanza una excepción en caso de que el objeto Ingrediente no exista
            throw new IngredienteNoExisteException("No existe un ingrediente con Id especificado.");
        }

        // Se determina si existe otro ingrediente con el mismo nombre y unidad 
        // Se añade la condición de que se distinto al id del parámetro para no 
        // considerar al objeto Ingrediente a actualizar.
        String jpqlQuery = """
                        SELECT i FROM Ingrediente i
                        WHERE i.nombre = :nombre 
                        AND i.unidad = :unidad
                        AND i.id != :id
                        """;
        
        // Se crea una consulta Query
        Query query = entityManager.createQuery(jpqlQuery);
        
        // Se establecen los parámetro de nombre, unidad y id para la consulta
        query.setParameter("nombre", ingredienteActualizadoDTO.getNombre());
        query.setParameter("unidad", ingredienteActualizadoDTO.getUnidad());
        query.setParameter("id", ingredienteActualizadoDTO.getId());
        
        // Se obtienen la cantidad de Ingredientes resultantes de la consulta
        int cantidadIngredientesMismoNombreUnidad = query.getResultList().size();
        
        // Se verifica si hay otros Ingredientes con el mismo nombre y unidad
        if(cantidadIngredientesMismoNombreUnidad > 0){
            // Se lanza una excepción en caso de que sí existan
            throw new IngredienteMismoNombreUnidadExistenteException("Ya existe un ingrediente con el mismo Nombre y Unidad.");
        }
        
        // Se inicia una transacción para realiar la actualización del Ingrediente
        entityManager.getTransaction().begin();
 
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Se crear la consulta CriteriaUpdate, actualizará objetos de
        // tipo Ingrediente.
        CriteriaUpdate<Ingrediente> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ingrediente.class);

        // Se obtiene la entidad de donde se actulizarán los objetos, en este caso
        // Ingrediente.
        Root<Ingrediente> root = criteriaUpdate.from(Ingrediente.class);

        // Se etablecen los nuevos valores de nombre, unidad, cantidad y direccionImange
        // para su actualizacion, en la consulta criteriaUpdate
        criteriaUpdate.set("nombre", ingredienteActualizadoDTO.getNombre());
        criteriaUpdate.set("unidad", ingredienteActualizadoDTO.getUnidad());
        criteriaUpdate.set("cantidad", ingredienteActualizadoDTO.getCantidad());
        criteriaUpdate.set("direccionImagen", ingredienteActualizadoDTO.getDireccionImagen());

        // Se define que se actualizarán solo el Ingrediente que tenga el mismo valor de
        // id que el recibido
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), ingredienteActualizadoDTO.getId()));

        // Se crea la consulta ejecutable y es ejecutada
        entityManager.createQuery(criteriaUpdate).executeUpdate();

        // La transacción finaliza
        entityManager.getTransaction().commit();
        
    }

    /**
     * Implementación del método consultarDisponibilidadIngrediente() de la interfaz {@link IIngredientesDAO}, 
     * que permite obtener la cantidad en inventario del Ingrediente con el id del parámetro.
     * @param idIngrediente Objeto Long que representa el id del ingrediente a 
     * obtener su disponibilidad.
     * @return Objeto Float, la cantidad del ingrediente disponible en inventario.
     * @throws ConsultaExistenciasSinIdIngredienteException Se lanza
     * si el id del Ingrediente recibido es nulo.
     * @throws IngredienteNoExisteException Se lanza si no existe un Ingrediente
     * con el id del parámetro.
     */
    @Override
    public Float consultarDisponibilidadIngrediente(Long idIngrediente) 
            throws ConsultaExistenciasSinIdIngredienteException,
            IngredienteNoExisteException{
        
        // Se valida el id del ingrediente
        if(idIngrediente == null){
            throw new ConsultaExistenciasSinIdIngredienteException("El Id del ingrediente a verificar existencias tiene valor nulo.");
        }
        
        // Se obtiene el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea la consulta JPQL para obtener la cantidad del Ingrediente
        String jpqlQuery = """
                           SELECT i.cantidad FROM Ingrediente i 
                           WHERE i.id = :idIngrediente
                           """;
        
        // Se crea el objeto TypedQuery<Float>, es la consulta ejecutable, 
        // devolverá objetos de tipo Float
        TypedQuery<Float> query = entityManager.createQuery(jpqlQuery, Float.class);
        
        // Se establece el valor para el parámetro idIngrediente de la consulta
        // como el recibido
        query.setParameter("idIngrediente", idIngrediente);
        
        // Estructura try-catch para manejar la excepción NoResultException.
        try{
            // Se devuelve la cantidad.
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            // En caso que no haya Ingredientes coincidentes, se lanza una excepción.
            throw new IngredienteNoExisteException("No existe un ingrediente con Id especificado.");
        }
        
    }

    
    /**
     * Implementación del método deshabilitarIngrediente(), de la interfaz 
     * {@link IIngredientesDAO}, permite deshabilitar el Ingrediente con el id
     * del parámetro
     * @param idIngrediente Objeto Long que representa el id del Ingrediente
     * a deshabilitar
     * @throws EliminacionIngredienteSinIdException Se lanza si el id recibido
     * tiene valor nulo,
     * @throws ProductosHabilitadosException Se lanza 
     * si el Ingrediente a deshabilitar es utilizado por objetos Producto registrados.
     */
    @Override
    public void deshabilitarIngrediente(Long idIngrediente)
            throws EliminacionIngredienteSinIdException, 
            ProductosHabilitadosException{
        
        if(idIngrediente == null){
            throw new EliminacionIngredienteSinIdException("El id de ingrediente a eliminar tiene valor nulo.");
        }
        
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQueryConsultaProductosAsociados = """
                                                     SELECT COUNT(ip) FROM IngredienteProducto ip 
                                                     WHERE ip.producto.deshabilitado = true 
                                                     AND ip.ingrediente.id = :idIngrediente"
                                                     """;
        
        TypedQuery<Long> query = entityManager.createQuery(jpqlQueryConsultaProductosAsociados, Long.class);
        
        query.setParameter("idIngrediente", idIngrediente);
        
        Long cantidadIngredientesProductosProductoHabilitado = query.getSingleResult();
        
        if(cantidadIngredientesProductosProductoHabilitado > 0){
            throw new ProductosHabilitadosException("El producto a eliminar es utilizado por productos habilitados.");
        }

        entityManager.getTransaction().begin();
        
        String jpqlQueryDeshabilitarIngrediente = "UPDATE Ingrediente i SET i.habilitado = false WHERE i.id = :idIngrediente";
        
        entityManager.createQuery(jpqlQueryDeshabilitarIngrediente).executeUpdate();
        
        entityManager.getTransaction().commit();
    }
    
    /**
     * Implementación dle método eliminarTodosIngredientes(), de la interfaz{@link IIngredientesDAO}
     * utilizado para eliminar todos los registros de Ingrediente accedidospor el sistema.
     * Utilizado sólo para realizar pruebas.
     */
    @Override
    public void eliminarTodosIngredientes() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        String jpqlQueryBorrarIngredientes = "DELETE FROM Ingrediente";
        
        entityManager.createQuery(jpqlQueryBorrarIngredientes).executeUpdate();
        
        entityManager.getTransaction().commit();
    }
    
    
    
}
