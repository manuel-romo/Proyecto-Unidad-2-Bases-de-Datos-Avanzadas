
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
import itson.sistemarestaurantepersistencia.IMesasDAO;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaIngredienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultarMesaSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.IngredienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.MesaNoExisteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MesasDAOTest {
    
    private static List<Mesa> mesasRegistradas = new ArrayList<>();
    
    public MesasDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        ManejadorConexiones.setConexionTest(true);
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        Mesa mesa1 = new Mesa(2);
        Mesa mesa2 = new Mesa(4);
        Mesa mesa3 = new Mesa(5);
        Mesa mesa4 = new Mesa(6);
        
        mesasRegistradas.add(mesa1);
        mesasRegistradas.add(mesa2);
        mesasRegistradas.add(mesa3);
        mesasRegistradas.add(mesa4);
        
        Comanda comanda1 = new Comanda(EstadoComanda.ABIERTA);
        Comanda comanda2 = new Comanda(EstadoComanda.ABIERTA);
        Comanda comanda3 = new Comanda(EstadoComanda.ENTREGADA);
        Comanda comanda4 = new Comanda(EstadoComanda.ABIERTA);
        Comanda comanda5 = new Comanda(EstadoComanda.CANCELADA);
        
        mesa1.addComanda(comanda1);
        mesa1.addComanda(comanda3);
        
        mesa2.addComanda(comanda4);
        mesa2.addComanda(comanda2);
        
        mesa3.addComanda(comanda5);


        entityManager.persist(comanda1);
        entityManager.persist(comanda2);
        entityManager.persist(comanda3);
        entityManager.persist(comanda4);
        entityManager.persist(comanda5);
        
        entityManager.persist(mesa1);
        entityManager.persist(mesa2);
        entityManager.persist(mesa3);
        entityManager.persist(mesa4);
        
        entityManager.getTransaction().commit();
    }
    
    @AfterEach
    public void tearDown() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        String jpqlQueryBorrarComandas = "DELETE FROM Comanda";
        String jpqlQueryBorrarMesas = "DELETE FROM Mesa";
        
        entityManager.createQuery(jpqlQueryBorrarComandas).executeUpdate();
        entityManager.createQuery(jpqlQueryBorrarMesas).executeUpdate();
        
        mesasRegistradas = new ArrayList<>();
        
        entityManager.getTransaction().commit();
    }


    /**
     * Método que permite probar el método consultarMesasDisponibles().
     */
    @Test
    public void testConsultarMesasDisponiblesOk() {
        
        IMesasDAO mesasDAO = new MesasDAO();
        
        List<Mesa> mesasEsperadas = Arrays.asList(
                mesasRegistradas.get(2),
                mesasRegistradas.get(3));
        
        final int TAMANIO_LISTA_MESAS = mesasEsperadas.size();
        
        List<Mesa> mesasConsultadas = mesasDAO.consultarMesasDisponibles();
        
        assertNotNull(mesasConsultadas);
        
        assertEquals(TAMANIO_LISTA_MESAS, mesasConsultadas.size());
        
        for(int i = 0; i < mesasEsperadas.size(); i++){
            
            Mesa mesaEsperada = mesasEsperadas.get(i);          
            Mesa mesaConsultada = mesasConsultadas.get(i);
            
            assertNotNull(mesaConsultada.getId());
            
            assertEquals(mesaEsperada.getId(), mesaConsultada.getId());
            assertEquals(mesaEsperada.getNumeroMesa(), mesaConsultada.getNumeroMesa());
            assertEquals(mesaEsperada.getComandas(), mesaConsultada.getComandas());
        }
        
        
    }

    @Test
    public void testConsultarMesaIdOk() {
        
        IMesasDAO mesasDAO = new MesasDAO();
        
        Mesa mesaEsperada = mesasRegistradas.get(1);
        
        final Long ID_MESA_CONSULTAR = mesaEsperada.getId();
        
        Mesa mesaConsultada = assertDoesNotThrow(() -> mesasDAO.consultarMesaId(ID_MESA_CONSULTAR));
        
        assertNotNull(mesaConsultada);
        
        assertEquals(mesaEsperada.getId(), mesaConsultada.getId());
        assertEquals(mesaEsperada.getNumeroMesa(), mesaConsultada.getNumeroMesa());
        assertEquals(mesaEsperada.getComandas(), mesaConsultada.getComandas());
    }
    
    @Test
    public void testConsultarMesaIdNoExiste() {
        
        IMesasDAO mesasDAO = new MesasDAO();
        
        List<Long> idsRegistrados = new LinkedList<>();
        
        for(Mesa mesa: mesasRegistradas){
            idsRegistrados.add(mesa.getId());
        }
        
        Long idMayor = 0L;
        for(Long id: idsRegistrados){
            
            if(id > idMayor){
                idMayor = id;
            }
        }
        
        final Long ID_MESA_CONSULTAR = idMayor + 1;
        
        Exception ex = assertThrows(MesaNoExisteException.class, 
                () -> mesasDAO.consultarMesaId(ID_MESA_CONSULTAR));
        
    }
    
    
  
    @Test
    public void testConsultarMesaIdNulo(){
        
        IMesasDAO mesasDAO = new MesasDAO();
         
        final Long ID_MESA_CONSULTAR = null;
        
        Exception ex = assertThrows(ConsultarMesaSinIdException.class, 
                () -> mesasDAO.consultarMesaId(ID_MESA_CONSULTAR));
        
    }
    
}
