
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class IngredientesDAO implements IIngredientesDAO{

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO) throws IngredienteMismoNombreUnidadExistenteException{
        
        // Vreificar que el no exista otro producto con el mismo nombre y unidad antes de intentar persistir?
        
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
                nuevoIngredienteDTO.getCantidadFloat(),
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
    
    
    
    
    
}
