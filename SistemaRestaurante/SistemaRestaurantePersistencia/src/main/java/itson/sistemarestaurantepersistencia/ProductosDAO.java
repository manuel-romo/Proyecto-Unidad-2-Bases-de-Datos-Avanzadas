
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantepersistencia.implementaciones.ManejadorConexiones;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import javax.persistence.EntityManager;


public class ProductosDAO {
    
    
//    public static void main(String[] args){
//        
//        EntityManager entityManager = ManejadorConexiones.getEntityManager();
//        
//        entityManager.getTransaction().begin();
//        Producto producto = new Producto("s", 23.3f, TipoProducto.BEBIDA, true);
//        
//        entityManager.persist(producto);
//        
//        entityManager.getTransaction().commit();
//        
//        entityManager.close();
//    }
}
