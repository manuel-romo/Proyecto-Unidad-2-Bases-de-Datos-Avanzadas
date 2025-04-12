
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.EstadoComanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import itson.sistemarestaurantepersistencia.excepciones.ConsultarMesaSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.MesaMismoNumeroException;
import itson.sistemarestaurantepersistencia.excepciones.MesaNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroMesaSinNumeroException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;


public class MesasDAO implements IMesasDAO{

    /**
     * 
     * @return 
     */
    @Override
    public List<Mesa> consultarMesasDisponibles() {
       
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Mesa> criteriaQuery = criteriaBuilder.createQuery(Mesa.class);
        
        Root<Mesa> entidadMesa = criteriaQuery.from(Mesa.class);
         
        // Se realiza un inner join con entre Mesas y Comandas.
        Join<Mesa, Comanda> joinComanda = entidadMesa.join("comandas", JoinType.LEFT);

        // Se obtienen solo las comandas que tengan estado Abierta
        criteriaQuery.where(criteriaBuilder.or(
                criteriaBuilder.isNull(joinComanda.get("estado")),
                criteriaBuilder.notEqual(joinComanda.get("estado"), EstadoComanda.ABIERTA)
        ));

        // Se utiliza distinct para evitar que se obtenga una misma mesa más de una vez.
        criteriaQuery.select(entidadMesa)
                .distinct(true)
                .orderBy(criteriaBuilder.asc(entidadMesa.get("numero")));

        TypedQuery<Mesa> query = entityManager.createQuery(criteriaQuery);
        
        List<Mesa> mesasDisponibles = query.getResultList();
        
        return mesasDisponibles;
    }

    @Override
    public Mesa consultarMesaId(Long idMesa) 
            throws MesaNoExisteException, 
            ConsultarMesaSinIdException{
        
        if(idMesa == null){
            throw new ConsultarMesaSinIdException("El id utilizado para la consulta de la mesa tiene valor nulo.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                        SELECT m FROM Mesa m
                        WHERE m.id = :idMesa 
                        """;
        
        
        TypedQuery<Mesa> query = entityManager.createQuery(jpqlQuery, Mesa.class);
        
        query.setParameter("idMesa", idMesa);
        
        try{
            Mesa mesa = query.getSingleResult();
            
            return mesa;
        } catch(NoResultException ex){
            throw new MesaNoExisteException("No existe una mesa con el Id especificado.");
        } 
    }
    
    @Override
    public Mesa registrarMesa(Integer numero) 
            throws MesaMismoNumeroException,
            RegistroMesaSinNumeroException{
        
        if(numero == null){
            throw new RegistroMesaSinNumeroException("El numero de mesa a registrar tiene valor nulo.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                        SELECT m FROM Mesa m
                        WHERE m.numero = :numero 
                        """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("numero", numero);
        
        int cantidadMesasMismoNumero = query.getResultList().size();
        
        if(cantidadMesasMismoNumero > 0){
            throw new MesaMismoNumeroException("Ya existe una mesa con el número recibido.");
        }
        
        entityManager.getTransaction().begin();
        
        Mesa mesa = new Mesa(numero);
        
        entityManager.persist(mesa);
        
        entityManager.getTransaction().commit();
        
        return mesa;
    }

    @Override
    public List<Mesa> consultarMesas() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Mesa> criteriaQuery = criteriaBuilder.createQuery(Mesa.class);
        
        Root<Mesa> entidadMesa = criteriaQuery.from(Mesa.class);
         
        criteriaQuery.select(entidadMesa).orderBy(criteriaBuilder.asc(entidadMesa.get("numero")));

        TypedQuery<Mesa> query = entityManager.createQuery(criteriaQuery);
        
        List<Mesa> mesasDisponibles = query.getResultList();
        
        return mesasDisponibles;
    }
    
}
