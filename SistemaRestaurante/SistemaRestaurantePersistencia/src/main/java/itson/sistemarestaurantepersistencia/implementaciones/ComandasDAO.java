
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantepersistencia.IComandasDAO;
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
        
        
        // Se expresa el tipo del resultado que devuelve, en este caso videojuegos
        TypedQuery<Comanda> query = entityManager.createQuery(jpqlQuery, Comanda.class);
        
        List<Comanda> comandas = query.getResultList();
        
        return comandas;
    }
}
