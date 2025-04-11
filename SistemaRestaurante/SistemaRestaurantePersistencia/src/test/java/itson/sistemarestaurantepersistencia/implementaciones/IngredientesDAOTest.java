
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.IngredienteProducto;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinUnidadException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteMismoNombreUnidadExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinCantidadException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroIngredienteSinUnidadException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IngredientesDAOTest {
    
    private static List<Ingrediente> ingredientesRegistrados = new ArrayList<>();
    
    public IngredientesDAOTest() {
    }
    
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
        
        Ingrediente lechuga = new Ingrediente("Lechuga", 2000F,  UnidadIngrediente.GRAMO, "/imagenLechuga.png");
        Ingrediente rabano = new Ingrediente("Rábano", 4000F,  UnidadIngrediente.GRAMO, "/imagenRabano.png");
        Ingrediente leche = new Ingrediente("Leche", 5040F,  UnidadIngrediente.MILILITRO, "/imagenLeche.png");
        Ingrediente huevo = new Ingrediente("Huevo", 100F,  UnidadIngrediente.PIEZA, "/imagenHuevo.png");
        Ingrediente brocoli = new Ingrediente("Brócoli", 5000F,  UnidadIngrediente.GRAMO, "/imagenBrocoli.png");
        Ingrediente coliflor = new Ingrediente("Coliflor", 3500F,  UnidadIngrediente.GRAMO, "/imagenColiflor.png");
        
        // Se guardan en este orden para garantizar el orden alfabético por nombre al realizar las pruebas.
        ingredientesRegistrados.add(brocoli);
        ingredientesRegistrados.add(coliflor);
        ingredientesRegistrados.add(huevo);
        ingredientesRegistrados.add(leche);
        ingredientesRegistrados.add(lechuga);
        ingredientesRegistrados.add(rabano);
        
        Producto ensalada = new Producto("Ensalada", 50F, TipoProducto.PLATILLO, true, "");
        Producto hotCakes = new Producto("HotCakes", 90F, TipoProducto.PLATILLO, true, "");
        Producto pastel = new Producto("Pastel", 120F, TipoProducto.POSTRE, true, "");
             
        IngredienteProducto cantidadLechugaEnsalada = new IngredienteProducto(1500F, ensalada, lechuga);
        IngredienteProducto cantidadRabanoEnsalada = new IngredienteProducto(600F, ensalada, rabano);
        IngredienteProducto cantidadLecheHotCakes = new IngredienteProducto(1500F, hotCakes, leche);
        IngredienteProducto cantidadHuevoHotCakes = new IngredienteProducto(2F, hotCakes, huevo);
        IngredienteProducto cantidadLechePastel = new IngredienteProducto(3000F, pastel, leche);
        IngredienteProducto cantidadHuevoPastel = new IngredienteProducto(3F, pastel, huevo);
        
        // Agregar relaciones IngredienteProducto a ingredientes
        lechuga.addProducto(cantidadLechugaEnsalada);
        
        rabano.addProducto(cantidadRabanoEnsalada);
        
        leche.addProducto(cantidadLecheHotCakes);
        leche.addProducto(cantidadLechePastel);
        
        huevo.addProducto(cantidadHuevoHotCakes);
        huevo.addProducto(cantidadHuevoPastel);
        
        
        // Agregar relaciones IngredienteProducto a productos
        
        ensalada.addIngrediente(cantidadLechugaEnsalada);
        ensalada.addIngrediente(cantidadRabanoEnsalada);
        
        hotCakes.addIngrediente(cantidadLecheHotCakes);
        hotCakes.addIngrediente(cantidadHuevoHotCakes);
        
        pastel.addIngrediente(cantidadLechePastel);
        pastel.addIngrediente(cantidadHuevoPastel);
        
        entityManager.persist(brocoli);
        entityManager.persist(coliflor);
        entityManager.persist(huevo);
        entityManager.persist(leche);
        entityManager.persist(lechuga);
        entityManager.persist(rabano);
         
        entityManager.persist(ensalada);
        entityManager.persist(hotCakes);
        entityManager.persist(pastel);
        
        entityManager.persist(cantidadLechugaEnsalada);
        entityManager.persist(cantidadRabanoEnsalada);
        entityManager.persist(cantidadLecheHotCakes);
        entityManager.persist(cantidadHuevoHotCakes);
        entityManager.persist(cantidadLechePastel);
        entityManager.persist(cantidadHuevoPastel);   

        entityManager.getTransaction().commit();
    }
    
    @AfterEach
    public void tearDown() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        String jpqlQueryBorrarIngredientesProductos = "DELETE FROM IngredienteProducto";
        String jpqlQueryBorrarProductos = "DELETE FROM Producto";
        String jpqlQueryBorrarIngredientes = "DELETE FROM Ingrediente";
        
        entityManager.createQuery(jpqlQueryBorrarIngredientesProductos).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarProductos).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarIngredientes).executeUpdate();
        
        ingredientesRegistrados = new ArrayList<>();
        
        entityManager.getTransaction().commit();
                
    }


    @Test
    public void testRegistrarIngredienteOk() {
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final String NOMBRE_INGREDIENTE_REGISTRAR = "Aguacate";
        final UnidadIngrediente UNIDAD_INGREDIENTE_REGISTRAR = UnidadIngrediente.PIEZA;
        final Float CANTIDAD_INGREDIENTE = 10F;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoIngredienteDTO nuevoIngredienteDTO = 
                new NuevoIngredienteDTO(
                        NOMBRE_INGREDIENTE_REGISTRAR, 
                        UNIDAD_INGREDIENTE_REGISTRAR,
                        CANTIDAD_INGREDIENTE,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        
        Ingrediente ingredienteRegistrado = 
                assertDoesNotThrow(() -> ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO));
        
        assertNotNull(ingredienteRegistrado.getId());
        assertEquals(NOMBRE_INGREDIENTE_REGISTRAR, ingredienteRegistrado.getNombre());
        assertEquals(UNIDAD_INGREDIENTE_REGISTRAR, ingredienteRegistrado.getUnidad());
        assertEquals(CANTIDAD_INGREDIENTE, ingredienteRegistrado.getCantidad());
        assertEquals(DIRECCION_IMAGEN_REGISTRAR, ingredienteRegistrado.getDireccionImagen()); 
        
    }
    
    @Test
    public void testRegistrarIngredientesSinNombreGeneraExcepcion() {
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final String NOMBRE_INGREDIENTE_REGISTRAR = null;
        final UnidadIngrediente UNIDAD_INGREDIENTE_REGISTRAR = UnidadIngrediente.PIEZA;
        final Float CANTIDAD_INGREDIENTE = 10F;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoIngredienteDTO nuevoIngredienteDTO = 
                new NuevoIngredienteDTO(
                        NOMBRE_INGREDIENTE_REGISTRAR, 
                        UNIDAD_INGREDIENTE_REGISTRAR,
                        CANTIDAD_INGREDIENTE,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        
        Exception ex = assertThrows(RegistroIngredienteSinNombreException.class, 
                () -> ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO));

        
    }
    
    @Test
    public void testRegistrarIngredientesSinUnidadGeneraExcepcion() {
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final String NOMBRE_INGREDIENTE_REGISTRAR = "Aguacate";
        final UnidadIngrediente UNIDAD_INGREDIENTE_REGISTRAR = null;
        final Float CANTIDAD_INGREDIENTE = 10F;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoIngredienteDTO nuevoIngredienteDTO = 
                new NuevoIngredienteDTO(
                        NOMBRE_INGREDIENTE_REGISTRAR, 
                        UNIDAD_INGREDIENTE_REGISTRAR,
                        CANTIDAD_INGREDIENTE,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        
        Exception ex = assertThrows(RegistroIngredienteSinUnidadException.class, 
                () -> ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO));

        
    }
    
    @Test
    public void testRegistrarIngredientesSinCantidadGeneraExcepcion() {
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final String NOMBRE_INGREDIENTE_REGISTRAR = "Aguacate";
        final UnidadIngrediente UNIDAD_INGREDIENTE_REGISTRAR = UnidadIngrediente.PIEZA;
        final Float CANTIDAD_INGREDIENTE = null;
        final String DIRECCION_IMAGEN_REGISTRAR = "ejemploDireccionImagen";
        
        NuevoIngredienteDTO nuevoIngredienteDTO = 
                new NuevoIngredienteDTO(
                        NOMBRE_INGREDIENTE_REGISTRAR, 
                        UNIDAD_INGREDIENTE_REGISTRAR,
                        CANTIDAD_INGREDIENTE,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        
        Exception ex = assertThrows(RegistroIngredienteSinCantidadException.class, () -> ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO));
  
    }
    
    @Test
    public void testRegistrarIngredientesSinDireccionImagenGeneraExcepcion() {
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final String NOMBRE_INGREDIENTE_REGISTRAR = "Aguacate";
        final UnidadIngrediente UNIDAD_INGREDIENTE_REGISTRAR = UnidadIngrediente.PIEZA;
        final Float CANTIDAD_INGREDIENTE = 10F;
        final String DIRECCION_IMAGEN_REGISTRAR = null;
        
        NuevoIngredienteDTO nuevoIngredienteDTO = 
                new NuevoIngredienteDTO(
                        NOMBRE_INGREDIENTE_REGISTRAR, 
                        UNIDAD_INGREDIENTE_REGISTRAR,
                        CANTIDAD_INGREDIENTE,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        
        Exception ex = assertThrows(RegistroIngredienteSinDireccionImagenException.class, 
                () -> ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO));
  
    }
    
    @Test
    public void testRegistrarIngredienteConMismoNombreUnidad() {
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final String NOMBRE_INGREDIENTE_REGISTRAR = "Lechuga";
        final UnidadIngrediente UNIDAD_INGREDIENTE_REGISTRAR = UnidadIngrediente.GRAMO;
        final Float CANTIDAD_INGREDIENTE = 3000F;
        final String DIRECCION_IMAGEN_REGISTRAR = "/imagenLechuga2.png";
        
        NuevoIngredienteDTO nuevoIngredienteDTO = 
                new NuevoIngredienteDTO(
                        NOMBRE_INGREDIENTE_REGISTRAR, 
                        UNIDAD_INGREDIENTE_REGISTRAR,
                        CANTIDAD_INGREDIENTE,
                        DIRECCION_IMAGEN_REGISTRAR);
        
        
        Exception ex  = assertThrows(IngredienteMismoNombreUnidadExistenteException.class, 
                () -> ingredientesDAO.registrarIngrediente(nuevoIngredienteDTO));
  
    }
    
    @Test
    public void testConsultarIngredientes(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final int TAMANIO_LISTA_INGREDIENTES_REGISTRADOS = ingredientesRegistrados.size();
        
        List<Ingrediente> ingredientesConsultados = ingredientesDAO.consultarIngredientes();
        
        assertEquals(TAMANIO_LISTA_INGREDIENTES_REGISTRADOS, ingredientesConsultados.size());
        
        for(int i = 0; i < ingredientesConsultados.size(); i++){
            
            Ingrediente ingredienteConsultado = ingredientesConsultados.get(i);
            Ingrediente ingredienteEsperado = ingredientesRegistrados.get(i);
            
            assertEquals(ingredienteEsperado.getId(), ingredienteConsultado.getId());
            assertEquals(ingredienteEsperado.getNombre(), ingredienteConsultado.getNombre());
            assertEquals(ingredienteEsperado.getCantidad(), ingredienteConsultado.getCantidad());
            assertEquals(ingredienteEsperado.getDireccionImagen(), ingredienteConsultado.getDireccionImagen());
            assertEquals(ingredienteEsperado.getProductos(), ingredienteConsultado.getProductos());
            
            
        }
        
          
    }
    
    @Test
    public void testConsultarIngredientesIdOk(){
        IngredientesDAO ingredientesDAO = new IngredientesDAO();

        Ingrediente ingredienteEsperado = ingredientesRegistrados.get(0);
        
        final Long ID_INGREDIENTE_CONSULTAR = ingredienteEsperado.getId();
        
        Ingrediente ingredienteConsultado = assertDoesNotThrow(() -> ingredientesDAO.consultarIngrediente(ID_INGREDIENTE_CONSULTAR));
        
        assertNotNull(ingredienteConsultado);
        
        assertEquals(ingredienteEsperado.getId(), ingredienteConsultado.getId());
        assertEquals(ingredienteEsperado.getNombre(), ingredienteConsultado.getNombre());
        assertEquals(ingredienteEsperado.getCantidad(), ingredienteConsultado.getCantidad());
        assertEquals(ingredienteEsperado.getUnidad(), ingredienteConsultado.getUnidad());
        assertEquals(ingredienteEsperado.getProductos(), ingredienteConsultado.getProductos());
        
    }
    
    @Test
    public void testConsultarIngredientesIdNoExiste(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        List<Long> idsRegistrados = new LinkedList<>();
        
        for(Ingrediente ingredienteEsperado: ingredientesRegistrados){
            idsRegistrados.add(ingredienteEsperado.getId());
        }
        
        Long idMayor = 0L;
        for(Long id: idsRegistrados){
            
            if(id > idMayor){
                idMayor = id;
            }
        }
        
        final Long ID_INGREDIENTE_CONSULTAR = idMayor + 1;
        
        Exception ex = assertThrows(IngredienteNoExisteException.class, 
                () -> ingredientesDAO.consultarIngrediente(ID_INGREDIENTE_CONSULTAR));
        
    }
    
    @Test
    public void testConsultarIngredientesIdNulo(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        final Long ID_INGREDIENTE_CONSULTAR = null;
        
        assertThrows(ConsultaIngredienteSinIdException.class, 
                () -> ingredientesDAO.consultarIngrediente(ID_INGREDIENTE_CONSULTAR));
        
    }
    
    @Test 
    public void testConsultarIngredientesNombreOk(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        
        List<Ingrediente> ingredientesEsperados = Arrays.asList(
                ingredientesRegistrados.get(0),
                ingredientesRegistrados.get(1));
                
        final int TAMANIO_LISTA_INGREDIENTES_REGISTRADOS = ingredientesEsperados.size();
        
        String NOMBRE_INGREDIENTES_CONSULTAR = "Coli";
        
        List<Ingrediente> ingredientesConsultados = 
                assertDoesNotThrow(() -> ingredientesDAO.consultarIngredientesNombre(NOMBRE_INGREDIENTES_CONSULTAR));
        
        assertNotNull(ingredientesConsultados);
        
        assertEquals(TAMANIO_LISTA_INGREDIENTES_REGISTRADOS, ingredientesConsultados.size());
        
        for(int i = 0; i < ingredientesConsultados.size(); i++){
            
            Ingrediente ingredienteConsultado = ingredientesConsultados.get(i);
            Ingrediente ingredienteEsperado = ingredientesEsperados.get(i);
            
            assertEquals(ingredienteEsperado.getId(), ingredienteConsultado.getId());
            assertEquals(ingredienteEsperado.getNombre(), ingredienteConsultado.getNombre());
            assertEquals(ingredienteEsperado.getCantidad(), ingredienteConsultado.getCantidad());
            assertEquals(ingredienteEsperado.getUnidad(), ingredienteConsultado.getUnidad());
            assertEquals(ingredienteEsperado.getProductos(), ingredienteConsultado.getProductos());
              
        }
    }
    
    @Test
    public void consultarIngredientesNombreSinCoincidencias(){
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        List<Ingrediente> ingredientesEsperados = new LinkedList<>();
                
        final int TAMANIO_LISTA_INGREDIENTES_REGISTRADOS = ingredientesEsperados.size();
        
        String NOMBRE_INGREDIENTES_CONSULTAR = "Cerezas";
        
        List<Ingrediente> ingredientesConsultados = 
                assertDoesNotThrow(() -> ingredientesDAO.consultarIngredientesNombre(NOMBRE_INGREDIENTES_CONSULTAR));
        
        assertNotNull(ingredientesConsultados);
        assertEquals(TAMANIO_LISTA_INGREDIENTES_REGISTRADOS, ingredientesConsultados.size());
        
        
    }
    
    @Test
    public void consultarIngredientesNombreNulo(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        List<Ingrediente> ingredientesEsperados = new LinkedList<>();
        
        String NOMBRE_INGREDIENTES_CONSULTAR = null;
        
        assertThrows(ConsultaIngredienteSinNombreException.class, 
                () -> ingredientesDAO.consultarIngredientesNombre(NOMBRE_INGREDIENTES_CONSULTAR));
        
        
    }
    
    @Test 
    public void testConsultarIngredientesUnidadOk(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        List<Ingrediente> ingredientesEsperados = Arrays.asList(
                ingredientesRegistrados.get(0),
                ingredientesRegistrados.get(1),
                ingredientesRegistrados.get(4),
                ingredientesRegistrados.get(5));
                
        final int TAMANIO_LISTA_INGREDIENTES_REGISTRADOS = ingredientesEsperados.size();
        
        String UNIDAD_INGREDIENTES_CONSULTAR = "Gramo";
        
        List<Ingrediente> ingredientesConsultados = 
                assertDoesNotThrow(() -> ingredientesDAO.consultarIngredientesUnidad(UNIDAD_INGREDIENTES_CONSULTAR));
        
        assertNotNull(ingredientesConsultados);
        
        assertEquals(TAMANIO_LISTA_INGREDIENTES_REGISTRADOS, ingredientesConsultados.size());
        
        for(int i = 0; i < ingredientesConsultados.size(); i++){
            
            Ingrediente ingredienteConsultado = ingredientesConsultados.get(i);
            Ingrediente ingredienteEsperado = ingredientesEsperados.get(i);
            
            assertEquals(ingredienteEsperado.getId(), ingredienteConsultado.getId());
            assertEquals(ingredienteEsperado.getNombre(), ingredienteConsultado.getNombre());
            assertEquals(ingredienteEsperado.getCantidad(), ingredienteConsultado.getCantidad());
            assertEquals(ingredienteEsperado.getUnidad(), ingredienteConsultado.getUnidad());
            assertEquals(ingredienteEsperado.getProductos(), ingredienteConsultado.getProductos());
              
        }
    }
    
    @Test
    public void consultarIngredientesUnidadSinCoincidencias(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        List<Ingrediente> ingredientesEsperados = new LinkedList<>();
                
        final int TAMANIO_LISTA_INGREDIENTES_REGISTRADOS = ingredientesEsperados.size();
        
        String UNIDAD_INGREDIENTES_CONSULTAR = "Kilogramo";
        
        List<Ingrediente> ingredientesConsultados = 
                assertDoesNotThrow(() -> ingredientesDAO.consultarIngredientesUnidad(UNIDAD_INGREDIENTES_CONSULTAR));
        
        assertNotNull(ingredientesConsultados);
        assertEquals(TAMANIO_LISTA_INGREDIENTES_REGISTRADOS, ingredientesConsultados.size());
        
    }
    
    @Test
    public void consultarIngredientesUnidadNula(){
        
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        
        String NOMBRE_INGREDIENTES_CONSULTAR = null;
        
        assertThrows(ConsultaIngredienteSinUnidadException.class, 
                () -> ingredientesDAO.consultarIngredientesUnidad(NOMBRE_INGREDIENTES_CONSULTAR));
        
        
    }
    
    
    
    
    
}
