
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.EstadoComanda;
import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantepersistencia.IComandasDAO;
import itson.sistemarestaurantepersistencia.excepciones.RegistroComandaSinMesaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class ComandasDAO implements IComandasDAO{
    
    
    @Override
    public List<Comanda> consultarComandas(){
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        String jpqlQuery = """
                           SELECT com FROM Comanda com
                           ORDER BY com.folio ASC
                        """;
        
        TypedQuery<Comanda> query = entityManager.createQuery(jpqlQuery, Comanda.class);
        
        List<Comanda> comandas = query.getResultList();
        
        return comandas;
    }

    @Override
    public Comanda registrarComanda(Mesa mesaNuevaComanda) 
            throws RegistroComandaSinMesaException {
        
        if(mesaNuevaComanda == null){
            throw new RegistroComandaSinMesaException("La mesa utilizada para registrar la comanda tiene valor nulo.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
                
        Comanda comanda = new Comanda(EstadoComanda.ABIERTA);
        
        mesaNuevaComanda.addComanda(comanda);
        
        entityManager.persist(comanda);
        
        entityManager.flush();
        
        entityManager.refresh(comanda);
        
        entityManager.getTransaction().commit();
        
        return comanda;
    }
}
