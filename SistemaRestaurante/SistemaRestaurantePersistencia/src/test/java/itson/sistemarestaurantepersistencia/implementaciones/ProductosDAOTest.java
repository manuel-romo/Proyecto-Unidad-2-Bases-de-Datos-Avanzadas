
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class ProductosDAOTest {
    
    private static List<Producto> productosRegistrados = new ArrayList<>();
    
    public ProductosDAOTest() {
    }

    @BeforeAll
    public static void setUpClass(){
        ManejadorConexiones.setConexionTest(true);
    }
    
    @AfterAll
    public static void tearDownClass(){
        ManejadorConexiones.setConexionTest(false);
    }
    
    @BeforeEach
    public void setUp(){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        Producto ensalada = new Producto("Ensalada", 50F, TipoProducto.PLATILLO, true, "/imagenEnsalada.png");
        Producto hotCakes = new Producto("HotCakes", 90F, TipoProducto.PLATILLO, true, "/imagenHotCakes.png");
        Producto pastel = new Producto("Pastel", 120F, TipoProducto.POSTRE, true, "/imagenPatel.png");

        productosRegistrados.add(ensalada);
        productosRegistrados.add(hotCakes);
        productosRegistrados.add(pastel);

        entityManager.persist(ensalada);
        entityManager.persist(hotCakes);
        entityManager.persist(pastel);

        entityManager.getTransaction().commit();
    }
    
    @AfterEach
    public void tearDown(){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        String jpqlQueryBorrarProductos = "DELETE FROM Producto";
        
        entityManager.createQuery(jpqlQueryBorrarProductos).executeUpdate();
        
        productosRegistrados = new ArrayList<>();
        
        entityManager.getTransaction().commit();
    }
    /**
     * Test of registrarProducto method, of class ProductosDAO.
     */
    
    @Test
    public void testRegistrarProductoOk() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String NOMBRE_PRODUCTO_REGISTRAR = "Sopa";
        final TipoProducto TIPO_PRODUCTO_REGISTRAR = TipoProducto.PLATILLO;
        final String PRECIO_CADENA_PRODUCTO = "15.0";
        final Float PRECIO_FLOAT_PRODUCTO = 15F;
        final Boolean HABILITADO_PRODUCTO = true;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoProductoDTO nuevoProductoDTO = 
                new NuevoProductoDTO(
                        NOMBRE_PRODUCTO_REGISTRAR, 
                        PRECIO_CADENA_PRODUCTO,
                        TIPO_PRODUCTO_REGISTRAR,
                        HABILITADO_PRODUCTO,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        nuevoProductoDTO.setPrecioFloat(PRECIO_FLOAT_PRODUCTO);
        
        Producto productoRegistrado = 
                assertDoesNotThrow(() -> productosDAO.registrarProducto(nuevoProductoDTO));
        
        assertNotNull(productoRegistrado.getId());
        assertEquals(NOMBRE_PRODUCTO_REGISTRAR, productoRegistrado.getNombre());
        assertEquals(PRECIO_FLOAT_PRODUCTO, productoRegistrado.getPrecio());
        assertEquals(TIPO_PRODUCTO_REGISTRAR, productoRegistrado.getTipo());
        assertEquals(HABILITADO_PRODUCTO, productoRegistrado.getHabilitado());
        assertEquals(DIRECCION_IMAGEN_REGISTRAR, productoRegistrado.getDireccionImagen()); 
        
    }
    
    @Test
    public void testRegistrarProductoSinNombreGeneraExcepcion() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String NOMBRE_PRODUCTO_REGISTRAR = null;
        final TipoProducto TIPO_PRODUCTO_REGISTRAR = TipoProducto.PLATILLO;
        final String PRECIO_CADENA_PRODUCTO = "15.0";
        final Float PRECIO_FLOAT_PRODUCTO = 15F;
        final Boolean HABILITADO_PRODUCTO = true;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoProductoDTO nuevoProductoDTO = 
                new NuevoProductoDTO(
                        NOMBRE_PRODUCTO_REGISTRAR, 
                        PRECIO_CADENA_PRODUCTO,
                        TIPO_PRODUCTO_REGISTRAR,
                        HABILITADO_PRODUCTO,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        nuevoProductoDTO.setPrecioFloat(PRECIO_FLOAT_PRODUCTO);
        
        assertThrows(Exception.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
        
    }
    
    @Test
    public void testRegistrarProductoSinTipoGeneraExcepcion() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String NOMBRE_PRODUCTO_REGISTRAR = "Sopa";
        final TipoProducto TIPO_PRODUCTO_REGISTRAR = null;
        final String PRECIO_CADENA_PRODUCTO = "15.0";
        final Float PRECIO_FLOAT_PRODUCTO = 15F;
        final Boolean HABILITADO_PRODUCTO = true;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoProductoDTO nuevoProductoDTO = 
                new NuevoProductoDTO(
                        NOMBRE_PRODUCTO_REGISTRAR, 
                        PRECIO_CADENA_PRODUCTO,
                        TIPO_PRODUCTO_REGISTRAR,
                        HABILITADO_PRODUCTO,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        nuevoProductoDTO.setPrecioFloat(PRECIO_FLOAT_PRODUCTO);
        
        assertThrows(Exception.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
        
    }
    
    @Test
    public void testRegistrarProductoSinPrecioGeneraExcepcion() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String NOMBRE_PRODUCTO_REGISTRAR = "Sopa";
        final TipoProducto TIPO_PRODUCTO_REGISTRAR = TipoProducto.PLATILLO;
        final String PRECIO_CADENA_PRODUCTO = null;
        final Float PRECIO_FLOAT_PRODUCTO = null;
        final Boolean HABILITADO_PRODUCTO = true;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoProductoDTO nuevoProductoDTO = 
                new NuevoProductoDTO(
                        NOMBRE_PRODUCTO_REGISTRAR, 
                        PRECIO_CADENA_PRODUCTO,
                        TIPO_PRODUCTO_REGISTRAR,
                        HABILITADO_PRODUCTO,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        nuevoProductoDTO.setPrecioFloat(PRECIO_FLOAT_PRODUCTO);
        
        assertThrows(Exception.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
        
    }
    
    @Test
    public void testRegistrarProductoSinDireccionImagenGeneraExcepcion() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String NOMBRE_PRODUCTO_REGISTRAR = "Sopa";
        final TipoProducto TIPO_PRODUCTO_REGISTRAR = TipoProducto.PLATILLO;
        final String PRECIO_CADENA_PRODUCTO = "15.0";
        final Float PRECIO_FLOAT_PRODUCTO = 15F;
        final Boolean HABILITADO_PRODUCTO = true;
        final String DIRECCION_IMAGEN_REGISTRAR = null;
        
        NuevoProductoDTO nuevoProductoDTO = 
                new NuevoProductoDTO(
                        NOMBRE_PRODUCTO_REGISTRAR, 
                        PRECIO_CADENA_PRODUCTO,
                        TIPO_PRODUCTO_REGISTRAR,
                        HABILITADO_PRODUCTO,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        nuevoProductoDTO.setPrecioFloat(PRECIO_FLOAT_PRODUCTO);
        
        assertThrows(Exception.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
        
    }
    
    @Test
    public void testRegistrarProductoConMismoNombreTipo() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String NOMBRE_PRODUCTO_REGISTRAR = "Ensalada";
        final TipoProducto TIPO_PRODUCTO_REGISTRAR = TipoProducto.PLATILLO;
        final String PRECIO_CADENA_PRODUCTO = "50.0";
        final Float PRECIO_FLOAT_PRODUCTO = 50F;
        final Boolean HABILITADO_PRODUCTO = true;
        final String DIRECCION_IMAGEN_REGISTRAR = "/imagenEnsalada2.png";
        
        NuevoProductoDTO nuevoProductoDTO = 
                new NuevoProductoDTO(
                        NOMBRE_PRODUCTO_REGISTRAR, 
                        PRECIO_CADENA_PRODUCTO,
                        TIPO_PRODUCTO_REGISTRAR,
                        HABILITADO_PRODUCTO,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        nuevoProductoDTO.setPrecioFloat(PRECIO_FLOAT_PRODUCTO);
        
        assertThrows(ProductoMismoNombreTipoExistenteException.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
        
    }
    
    @Test
    public void testConsultarProductos() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final int TAMANIO_LISTA_PRODUCTOS_REGISTRADOS = productosRegistrados.size();
        
        List<Producto> productosConsultados = productosDAO.consultarProductos();
        
        assertEquals(TAMANIO_LISTA_PRODUCTOS_REGISTRADOS, productosConsultados.size());
        
        for(int i = 0; i < productosConsultados.size(); i++) {
            
            Producto productoConsultado = productosConsultados.get(i);
            Producto productoEsperado = productosRegistrados.get(i);
            
            assertEquals(productoEsperado.getId(), productoConsultado.getId());
            assertEquals(productoEsperado.getNombre(), productoConsultado.getNombre());
            assertEquals(productoEsperado.getPrecio(), productoConsultado.getPrecio());
            assertEquals(productoEsperado.getTipo(), productoConsultado.getTipo());
            assertEquals(productoEsperado.getHabilitado(), productoConsultado.getHabilitado());
            
        }
        
    }
    
    @Test
    public void testBuscarProductoPorNombreOCategoria() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String CRITERIO_BUSQUEDA_NOMBRE = "Ensalada";
        
        Producto productoConsultadoPorNombre = productosDAO.buscarProductoPorNombreOCategoria(CRITERIO_BUSQUEDA_NOMBRE);
        
        assertNotNull(productoConsultadoPorNombre);
        assertEquals(CRITERIO_BUSQUEDA_NOMBRE, productoConsultadoPorNombre.getNombre());
        
        final String CRITERIO_BUSQUEDA_CATEGORIA = "POSTRE";
        
        Producto productoConsultadoPorCategoria = productosDAO.buscarProductoPorNombreOCategoria(CRITERIO_BUSQUEDA_CATEGORIA);
        
        assertNotNull(productoConsultadoPorCategoria);
        assertEquals(TipoProducto.POSTRE, productoConsultadoPorCategoria.getTipo());
        
    }
    
    @Test
    public void testBuscarProductoPorNombreOCategoria_NoResult() {
        
        ProductosDAO productosDAO = new ProductosDAO();
        
        final String CRITERIO_BUSQUEDA = "ProductoInexistente";
        
        Producto productoConsultado = productosDAO.buscarProductoPorNombreOCategoria(CRITERIO_BUSQUEDA);
        
        assertNull(productoConsultado);   
    }    
}
