
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.EstadoComanda;
import itson.sistemarestaurantedominio.Ingrediente;
import itson.sistemarestaurantedominio.IngredienteProducto;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.ProductoComanda;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ComandasDAOTest {
    
    private static List<Comanda> comandasRegistradas = new ArrayList();
    private static List<Mesa> mesasRegistradas = new ArrayList();
    
    public ComandasDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
        ManejadorConexiones.setConexionTest(true);
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        Comanda comanda1 = new Comanda(EstadoComanda.ABIERTA);
        Comanda comanda2 = new Comanda(EstadoComanda.ABIERTA);
        Comanda comanda3 = new Comanda(EstadoComanda.ABIERTA);
        Comanda comanda4 = new Comanda(EstadoComanda.ENTREGADA);
        Comanda comanda5 = new Comanda(EstadoComanda.ENTREGADA);
        Comanda comanda6 = new Comanda(EstadoComanda.CANCELADA);
        
        
        //Mesas:
        
        Mesa mesa1 = new Mesa(3);
        Mesa mesa2 = new Mesa(4);
        Mesa mesa3 = new Mesa(6);
        Mesa mesa4 = new Mesa(1);
        Mesa mesa5 = new Mesa(8);
        Mesa mesa6 = new Mesa(12);
        
        comanda1.setMesa(mesa1);
        comanda2.setMesa(mesa2);
        comanda3.setMesa(mesa3);
        comanda4.setMesa(mesa4);
        comanda5.setMesa(mesa5);
        comanda6.setMesa(mesa6);
        
        entityManager.persist(mesa1);
        entityManager.persist(mesa2);
        entityManager.persist(mesa3);
        entityManager.persist(mesa4);
        entityManager.persist(mesa5);
        entityManager.persist(mesa6);
        
        mesasRegistradas.add(mesa1);
        mesasRegistradas.add(mesa2);
        mesasRegistradas.add(mesa3);
        mesasRegistradas.add(mesa4);
        mesasRegistradas.add(mesa5);
        mesasRegistradas.add(mesa6);
        

        // Clientes registrados:
        Calendar fechaRegistroCliente1 = Calendar.getInstance();
        fechaRegistroCliente1.set(2025, 1, 5);
        
        Calendar fechaRegistroCliente2 = Calendar.getInstance();
        fechaRegistroCliente1.set(2025, 2, 20);

        Cliente cliente1 = new Cliente("Ana", "López", "Arellano", "6441234567", fechaRegistroCliente1, false);
        Cliente cliente2 = new Cliente("Esteban", "Contreras", "Arroyo", "6447894561", fechaRegistroCliente2, false);
        
        cliente1.agregarComanda(comanda1);
        cliente2.agregarComanda(comanda3);
        
        
        entityManager.persist(comanda1);
        entityManager.persist(comanda2);
        entityManager.persist(comanda3);
        entityManager.persist(comanda4);
        entityManager.persist(comanda5);
        entityManager.persist(comanda6);
        
        entityManager.persist(cliente1);
        entityManager.persist(cliente2);
        
        Ingrediente carne = new Ingrediente("Carne de res", 3000F, UnidadIngrediente.GRAMO, "direccioImagenEjemplo", true);
        Ingrediente tomate = new Ingrediente("Tomate", 12F, UnidadIngrediente.PIEZA,"direccioImagenEjemplo", true);
        Ingrediente cebolla = new Ingrediente("Cebolla", 6F, UnidadIngrediente.PIEZA, "direccioImagenEjemplo",true);
        Ingrediente chile = new Ingrediente("Chile", 5F, UnidadIngrediente.PIEZA, "direccioImagenEjemplo",true);
        Ingrediente limon = new Ingrediente("Limón", 9F, UnidadIngrediente.PIEZA, "direccioImagenEjemplo", true);
        Ingrediente aguacate = new Ingrediente("Aguacate", 15F, UnidadIngrediente.PIEZA, "direccioImagenEjemplo", true);
        Ingrediente arroz = new Ingrediente("Arroz", 15000F, UnidadIngrediente.GRAMO,"direccioImagenEjemplo", true);
        Ingrediente leche = new Ingrediente("Leche", 4500F, UnidadIngrediente.MILILITRO, "direccioImagenEjemplo",true);
        Ingrediente frijol = new Ingrediente("Frijol", 6000F, UnidadIngrediente.GRAMO, "direccioImagenEjemplo",true);
        
        entityManager.persist(carne);
        entityManager.persist(tomate);
        entityManager.persist(cebolla);
        entityManager.persist(chile);
        entityManager.persist(limon);
        entityManager.persist(aguacate);
        entityManager.persist(arroz);
        entityManager.persist(leche);
        entityManager.persist(frijol);
        
        Producto carneAsada = new Producto("Carne asada", 100F, TipoProducto.PLATILLO, true, "");
        Producto carneMachaca = new Producto("Carne machaca", 90F, TipoProducto.PLATILLO, true, "");
        Producto tacosCecina = new Producto("Tacos de cecina", 120F, TipoProducto.PLATILLO, true, "");
        Producto arrozLeche = new Producto("Arroz con leche", 30F, TipoProducto.BEBIDA, true, "");
        
        entityManager.persist(carneAsada);
        entityManager.persist(carneMachaca);
        entityManager.persist(arrozLeche);
        entityManager.persist(tacosCecina);
                
        IngredienteProducto cantidadCarneCarneAsada = new IngredienteProducto(1000F, carneAsada, carne);
        IngredienteProducto cantidadTomateCarneAsasda = new IngredienteProducto(2F, carneAsada, tomate);
        IngredienteProducto cantidadChileCarneAsasda = new IngredienteProducto(2F, carneAsada, chile);
        IngredienteProducto cantidadCebollaCarneAsasda = new IngredienteProducto(2F, carneAsada, cebolla);

        IngredienteProducto cantidadCarneCarneMachaca = new IngredienteProducto(1500F, carneMachaca, carne);
        IngredienteProducto cantidadTomateCarneMachaca = new IngredienteProducto(2F, carneMachaca, tomate);
        IngredienteProducto cantidadChileCarneMachaca = new IngredienteProducto(1F, carneMachaca, chile);
        IngredienteProducto cantidadFrijolCarneMachaca = new IngredienteProducto(1400F, carneMachaca, frijol);
        
        IngredienteProducto cantidadCarneTacosCecina = new IngredienteProducto(500F, tacosCecina, carne);
        IngredienteProducto cantidadChileTacosCecina = new IngredienteProducto(2F, tacosCecina, chile);
        IngredienteProducto cantidadFrijolTacosCecina = new IngredienteProducto(1000F, tacosCecina, frijol);
        IngredienteProducto cantidadLimonTacosCecina = new IngredienteProducto(2F, tacosCecina, limon);
        
        IngredienteProducto cantidadLecheArrozLeche = new IngredienteProducto(300F, arrozLeche, leche);
        IngredienteProducto cantidadArrozArrozLeche = new IngredienteProducto(100F, arrozLeche, arroz);
        
        entityManager.persist(cantidadCarneCarneAsada);
        entityManager.persist(cantidadTomateCarneAsasda);
        entityManager.persist(cantidadChileCarneAsasda);
        entityManager.persist(cantidadCebollaCarneAsasda);
        
        entityManager.persist(cantidadCarneCarneMachaca);
        entityManager.persist(cantidadTomateCarneMachaca);
        entityManager.persist(cantidadChileCarneMachaca);
        entityManager.persist(cantidadFrijolCarneMachaca);
        
        entityManager.persist(cantidadCarneTacosCecina);
        entityManager.persist(cantidadChileTacosCecina);
        entityManager.persist(cantidadFrijolTacosCecina);
        entityManager.persist(cantidadLimonTacosCecina);
        
        entityManager.persist(cantidadLecheArrozLeche);
        entityManager.persist(cantidadArrozArrozLeche);
        
        ProductoComanda carneAsadaComanda1 = new ProductoComanda(carneAsada.getPrecio(), 2, carneAsada, comanda1);
        ProductoComanda carneMachacaComanda1 = new ProductoComanda(carneMachaca.getPrecio(), 1, carneMachaca, comanda1);
        
        ProductoComanda tacosCecinaComanda2 = new ProductoComanda(tacosCecina.getPrecio(), 3, tacosCecina, comanda2);
        ProductoComanda arrozLecheComanda2 = new ProductoComanda(arrozLeche.getPrecio(), 3, arrozLeche, comanda2);
        
        ProductoComanda carneAsadaComanda3 = new ProductoComanda(carneAsada.getPrecio(), 4, carneAsada, comanda3);
        ProductoComanda carneMachacaComanda3 = new ProductoComanda(carneMachaca.getPrecio(), 2, carneMachaca, comanda3);
        ProductoComanda arrozLecheComanda3 = new ProductoComanda(arrozLeche.getPrecio(), 4, arrozLeche, comanda3);
        
        entityManager.persist(carneAsadaComanda1);
        entityManager.persist(carneMachacaComanda1);
        
        entityManager.persist(tacosCecinaComanda2);
        entityManager.persist(arrozLecheComanda2);
        
        entityManager.persist(carneAsadaComanda3);
        entityManager.persist(carneMachacaComanda3);
        entityManager.persist(arrozLecheComanda3);
        
        // Se refrescan los objetos Comanda con los datos de folio y fecha añadidos
        // cuando fueron persistidos.
        entityManager.flush();
        entityManager.refresh(comanda1);
        entityManager.refresh(comanda2);
        entityManager.refresh(comanda3);
        entityManager.refresh(comanda4);
        entityManager.refresh(comanda5);
        entityManager.refresh(comanda6);
        
        comandasRegistradas.add(comanda1);
        comandasRegistradas.add(comanda2);
        comandasRegistradas.add(comanda3);
        comandasRegistradas.add(comanda4);
        comandasRegistradas.add(comanda5);
        comandasRegistradas.add(comanda6);
        
        entityManager.getTransaction().commit();
    }
    
    @AfterAll
    public static void tearDownClass() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        String jpqlQueryBorrarIngredientesProductos = "DELETE FROM IngredienteProducto";
        String jpqlQueryBorrarIngredientes = "DELETE FROM Ingrediente";
        String jpqlQueryBorrarProductosComandas = "DELETE FROM ProductoComanda";
        String jpqlQueryBorrarProductos = "DELETE FROM Producto";
        String jpqlQueryBorrarComandas = "DELETE FROM Comanda";
        String jpqlQueryBorrarClientes = "DELETE FROM Cliente";
        
        entityManager.createQuery(jpqlQueryBorrarIngredientesProductos).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarIngredientes).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarProductosComandas).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarProductos).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarComandas).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarClientes).executeUpdate();
        
        comandasRegistradas = new ArrayList<>();
        
        entityManager.getTransaction().commit();
        
        ManejadorConexiones.setConexionTest(false);
        

    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConsultarComandas() {
        
        ComandasDAO comandasDAO = new ComandasDAO();
        
        final int TAMANIO_LISTA_COMANDAS_REGISTRADAS = comandasRegistradas.size();
        
        comandasRegistradas.sort(Comparator.comparing(Comanda::getFolio));
        
        List<Comanda> comandasConsultadas = comandasDAO.consultarComandas();
        
        assertEquals(TAMANIO_LISTA_COMANDAS_REGISTRADAS, comandasConsultadas.size());

        
        for(int i = 0; i < comandasConsultadas.size(); i++){
            
            Comanda comandaConsutada = comandasConsultadas.get(i);
            Comanda comandaEsperada = comandasRegistradas.get(i);
            
            assertEquals(comandaEsperada.getId(), comandaConsutada.getId());
            assertEquals(comandaEsperada.getFolio(), comandaConsutada.getFolio());
            assertEquals(comandaEsperada.getCliente(), comandaConsutada.getCliente());
            assertEquals(comandaEsperada.getEstado(), comandaConsutada.getEstado());
            assertEquals(comandaEsperada.getFechaHoraCreacion(), comandaConsutada.getFechaHoraCreacion()); 
            assertEquals(comandaEsperada.getProductosSolicitados(), comandaConsutada.getProductosSolicitados());
            
        }
        
    }
    
    @Test void testRegistrarComanda(){
        
        ComandasDAO comandasDAO = new ComandasDAO();
        
        
        final EstadoComanda ESTADO_COMANDA_ESPERADO = EstadoComanda.ABIERTA;
        final Mesa MESA_COMANDA_ESPERADA = mesasRegistradas.get(3);

        Comanda comandaRegistrada = assertDoesNotThrow(() -> comandasDAO.registrarComanda(MESA_COMANDA_ESPERADA));
        
        assertNotNull(comandaRegistrada);
        
        assertNotNull(comandaRegistrada.getId());
        assertNotNull(comandaRegistrada.getEstado());
        assertNotNull(comandaRegistrada.getFolio());
        assertNotNull(comandaRegistrada.getMesa());
        assertNotNull(comandaRegistrada.getFechaHoraCreacion());
        assertNotNull(comandaRegistrada.getProductosSolicitados());
        
        assertTrue(comandaRegistrada.getProductosSolicitados().isEmpty());
        
        assertNull(comandaRegistrada.getCliente());
        
        assertEquals(ESTADO_COMANDA_ESPERADO, comandaRegistrada.getEstado());
        assertEquals(MESA_COMANDA_ESPERADA, comandaRegistrada.getMesa());
    }
    
}
