
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ProductoNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinPrecioException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinTipoException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;


public class ProductosDAO implements IProductosDAO{
    
    @Override
    public Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO) 
            throws ProductoMismoNombreTipoExistenteException,
            RegistroProductoSinNombreException,
            RegistroProductoSinPrecioException,
            RegistroProductoSinTipoException,
            RegistroProductoSinDireccionImagenException {
        
        // Validaciones
        if (nuevoProductoDTO.getNombre() == null) {
            throw new RegistroProductoSinNombreException("El producto que se intentó registrar no tiene nombre.");
        }
        if (nuevoProductoDTO.getPrecio() == null) {
            throw new RegistroProductoSinPrecioException("El producto que se intentó registrar no tiene precio.");
        }
        if (nuevoProductoDTO.getTipo() == null) {
            throw new RegistroProductoSinTipoException("El producto que se intentó registrar no tiene tipo.");
        }
        if (nuevoProductoDTO.getDireccionImagen() == null) {
            throw new RegistroProductoSinDireccionImagenException("El producto que se intentó registrar no tiene imagen.");
        }
        
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
            throw new ProductoMismoNombreTipoExistenteException("Ya existe un producto con el mismo nombre y tipo");
        }
        
        entityManager.getTransaction().begin();
        
        Producto producto = new Producto(
                nuevoProductoDTO.getNombre(),
                nuevoProductoDTO.getPrecio(),
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
    public List<Producto> consultarProductosNombre(String nombreProductos){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
        
        Root<Producto> entidadIngrediente = criteriaQuery.from(Producto.class);
        
        criteriaQuery
                .select(entidadIngrediente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadIngrediente.get("nombre")), "%" + nombreProductos.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadIngrediente.get("nombre")));
        
        TypedQuery<Producto> query = entityManager.createQuery(criteriaQuery);
        
        List<Producto> productos = query.getResultList();
        
        return productos;
    }
    
    public List<Producto> consultarProductosTipo(String tipoProductos){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
        
        Root<Producto> entidadIngrediente = criteriaQuery.from(Producto.class);
        
        criteriaQuery
                .select(entidadIngrediente)
                .where(criteriaBuilder.like(
                        criteriaBuilder.lower(entidadIngrediente.get("tipo")), "%" + tipoProductos.toLowerCase() + "%"))
                .orderBy(criteriaBuilder.asc(entidadIngrediente.get("tipo")));
        
        TypedQuery<Producto> query = entityManager.createQuery(criteriaQuery);
        
        List<Producto> productos = query.getResultList();
        
        return productos;
    }
    
    @Override
    public Producto consultarProducto(Long idProducto) throws ProductoNoExisteException{
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Producto> criteriaQuery = criteraBuilder.createQuery(Producto.class);

        Root<Producto> entidadProducto = criteriaQuery.from(Producto.class);

        criteriaQuery.select(entidadProducto).where(criteraBuilder.equal(entidadProducto.get("id"), idProducto));

        TypedQuery<Producto> query = entityManager.createQuery(criteriaQuery);
        try{
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            throw new ProductoNoExisteException("No existe un producto con Id especificado.");
        }
        
    }
    
    @Override
    public void actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO)
            throws ProductoMismoNombreTipoExistenteException,
            ProductoNoExisteException,
            RegistroProductoSinNombreException,
            RegistroProductoSinPrecioException,
            RegistroProductoSinTipoException,
            RegistroProductoSinDireccionImagenException {
        
        // Validaciones
        if (productoActualizadoDTO.getNombre() == null) {
            throw new RegistroProductoSinNombreException("El producto que se intentó registrar no tiene nombre.");
        }
        if (productoActualizadoDTO.getPrecio() == null) {
            throw new RegistroProductoSinPrecioException("El producto que se intentó registrar no tiene precio.");
        }
        if (productoActualizadoDTO.getTipo() == null) {
            throw new RegistroProductoSinTipoException("El producto que se intentó registrar no tiene tipo.");
        }
        if (productoActualizadoDTO.getDireccionImagen() == null) {
            throw new RegistroProductoSinDireccionImagenException("El producto que se intentó registrar no tiene imagen.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se comprueba que el ingrediente exista:
        String jpqlQueryValidarId = """
                        SELECT p FROM Producto p
                        WHERE p.id = :id
                        """;
        
        TypedQuery<Producto> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Producto.class);
        
        queryValidarId.setParameter("id", productoActualizadoDTO.getId());
        
        try{
            
            queryValidarId.getSingleResult();
            
        } catch(NoResultException ex){
            throw new ProductoNoExisteException("No existe un producto con Id especificado.");
        }
        
        
        // Se determina si existe otro ingrediente con el mismo nombre y unidad.
        
        String jpqlQuery = """
                        SELECT p FROM Producto p
                        WHERE p.nombre = :nombre 
                        AND p.tipo = :tipo
                        AND p.id != :id
                        """;
        
        Query query = entityManager.createQuery(jpqlQuery);
        
        query.setParameter("nombre", productoActualizadoDTO.getNombre());
        query.setParameter("unidad", productoActualizadoDTO.getTipo());
        query.setParameter("id", productoActualizadoDTO.getId());
        
        int cantidadIngredientesMismoNombreUnidad = query.getResultList().size();
        
        if(cantidadIngredientesMismoNombreUnidad > 0){
            throw new ProductoMismoNombreTipoExistenteException("Ya existe un producto con el mismo Nombre y Tipo.");
        }
        
        entityManager.getTransaction().begin();
 
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Producto> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Producto.class);

        Root<Producto> root = criteriaUpdate.from(Producto.class);

        criteriaUpdate.set("nombre", productoActualizadoDTO.getNombre());
        criteriaUpdate.set("unidad", productoActualizadoDTO.getTipo());
        criteriaUpdate.set("cantidad", productoActualizadoDTO.getPrecio());
        criteriaUpdate.set("direccionImagen", productoActualizadoDTO.getDireccionImagen());

        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), productoActualizadoDTO.getId()));

        entityManager.createQuery(criteriaUpdate).executeUpdate();

        entityManager.getTransaction().commit();
    }
}
