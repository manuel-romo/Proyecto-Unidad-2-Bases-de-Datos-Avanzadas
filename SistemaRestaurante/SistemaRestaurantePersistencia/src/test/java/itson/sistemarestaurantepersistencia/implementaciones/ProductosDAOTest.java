package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinPrecioException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinTipoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductosDAOTest {

    private static List<Producto> productosRegistrados = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        ManejadorConexiones.setConexionTest(true);
    }

    @AfterAll
    public static void tearDownClass() {
        ManejadorConexiones.setConexionTest(false);
    }

    @BeforeEach
    public void setUp() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        entityManager.getTransaction().begin();

        Producto pizza = new Producto("Pizza", 150F, TipoProducto.PLATILLO, true, "imagenPizza.png");
        Producto hamburguesa = new Producto("Hamburguesa", 100F, TipoProducto.PLATILLO, true, "imagenHamburguesa.png");
        Producto pastel = new Producto("Pastel", 200F, TipoProducto.POSTRE, true, "imagenPastel.png");

        productosRegistrados.add(pizza);
        productosRegistrados.add(hamburguesa);
        productosRegistrados.add(pastel);

        entityManager.persist(pizza);
        entityManager.persist(hamburguesa);
        entityManager.persist(pastel);

        entityManager.getTransaction().commit();
    }

    @Test
    public void testRegistrarProductoOk() {
        ProductosDAO productosDAO = new ProductosDAO();

        final String NOMBRE_PRODUCTO = "Tacos";
        final float PRECIO_PRODUCTO = 50F;
        final TipoProducto TIPO_PRODUCTO = TipoProducto.PLATILLO;
        final String IMAGEN_PRODUCTO = "imagenTacos.png";

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(
                NOMBRE_PRODUCTO,
                PRECIO_PRODUCTO,
                TIPO_PRODUCTO,
                true,
                IMAGEN_PRODUCTO
        );

        Producto productoRegistrado = assertDoesNotThrow(() -> productosDAO.registrarProducto(nuevoProductoDTO));

        assertNotNull(productoRegistrado.getId());
        assertEquals(NOMBRE_PRODUCTO, productoRegistrado.getNombre());
        assertEquals(PRECIO_PRODUCTO, productoRegistrado.getPrecio());
        assertEquals(TIPO_PRODUCTO, productoRegistrado.getTipo());
        assertEquals(IMAGEN_PRODUCTO, productoRegistrado.getDireccionImagen());
    }

    @Test
    public void testRegistrarProductoSinNombreGeneraExcepcion() {
        ProductosDAO productosDAO = new ProductosDAO();

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(
                null,
                50F,
                TipoProducto.PLATILLO,
                true,
                "imagen.png"
        );

        assertThrows(RegistroProductoSinNombreException.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
    }

    @Test
    public void testRegistrarProductoSinPrecioGeneraExcepcion() {
        ProductosDAO productosDAO = new ProductosDAO();

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(
                "Tacos",
                null,
                TipoProducto.PLATILLO,
                true,
                "imagen.png"
        );

        assertThrows(RegistroProductoSinPrecioException.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
    }

    @Test
    public void testRegistrarProductoSinTipoGeneraExcepcion() {
        ProductosDAO productosDAO = new ProductosDAO();

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(
                "Tacos",
                50F,
                null,
                true,
                "imagen.png"
        );

        assertThrows(RegistroProductoSinTipoException.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
    }

    @Test
    public void testRegistrarProductoSinImagenGeneraExcepcion() {
        ProductosDAO productosDAO = new ProductosDAO();

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(
                "Tacos",
                50F,
                TipoProducto.PLATILLO,
                true,
                null
        );

        assertThrows(RegistroProductoSinDireccionImagenException.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
    }

    @Test
    public void testRegistrarProductoDuplicadoGeneraExcepcion() {
        ProductosDAO productosDAO = new ProductosDAO();

        NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO(
                "Pizza",
                150F,
                TipoProducto.PLATILLO,
                true,
                "imagenPizza.png"
        );

        assertThrows(ProductoMismoNombreTipoExistenteException.class, () -> productosDAO.registrarProducto(nuevoProductoDTO));
    }

    @Test
    public void testConsultarProductos() {
        ProductosDAO productosDAO = new ProductosDAO();

        List<Producto> productos = productosDAO.consultarProductos();

        assertEquals(productosRegistrados.size(), productos.size());
        for (int i = 0; i < productos.size(); i++) {
            assertEquals(productosRegistrados.get(i).getNombre(), productos.get(i).getNombre());
            assertEquals(productosRegistrados.get(i).getPrecio(), productos.get(i).getPrecio());
            assertEquals(productosRegistrados.get(i).getTipo(), productos.get(i).getTipo());
            assertEquals(productosRegistrados.get(i).getDireccionImagen(), productos.get(i).getDireccionImagen());
        }
    }

    @Test
    public void testConsultarProductoPorId() {
        ProductosDAO productosDAO = new ProductosDAO();

        Producto productoEsperado = productosRegistrados.get(0);

        Producto productoConsultado = assertDoesNotThrow(() -> productosDAO.consultarProducto(productoEsperado.getId()));

        assertEquals(productoEsperado.getId(), productoConsultado.getId());
        assertEquals(productoEsperado.getNombre(), productoConsultado.getNombre());
        assertEquals(productoEsperado.getPrecio(), productoConsultado.getPrecio());
        assertEquals(productoEsperado.getTipo(), productoConsultado.getTipo());
        assertEquals(productoEsperado.getDireccionImagen(), productoConsultado.getDireccionImagen());
    }

    @Test
    public void testConsultarProductoPorIdNoExiste() {
        ProductosDAO productosDAO = new ProductosDAO();

        long idInexistente = productosRegistrados.stream()
                .mapToLong(Producto::getId)
                .max()
                .orElse(0L) + 1;

        assertThrows(RegistroProductoNoExisteException.class, () -> productosDAO.consultarProducto(idInexistente));
    }
}