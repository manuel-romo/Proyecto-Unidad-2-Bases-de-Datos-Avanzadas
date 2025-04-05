
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ProductosDAO implements IProductosDAO{
    
    @Override
    public Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO) throws ProductoMismoNombreTipoExistenteException{
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                           SELECT p FROM Producto p
                           WHERE p.nombre = :nombre
                           AND p.tipo = :tipo
                           """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("nombre", nuevoProductoDTO.getNombre());
        query.setParameter("tipo", nuevoProductoDTO.getTipo());
        
        int cantidadProductosMismoNombreTipo = query.getResultList().size();
        
        if(cantidadProductosMismoNombreTipo > 0){
            throw new ProductoMismoNombreTipoExistenteException("Ya existe un producto con el mismo nombre");
        }
        
        entityManager.getTransaction().begin();
        
        Producto producto = new Producto(
                nuevoProductoDTO.getNombre(),
                nuevoProductoDTO.getPrecioFloat(),
                nuevoProductoDTO.getTipo(),
                nuevoProductoDTO.getHabilitado(),
                nuevoProductoDTO.getDireccionImagen()
        );
        
        entityManager.persist(producto);
        
        entityManager.getTransaction().commit();
        
        return producto;
    }
    
    @Override
    public List<Producto> consultarProductos() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
        
        Root<Producto> entidadProducto = criteriaQuery.from(Producto.class);
        
        criteriaQuery.select(entidadProducto).orderBy(criteriaBuilder.asc(entidadProducto.get("nombre")));
        
        TypedQuery<Producto> query = entityManager.createQuery(criteriaQuery);
        
        List<Producto> productos = query.getResultList();
        
        return productos;
    }
    
    @Override
    public Producto buscarProductoPorNombreOCategoria(String nombreOCategoria) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                        SELECT p FROM Producto p
                        WHERE p.nombre LIKE :nombreOCategoria
                        OR p.tipo LIKE :nombreOCategoria
                        """;
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        
        query.setParameter("nombreOCategoria", "%" + nombreOCategoria + "%");
        
        List<Producto> productos = query.getResultList();
        
        return productos.isEmpty() ? null : productos.get(0);
    }
}
