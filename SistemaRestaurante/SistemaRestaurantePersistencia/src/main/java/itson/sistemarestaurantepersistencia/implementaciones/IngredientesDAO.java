
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.IngredienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;


public class IngredientesDAO implements IIngredientesDAO{

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) 
            throws IngredienteMismoNombreUnidadExistenteException,
            RegistroIngredienteSinUnidadException,
            RegistroIngredienteSinNombreException,
            RegistroIngredienteSinCantidadException,
            RegistroIngredienteSinDireccionImagenException{
        
        // Se valida que el Nombre no sea nulo
        if(nuevoIngredienteDTO.getNombre() == null){
            throw new RegistroIngredienteSinNombreException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        // Se valida que la Unidad no sea nula
        if(nuevoIngredienteDTO.getUnidad() == null){
            throw new RegistroIngredienteSinUnidadException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        // Se valiad que la cantidad no sea nula
        if(nuevoIngredienteDTO.getCantidad() == null){
            throw new RegistroIngredienteSinCantidadException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        // Se valida que la dirección de imagen no sea nula
        if(nuevoIngredienteDTO.getDireccionImagen() == null){
            throw new RegistroIngredienteSinDireccionImagenException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                        SELECT i FROM Ingrediente i
                        WHERE i.nombre = :nombre 
                        AND i.unidad = :unidad
                        """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("nombre", nuevoIngredienteDTO.getNombre());
        query.setParameter("unidad", nuevoIngredienteDTO.getUnidad());
        
        int cantidadIngredientesMismoNombreUnidad = query.getResultList().size();
        
        if(cantidadIngredientesMismoNombreUnidad > 0){
            throw new IngredienteMismoNombreUnidadExistenteException("Ya existe un ingrediente con el mismo Nombre y Unidad.");
        }
        
        entityManager.getTransaction().begin();
        
        Ingrediente ingrediente = new Ingrediente(
                nuevoIngredienteDTO.getNombre(),
                nuevoIngredienteDTO.getCantidad(),
                nuevoIngredienteDTO.getUnidad(),
                nuevoIngredienteDTO.getDireccionImagen());
        
        entityManager.persist(ingrediente);
        
        entityManager.getTransaction().commit();
        
        return ingrediente;
    }

    @Override
    public List<Ingrediente> consultarIngredientes() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Ingrediente> criteriaQuery = criteraBuilder.createQuery(Ingrediente.class);
        
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);
        
        criteriaQuery.select(entidadIngrediente).orderBy(criteraBuilder.asc(entidadIngrediente.get("nombre")));
        
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        List<Ingrediente> ingredientes = query.getResultList();
        
        return ingredientes;
        
    }
    
    
    @Override
    public List<Ingrediente> consultarIngredientesNombre(String nombreIngrediente) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Ingrediente> criteriaQuery = criteriaBuilder.createQuery(Ingrediente.class);
        
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);
        
        criteriaQuery
                .select(entidadIngrediente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadIngrediente.get("nombre")), "%" + nombreIngrediente.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadIngrediente.get("nombre")));
        
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        List<Ingrediente> ingredientes = query.getResultList();
        
        return ingredientes;
        
    }
    
    @Override
    public List<Ingrediente> consultarIngredientesUnidad(String unidadIngrediente) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Ingrediente> criteriaQuery = criteriaBuilder.createQuery(Ingrediente.class);
        
        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);
        
        criteriaQuery
                .select(entidadIngrediente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadIngrediente.get("unidad")), "%" + unidadIngrediente.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadIngrediente.get("unidad")));
        
        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        
        List<Ingrediente> ingredientes = query.getResultList();
        
        return ingredientes;
        
    }

    @Override
    public Ingrediente consultarIngrediente(Long idIngrediente) throws IngredienteNoExisteException{
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Ingrediente> criteriaQuery = criteraBuilder.createQuery(Ingrediente.class);

        Root<Ingrediente> entidadIngrediente = criteriaQuery.from(Ingrediente.class);

        criteriaQuery.select(entidadIngrediente).where(criteraBuilder.equal(entidadIngrediente.get("id"), idIngrediente));

        TypedQuery<Ingrediente> query = entityManager.createQuery(criteriaQuery);
        try{
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            throw new IngredienteNoExisteException("No existe un ingrediente con Id especificado.");
        }
        
    }
    
    
    @Override
    public void actualizarIngrediente(IngredienteActualizadoDTO ingredienteActualizadoDTO)
            throws RegistroIngredienteSinNombreException,
            IngredienteMismoNombreUnidadExistenteException, 
            RegistroIngredienteSinUnidadException,
            RegistroIngredienteSinCantidadException,
            RegistroIngredienteSinDireccionImagenException,
            IngredienteNoExisteException,
            ActualizacionIngredienteSinIdException {
        
        // Se valida que el Nombre no sea nulo
        if(ingredienteActualizadoDTO.getNombre() == null){
            throw new RegistroIngredienteSinNombreException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        // Se valida que la Unidad no sea nula
        if(ingredienteActualizadoDTO.getUnidad() == null){
            throw new RegistroIngredienteSinUnidadException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        // Se valiad que la cantidad no sea nula
        if(ingredienteActualizadoDTO.getCantidad() == null){
            throw new RegistroIngredienteSinCantidadException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
        
        // Se valida que la dirección de imagen no sea nula
        if(ingredienteActualizadoDTO.getDireccionImagen() == null){
            throw new RegistroIngredienteSinDireccionImagenException("El ingrediente que se intentó registrar no tiene Nombre.");
        }
                
                
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se comprueba que el ingrediente exista:
        String jpqlQueryValidarId = """
                        SELECT i FROM Ingrediente i
                        WHERE i.id = :id
                        """;
        
        TypedQuery<Ingrediente> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Ingrediente.class);
        
        queryValidarId.setParameter("id", ingredienteActualizadoDTO.getId());
        
        try{
            
            queryValidarId.getSingleResult();
            
        } catch(NoResultException ex){
            throw new IngredienteNoExisteException("No existe un ingrediente con Id especificado.");
        }
        
        
        // Se determina si existe otro ingrediente con el mismo nombre y unidad.
        
        String jpqlQuery = """
                        SELECT i FROM Ingrediente i
                        WHERE i.nombre = :nombre 
                        AND i.unidad = :unidad
                        AND i.id != :id
                        """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("nombre", ingredienteActualizadoDTO.getNombre());
        query.setParameter("unidad", ingredienteActualizadoDTO.getUnidad());
        query.setParameter("id", ingredienteActualizadoDTO.getId());
        
        int cantidadIngredientesMismoNombreUnidad = query.getResultList().size();
        
        if(cantidadIngredientesMismoNombreUnidad > 0){
            throw new IngredienteMismoNombreUnidadExistenteException("Ya existe un ingrediente con el mismo Nombre y Unidad.");
        }
        
        entityManager.getTransaction().begin();
 
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Ingrediente> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Ingrediente.class);

        Root<Ingrediente> root = criteriaUpdate.from(Ingrediente.class);

        criteriaUpdate.set("nombre", ingredienteActualizadoDTO.getNombre());
        criteriaUpdate.set("unidad", ingredienteActualizadoDTO.getUnidad());
        criteriaUpdate.set("cantidad", ingredienteActualizadoDTO.getCantidad());
        criteriaUpdate.set("direccionImagen", ingredienteActualizadoDTO.getDireccionImagen());

        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), ingredienteActualizadoDTO.getId()));

        entityManager.createQuery(criteriaUpdate).executeUpdate();

        entityManager.getTransaction().commit();
        
    }
    
    
    
    
    
    
    
    
}
