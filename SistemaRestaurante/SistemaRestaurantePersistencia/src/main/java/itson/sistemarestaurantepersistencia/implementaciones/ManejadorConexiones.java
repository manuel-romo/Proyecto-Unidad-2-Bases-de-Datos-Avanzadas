
package itson.sistemarestaurantepersistencia.implementaciones;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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
      
        Properties propiedades = new Properties();
            try (InputStream inputStream = ManejadorConexiones.class.getClassLoader().getResourceAsStream("META-INF/properties.txt")) {
                propiedades.load(inputStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Properties sistema_restaurante_bd_PU_Prop = new Properties();
            Properties sistema_restaurante_bd_tests_PU_Prop = new Properties();

            for (String key : propiedades.stringPropertyNames()) {
                if (key.startsWith("itson_SistemaRestaurante_jar_1.0PU.")) {
                   
                    String keyPropiedad = key.substring("itson_SistemaRestaurante_jar_1.0PU.".length());
                    sistema_restaurante_bd_PU_Prop.put(keyPropiedad, propiedades.getProperty(key));
                    
                } else if (key.startsWith("itson_SistemaRestaurante_jar_1.0PU_Tests.")) {
                    String propKey = key.substring("itson_SistemaRestaurante_jar_1.0PU_Tests.".length());
                    sistema_restaurante_bd_tests_PU_Prop.put(propKey, propiedades.getProperty(key));
                }
            }
            
        if(conexionTest){
            
            
            emFactory = Persistence.createEntityManagerFactory("itson_SistemaRestaurante_jar_1.0PU_Tests", 
                    sistema_restaurante_bd_tests_PU_Prop);
        } else{
            emFactory = Persistence.createEntityManagerFactory("itson_SistemaRestaurante_jar_1.0PU", 
                    sistema_restaurante_bd_PU_Prop);
        }
        
        EntityManager entityManager = emFactory.createEntityManager();
        
        return entityManager;
    }
}
