
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.EstadoComanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        Join<Mesa, Comanda> comandaJoin = entidadMesa.join("comandas", JoinType.INNER);

        // Se obtienen solo las comandas que tengan estado Abierta
        criteriaQuery.where(criteriaBuilder.equal(comandaJoin.get("estado"), EstadoComanda.ABIERTA));

        // Se utiliza distinct para evitar que se obtenga una misma mesa más de una vez.
        criteriaQuery.select(entidadMesa)
                .distinct(true)
                .orderBy(criteriaBuilder.asc(entidadMesa.get("numero")));

        TypedQuery<Mesa> query = entityManager.createQuery(criteriaQuery);
        
        List<Mesa> mesasDisponibles = query.getResultList();
        
        return mesasDisponibles;
    }
    
}
