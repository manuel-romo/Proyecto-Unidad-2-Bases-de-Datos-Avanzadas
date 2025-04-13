package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.Comanda;
import itson.sistemarestaurantedominio.EstadoComanda;
import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.ProductoComanda;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.dtos.ClienteActualizadoDTO;
import itson.sistemarestaurantedominio.dtos.NuevoClienteDTO;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoCorreoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteMismoTelefonoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ClienteNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinCorreoException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.ConsultaClienteSinTelefonoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteFormatoInvalidoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinCorreoException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroClienteSinTelefonoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
                "Aaron Uriel", 
                "Insunza", 
                "Lira", 
                "6442550989", 
                "ArnoGamer53@gmail.com", 
                fechaRegistroCliente6,
                true
        );
        
        Cliente cliente2 = new Cliente(
                "José Alfredo", 
                "Guzmán", 
                "Moreno", 
                "6441025678", 
                "jose.guzman252525@gmail.com", 
                fechaRegistroCliente1,
                false
        );
        
        Cliente cliente3 = new Cliente(
                "José Eduardo", 
                "Jimenez", 
                "Félix", 
                "6441097652", 
                "SoyEdu25@gmail.com", 
                fechaRegistroCliente5,
                true
        );
        
        Cliente cliente4 = new Cliente(
                "Leonardo", 
                "Flores", 
                "Leyva", 
                "6442087653", 
                "leonardoF24@gmail.com", 
                fechaRegistroCliente2,
                false
        );
        
        Cliente cliente5 = new Cliente(
                "Marco Antonio", 
                "Solis", 
                "Casillas", 
                "6442096532", 
                "Mantonio75@gmail.com", 
                fechaRegistroCliente4,
                false
        );
        
        Cliente cliente6 = new Cliente(
                "Mario Ernesto", 
                "Alcantar", 
                "García", 
                "6441045683", 
                "marioNeto@gmail.com", 
                fechaRegistroCliente3,
                false
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
        
//        EntityManager entityManager = ManejadorConexiones.getEntityManager();
//        
//        entityManager.getTransaction().begin();
//        
//        String jpqlQueryBorrarClientes = "DELETE FROM Cliente";
//
//        entityManager.createQuery(jpqlQueryBorrarClientes).executeUpdate();
//        
//        clientesRegistrados = new ArrayList<>();
//        
//        entityManager.getTransaction().commit();
        
    }

    //TODO pruebas para consultarCliente, actualizarCliente, eliminarCliente, consultarVisitasCliente, obtenerGastoTotalComandasCliente
    
    /**
     * Tests para registrar clientes
     */
    @Test
    public void registrarClienteOk() {
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 3, 12);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Manuel Ernesto";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "López";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Márquez";
        final String TELEFONO_CLIENTE_REGISTRAR = "6441022986";
        final String CORREO_CLIENTE_REGISTRAR = "manyER@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
                
        Cliente clienteRegistrado = 
                assertDoesNotThrow(() -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        assertNotNull(clienteRegistrado.getId());
        assertEquals(NOMBRE_CLIENTE_REGISTRAR, clienteRegistrado.getNombres());
        assertEquals(APELLIDO_PATERNO_CLIENTE_REGISTRAR, clienteRegistrado.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO_CLIENTE_REGISTRAR, clienteRegistrado.getApellidoMaterno());
        assertEquals(TELEFONO_CLIENTE_REGISTRAR, clienteRegistrado.getTelefono());
        assertEquals(CORREO_CLIENTE_REGISTRAR, clienteRegistrado.getCorreoElectronico());
        assertEquals(FECHA_REGISTRO_CLIENTE, clienteRegistrado.getFechaRegistro());
        assertEquals(ES_CLIENTE_FRECUENTE, clienteRegistrado.getEsFrecuente());
    }
    
    @Test
    public void registrarClienteSinNombreGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2024, 10, 15);
        
        final String NOMBRE_CLIENTE_REGISTRAR = null;
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Esquer";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Juárez";
        final String TELEFONO_CLIENTE_REGISTRAR = "6441098976";
        final String CORREO_CLIENTE_REGISTRAR = "fernan21@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteSinNombreException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void registrarClienteSinCorreoGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 9, 11);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Mario";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Fernandez";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "García";
        final String TELEFONO_CLIENTE_REGISTRAR = "6442097534";
        final String CORREO_CLIENTE_REGISTRAR = null;
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteSinCorreoException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test 
    public void registrarClienteSinTelefonoGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 11, 9);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Jesus";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Gonzalez";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Vega";
        final String TELEFONO_CLIENTE_REGISTRAR = null;
        final String CORREO_CLIENTE_REGISTRAR = "JGonza@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteSinTelefonoException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void registrarClienteSinApellidoPaternoGeneraExcepcion(){ 
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 2, 14);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Obed";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = null;
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Lopez";
        final String TELEFONO_CLIENTE_REGISTRAR = "6442550959";
        final String CORREO_CLIENTE_REGISTRAR = "ObeLop25@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteSinNombreException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void registrarClienteSinApellidoMaternoGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2024, 1, 1);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Jose German";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Vega";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = null;
        final String TELEFONO_CLIENTE_REGISTRAR = "6441669870";
        final String CORREO_CLIENTE_REGISTRAR = "JGerman89@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteSinNombreException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void registrarClienteMismoCorreGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 7, 10);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Enrique";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Torres";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Cisneros";
        final String TELEFONO_CLIENTE_REGISTRAR = "6442557689";
        final String CORREO_CLIENTE_REGISTRAR = "manyER@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(ClienteMismoCorreoExistenteException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void registrarClienteMismoTelefonoGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 10, 30);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Luis Felipe";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Rodriguez";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Acosta";
        final String TELEFONO_CLIENTE_REGISTRAR = "6441022986";
        final String CORREO_CLIENTE_REGISTRAR = "LFelipe25@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(ClienteMismoTelefonoExistenteException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void registrarClienteNombreExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2025, 12, 24);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Luis Mario Aaron Manuel Fernando German Enrique Del Perpento Socorro";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Rodriguez";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Moreno";
        final String TELEFONO_CLIENTE_REGISTRAR = "6441067843";
        final String CORREO_CLIENTE_REGISTRAR = "AaronM79@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex); 
    }
    
    @Test
    public void registrarClienteApellidoPaternoExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2024, 10, 14);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Jorge";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Valdez Manriquez Quiñonez Felix Mendivil Cierra Vega Guzman Govea Moreno";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Serrano";
        final String TELEFONO_CLIENTE_REGISTRAR = "6442627835";
        final String CORREO_CLIENTE_REGISTRAR = "JorgeV@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex); 
    }
    
    @Test
    public void registrarClienteApellidoMaternoExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2024, 5, 14);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "German";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Hernandez";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Valdez Manriquez Quiñonez Felix Mendivil Cierra Vega Guzman Govea Moreno";
        final String TELEFONO_CLIENTE_REGISTRAR = "6442094568";
        final String CORREO_CLIENTE_REGISTRAR = "HolaSoyGerman@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex); 
    }
    
    @Test
    public void registrarClienteTelefonoExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Calendar fechaRegistroClienteOk = Calendar.getInstance();
        fechaRegistroClienteOk.set(2024, 1, 28);
        
        final String NOMBRE_CLIENTE_REGISTRAR = "Manuel Alfredo";
        final String APELLIDO_PATERNO_CLIENTE_REGISTRAR = "Fernandez";
        final String APELLIDO_MATERNO_CLIENTE_REGISTRAR = "Cierra";
        final String TELEFONO_CLIENTE_REGISTRAR = "644255097978";
        final String CORREO_CLIENTE_REGISTRAR = "MFreddy@gmail.com";
        final Calendar FECHA_REGISTRO_CLIENTE = fechaRegistroClienteOk;
        final Boolean ES_CLIENTE_FRECUENTE = false;
        
        NuevoClienteDTO nuevoClienteDTO = 
                new NuevoClienteDTO(
                        NOMBRE_CLIENTE_REGISTRAR, 
                        APELLIDO_PATERNO_CLIENTE_REGISTRAR,
                        APELLIDO_MATERNO_CLIENTE_REGISTRAR,
                        TELEFONO_CLIENTE_REGISTRAR,
                        CORREO_CLIENTE_REGISTRAR,
                        FECHA_REGISTRO_CLIENTE,
                        ES_CLIENTE_FRECUENTE
                );
        
        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.registrarCliente(nuevoClienteDTO));
        
        System.out.println("Error: " + ex);  
    }
    
    /**
     * Tests para consultar clientes
     */
    @Test
    public void consultarClientesOk(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final int TAMANIO_LISTA_CLIENTES_REGISTRADOS = clientesRegistrados.size();
        
        List<Cliente> clientesConsultados = clientesDAO.consultarClientes();
        
        assertEquals(TAMANIO_LISTA_CLIENTES_REGISTRADOS, clientesConsultados.size());
        
        System.out.println(clientesRegistrados.get(0));
        
        for(int i = 0; i < clientesConsultados.size(); i++){
            
            Cliente clienteConsultado = clientesConsultados.get(i);
            Cliente clienteEsperado = clientesRegistrados.get(i);
            
            assertEquals(clienteEsperado.getId(), clienteConsultado.getId());
            assertEquals(clienteEsperado.getNombres(), clienteConsultado.getNombres());
            assertEquals(clienteEsperado.getApellidoPaterno(), clienteConsultado.getApellidoPaterno());
            assertEquals(clienteEsperado.getApellidoMaterno(), clienteConsultado.getApellidoMaterno());
            assertEquals(clienteEsperado.getTelefono(), clienteConsultado.getTelefono());
            assertEquals(clienteEsperado.getCorreoElectronico(), clienteConsultado.getCorreoElectronico());
            assertEquals(clienteEsperado.getEsFrecuente(), clienteConsultado.getEsFrecuente());       
        }
        
    }
    
    @Test
    public void consultarClientePorIdOk(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteEsperado = clientesRegistrados.get(0);
        
        final Long ID_CLIENTE_CONSULTAR = clienteEsperado.getId();
        
        Cliente clienteConsultado = assertDoesNotThrow(() -> clientesDAO.consultarCliente(ID_CLIENTE_CONSULTAR));
        
        assertNotNull(clienteConsultado);
        
        assertEquals(clienteEsperado.getId(), clienteConsultado.getId());
        assertEquals(clienteEsperado.getNombres(), clienteConsultado.getNombres());
        assertEquals(clienteEsperado.getApellidoPaterno(), clienteConsultado.getApellidoPaterno());
        assertEquals(clienteEsperado.getApellidoMaterno(), clienteConsultado.getApellidoMaterno());
        assertEquals(clienteEsperado.getTelefono(), clienteConsultado.getTelefono());
        assertEquals(clienteEsperado.getCorreoElectronico(), clienteConsultado.getCorreoElectronico());
        assertEquals(clienteEsperado.getEsFrecuente(), clienteConsultado.getEsFrecuente());
    }
    
    @Test
    public void consultarClienteIdNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final Long ID_CLIENTE_CONSULTAR = null;
        
        Exception ex =assertThrows(ConsultaClienteSinIdException.class, 
                () -> clientesDAO.consultarCliente(ID_CLIENTE_CONSULTAR));
        
        System.out.println("Error: " + ex);
    }
    
    @Test 
    public void consultarClienteIdNoExisteGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        List<Long> idsRegistrados = new LinkedList<>();
        
        for(Cliente clienteEsperado: clientesRegistrados){
            idsRegistrados.add(clienteEsperado.getId());
        }
        
        Long idMayor = 0L;
        for(Long id: idsRegistrados){
            
            if(id > idMayor){
                idMayor = id;
            }
        }
        
        final Long ID_CLIENTE_CONSULTAR = idMayor + 1;
        
        Exception ex = assertThrows(ClienteNoExisteException.class, 
                () -> clientesDAO.consultarCliente(ID_CLIENTE_CONSULTAR));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void consultarClientePorNombreOk(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        List<Cliente> clientesEsperados = Arrays.asList(
                clientesRegistrados.get(1),
                clientesRegistrados.get(2));
                
        final int TAMANIO_LISTA_CLIENTES_REGISTRADOS = clientesEsperados.size();
        
        String NOMBRE_CLIENTES_CONSULTAR = "José";
        
        List<Cliente> clientesConsultados = 
                assertDoesNotThrow(() -> clientesDAO.consultarClientesNombre(NOMBRE_CLIENTES_CONSULTAR));
        
        assertNotNull(clientesConsultados);
        
        assertEquals(TAMANIO_LISTA_CLIENTES_REGISTRADOS, clientesConsultados.size());
        
        for(int i = 0; i < clientesConsultados.size(); i++){
            
            Cliente clienteConsultado = clientesConsultados.get(i);
            Cliente clienteEsperado = clientesEsperados.get(i);
            
            assertEquals(clienteEsperado.getId(), clienteConsultado.getId());
            assertEquals(clienteEsperado.getNombres(), clienteConsultado.getNombres());
            assertEquals(clienteEsperado.getApellidoPaterno(), clienteConsultado.getApellidoPaterno());
            assertEquals(clienteEsperado.getApellidoMaterno(), clienteConsultado.getApellidoMaterno());
            assertEquals(clienteEsperado.getTelefono(), clienteConsultado.getTelefono());
            assertEquals(clienteEsperado.getCorreoElectronico(), clienteConsultado.getCorreoElectronico());
            assertEquals(clienteEsperado.getEsFrecuente(), clienteConsultado.getEsFrecuente());
        }

    }

    @Test
    public void consultarClienteNombreNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        List<Cliente> clientesEsperados = new LinkedList<>();
        
        String NOMBRE_CLIENTES_CONSULTAR = null;
        
        Exception ex = assertThrows(ConsultaClienteSinNombreException.class, 
                () -> clientesDAO.consultarClientesNombre(NOMBRE_CLIENTES_CONSULTAR));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void consultarClientePorTelefonoOk(){
       
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteEsperado = clientesRegistrados.get(0);
        
        final String TELEFONO_CLIENTE_CONSULTAR = clienteEsperado.getTelefono();

        Cliente clienteConsultado = 
                assertDoesNotThrow(() -> clientesDAO.consultarClientesTelefono(TELEFONO_CLIENTE_CONSULTAR));

        assertNotNull(clienteConsultado);

        assertEquals(clienteEsperado.getId(), clienteConsultado.getId());
        assertEquals(clienteEsperado.getNombres(), clienteConsultado.getNombres());
        assertEquals(clienteEsperado.getApellidoPaterno(), clienteConsultado.getApellidoPaterno());
        assertEquals(clienteEsperado.getApellidoMaterno(), clienteConsultado.getApellidoMaterno());
        assertEquals(clienteEsperado.getTelefono(), clienteConsultado.getTelefono());
        assertEquals(clienteEsperado.getCorreoElectronico(), clienteConsultado.getCorreoElectronico());
        assertEquals(clienteEsperado.getEsFrecuente(), clienteConsultado.getEsFrecuente());
    }
    
    @Test
    public void consultarClienteTelefonoNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final String TELEFONO_CLIENTE_CONSULTAR = null;
        
        Exception ex =assertThrows(ConsultaClienteSinTelefonoException.class, 
                () -> clientesDAO.consultarClientesTelefono(TELEFONO_CLIENTE_CONSULTAR));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void consultarClienteTelefonoNoExisteGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final String TELEFONO_NO_EXISTENTE = "9999999999";

        Exception ex = assertThrows(ClienteNoExisteException.class,
                () -> clientesDAO.consultarClientesTelefono(TELEFONO_NO_EXISTENTE));

        System.out.println("Error: " + ex.getMessage());
    }
    
    @Test
    public void consultarClientePorCorreoOk(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteEsperado = clientesRegistrados.get(2);
        
        final String CORREO_CLIENTE_CONSULTAR = clienteEsperado.getTelefono();

        Cliente clienteConsultado = 
                assertDoesNotThrow(() -> clientesDAO.consultarClientesTelefono(CORREO_CLIENTE_CONSULTAR));

        assertNotNull(clienteConsultado);

        assertEquals(clienteEsperado.getId(), clienteConsultado.getId());
        assertEquals(clienteEsperado.getNombres(), clienteConsultado.getNombres());
        assertEquals(clienteEsperado.getApellidoPaterno(), clienteConsultado.getApellidoPaterno());
        assertEquals(clienteEsperado.getApellidoMaterno(), clienteConsultado.getApellidoMaterno());
        assertEquals(clienteEsperado.getTelefono(), clienteConsultado.getTelefono());
        assertEquals(clienteEsperado.getCorreoElectronico(), clienteConsultado.getCorreoElectronico());
        assertEquals(clienteEsperado.getEsFrecuente(), clienteConsultado.getEsFrecuente());
    }
    
    @Test
    public void consultarClienteCorreoNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final String CORREO_CLIENTE_CONSULTAR = null;
        
        Exception ex =assertThrows(ConsultaClienteSinCorreoException.class, 
                () -> clientesDAO.consultarClientesCorreo(CORREO_CLIENTE_CONSULTAR));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void consultarClienteCorreoNoExisteGeneraException(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final String CORREO_NO_EXISTENTE = "Test@gmail.com";

        Exception ex = assertThrows(ClienteNoExisteException.class,
                () -> clientesDAO.consultarClientesCorreo(CORREO_NO_EXISTENTE));

        System.out.println("Error: " + ex.getMessage());
    }

    /**
     * Tests para actualizar cliente
     */
    @Test
    public void actualizarClienteOk(){

        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        assertDoesNotThrow(() -> clientesDAO.actualizarCliente(clienteActualizadoDTO));

        Cliente clienteActualizado = assertDoesNotThrow(() ->
                clientesDAO.consultarCliente(clienteOriginal.getId()));

        assertNotNull(clienteActualizado.getId());
        assertEquals(NUEVO_NOMBRE_CLIENTE, clienteActualizado.getNombres());
        assertEquals(NUEVO_APELLIDO_PATERNO, clienteActualizado.getApellidoPaterno());
        assertEquals(NUEVO_APELLIDO_MATERNO, clienteActualizado.getApellidoMaterno());
        assertEquals(NUEVO_TELEFONO, clienteActualizado.getTelefono());
        assertEquals(NUEVO_CORREO, clienteActualizado.getCorreoElectronico());
    }
    
    @Test
    public void actualizarClienteNombreNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = null;
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteSinNombreException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteApellidoPaternoNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = null;
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteSinNombreException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteApellidoMaternoNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = null;
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteSinNombreException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteTelefonoNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = null;
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteSinTelefonoException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteCorreoNuloGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = null;

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteSinCorreoException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteNombreExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Luis Mario Aaron Manuel Fernando German Enrique Del Perpento Socorro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteApellidoPaternoExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Valdez Manriquez Quiñonez Felix Mendivil Cierra Vega Guzman Govea Moreno";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteApellidoMaternoExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Valdez Manriquez Quiñonez Felix Mendivil Cierra Vega Guzman Govea Moreno";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteTelefonoExcedeLongitudGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "644102965456";
        final String NUEVO_CORREO = "imPeterM@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(RegistroClienteFormatoInvalidoException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }

    @Test
    public void actualizarClienteMismoCorreoGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441029654";
        final String NUEVO_CORREO = "SoyEdu25@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(ClienteMismoCorreoExistenteException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    @Test
    public void actualizarClienteMismoTelefonoGeneraExcepcion(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteOriginal = clientesRegistrados.get(0);

        final String NUEVO_NOMBRE_CLIENTE = "Pedro";
        final String NUEVO_APELLIDO_PATERNO = "Morales";
        final String NUEVO_APELLIDO_MATERNO = "Luna";
        final String NUEVO_TELEFONO = "6441025678";
        final String NUEVO_CORREO = "SoyEdu25@gmail.com";

        ClienteActualizadoDTO clienteActualizadoDTO = new ClienteActualizadoDTO(
                clienteOriginal.getId(),
                NUEVO_NOMBRE_CLIENTE,
                NUEVO_APELLIDO_PATERNO,
                NUEVO_APELLIDO_MATERNO,
                NUEVO_TELEFONO,
                NUEVO_CORREO
        );

        Exception ex = assertThrows(ClienteMismoTelefonoExistenteException.class, 
                () -> clientesDAO.actualizarCliente(clienteActualizadoDTO));
        
        System.out.println("Error: " + ex);
    }
    
    /**
     * Tests para eliminar clientes
     */
    @Test
    public void eliminarClienteOk(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        Cliente clienteEliminar = clientesRegistrados.get(0);

        final Long ID_CLIENTE_ELIMINAR = clienteEliminar.getId();

        assertDoesNotThrow(() -> clientesDAO.eliminarCliente(ID_CLIENTE_ELIMINAR));

        EntityManager em = ManejadorConexiones.getEntityManager();
        Cliente clienteEliminado = em.find(Cliente.class, ID_CLIENTE_ELIMINAR);
        assertNull(clienteEliminado);
    }

    @Test
    public void eliminarClienteSinIdGeneraException(){
        
        ClientesDAO clientesDAO = new ClientesDAO();

        final Long ID_CLIENTE_ELIMINAR = null;

        Exception ex = assertThrows(ConsultaClienteSinIdException.class, 
                () -> clientesDAO.eliminarCliente(ID_CLIENTE_ELIMINAR));
        
        System.out.println("Error: " + ex);
    }
    
    /**
     * Test para consultar visitas del cliente
     */
    @Test
    public void consultarVisitasClienteOk(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        Cliente clienteConsultar = clientesRegistrados.get(0);
        
        final Long ID_CLIENTE_CONSULTAR_VISITAS = clienteConsultar.getId();

        int visitas = assertDoesNotThrow(() -> clientesDAO.consultarVisitasCliente(ID_CLIENTE_CONSULTAR_VISITAS));

        assertTrue(visitas >= 0);
    }
    
    @Test
    public void consultarVisitasClienteSinIdGeneraException(){
        
        ClientesDAO clientesDAO = new ClientesDAO();
        
        final Long ID_CLIENTE_CONSULTAR_VISITAS = null;

        Exception ex = assertThrows(ConsultaClienteSinIdException.class, 
                () -> clientesDAO.eliminarCliente(ID_CLIENTE_CONSULTAR_VISITAS));
        
        System.out.println("Error: " + ex);
    }
    
}