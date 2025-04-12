package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.Cliente_;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase TEST que implementa todas las pruebas para el módulo de comandas
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class ClientesDAOTest {
    
    //Lista de clientes registrados para realizar pruebas
    private static List<Cliente> clientesRegistrados = new ArrayList<>();
    
    public ClientesDAOTest() {
    }
    
    /**
     * Método que establece conexión con nuestra base de datos de prueba
     * Antes de comenzar con las mismas
     */
    @BeforeAll
    public static void setUpClass() {
        ManejadorConexiones.setConexionTest(true);
    }
    
    /**
     * Método que define una conexión a la base de pruebas como false en un inicio
     */
    @AfterAll
    public static void tearDownClass() {
        ManejadorConexiones.setConexionTest(false);
    }
    
    /**
     * Método para establecer registros antes de cada prueba
     */
    @BeforeEach
    public void setUp() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        Calendar fechaRegistroCliente1 = Calendar.getInstance();
        fechaRegistroCliente1.set(2025, 4, 11);
        
        Calendar fechaRegistroCliente2 = Calendar.getInstance();
        fechaRegistroCliente2.set(2025, 4, 10);
        
        Calendar fechaRegistroCliente3 = Calendar.getInstance();
        fechaRegistroCliente3.set(2024, 12, 15);
        
        Calendar fechaRegistroCliente4 = Calendar.getInstance();
        fechaRegistroCliente4.set(2025, 2, 20);
        
        Calendar fechaRegistroCliente5 = Calendar.getInstance();
        fechaRegistroCliente5.set(2025, 3, 25);
        
        Calendar fechaRegistroCliente6 = Calendar.getInstance();
        fechaRegistroCliente6.set(2025, 3, 14);
        
        Cliente cliente1 = new Cliente(
                "José Alfredo", 
                "Guzmán", 
                "Moreno", 
                "6441025678", 
                "jose.guzman252525@gmail.com", 
                fechaRegistroCliente1,
                Boolean.FALSE
        );
        
        Cliente cliente2 = new Cliente(
                "Leonardo", 
                "Flores", 
                "Leyva", 
                "6442087653", 
                "leonardoF24@gmail.com", 
                fechaRegistroCliente2,
                Boolean.FALSE
        );
        
        Cliente cliente3 = new Cliente(
                "Mario Ernesto", 
                "Alcantar", 
                "García", 
                "6441045683", 
                "marioNeto@gmail.com", 
                fechaRegistroCliente3,
                Boolean.FALSE
        );
          
        Cliente cliente4 = new Cliente(
                "Marco Antonio", 
                "Solis", 
                "Casillas", 
                "6442096532", 
                "Mantonio75@gmail.com", 
                fechaRegistroCliente4,
                Boolean.FALSE
        );
        
        Cliente cliente5 = new Cliente(
                "José Eduardo", 
                "Jimenez", 
                "Félix", 
                "6441097652", 
                "SoyEdu25@gmail.com", 
                fechaRegistroCliente5,
                Boolean.TRUE
        );
        
        Cliente cliente6 = new Cliente(
                "Aaron Ernesto", 
                "Insunza", 
                "Lira", 
                "6442550989", 
                "ArnoGamer53@gmail.com", 
                fechaRegistroCliente6,
                Boolean.TRUE
        );
        
        clientesRegistrados.add(cliente1);
        clientesRegistrados.add(cliente2);
        clientesRegistrados.add(cliente3);
        clientesRegistrados.add(cliente4);
        clientesRegistrados.add(cliente5);
        clientesRegistrados.add(cliente6);
        
        entityManager.persist(cliente1);
        entityManager.persist(cliente2);
        entityManager.persist(cliente3);
        entityManager.persist(cliente4);
        entityManager.persist(cliente5);
        entityManager.persist(cliente6);
        
        entityManager.getTransaction().commit();
        
    }
    
    /**
     * Método para eliminar todos los registros de la base de datos de prueba
     */
    @AfterEach
    public void tearDown() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        String jpqlQueryBorrarClientes = "DELETE FROM Cliente";

        entityManager.createQuery(jpqlQueryBorrarClientes).executeUpdate();
        
        clientesRegistrados = new ArrayList<>();
        
        entityManager.getTransaction().commit();
        
    }

    //TODO pruebas para cada método
    
    @Test
    public void registrarClienteOk() {
    }
    
    @Test
    public void registrarClienteSinNombreGeneraExcepcion(){
        
    }
    
    @Test
    public void registrarClienteSinCorreoGeneraExcepcion(){
        
    }
    
    @Test 
    public void registrarClienteSinTelefonoGeneraExcepcion(){
        
    }
    
    @Test
    public void registrarClienteSinApellidoPaternoGeneraExcepcion(){
        
    }
}
