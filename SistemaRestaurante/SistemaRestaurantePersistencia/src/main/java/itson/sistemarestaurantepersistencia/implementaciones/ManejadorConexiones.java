
package itson.sistemarestaurantepersistencia.implementaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ManejadorConexiones {
    
    private static boolean conexionTest = false;
    
    public static void setConexionTest(boolean conexionTestActiva){
        conexionTest = conexionTestActiva;
    }
    
    public static EntityManager getEntityManager(){
        
        
        EntityManagerFactory emFactory;
      
        if(conexionTest){
            emFactory = Persistence.createEntityManagerFactory("itson_SistemaRestaurante_jar_1.0PU_Tests");
        } else{
            emFactory = Persistence.createEntityManagerFactory("itson_SistemaRestaurante_jar_1.0PU");
        }
        
        EntityManager entityManager = emFactory.createEntityManager();
        
        return entityManager;
    }
}
