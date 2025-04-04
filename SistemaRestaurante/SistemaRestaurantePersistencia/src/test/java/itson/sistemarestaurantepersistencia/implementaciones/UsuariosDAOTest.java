
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantepersistencia.excepciones.UsuarioNoExisteException;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UsuariosDAOTest {
    
    private static Usuario usuarioRegistrado;
    
    public UsuariosDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {

        ManejadorConexiones.setConexionTest(true);
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
        
        Usuario usuario = new Usuario();
        
        usuario.setNombres("Maria Fernanda");
        usuario.setApellidoPaterno("Juárez");
        usuario.setApellidoMaterno("Gutiérrez");
        
        // Correo electrónico utilizada: mariaj@hotmail.com
        usuario.setCorreoElectronico("DloBX/K2lU+Krp0LtHbisaF9zO68qt5nf6fqcKGIf5Q=");
        
        // Contraseña utilizada: contraseniaEjemplo1234
        usuario.setContrasenia("4uHZoY4fZk9mtYp4gRjS+8m+tntXESOkOOWvws0aL5I=");
        
        entityManager.persist(usuario);

        entityManager.getTransaction().commit();

        usuarioRegistrado = usuario;
    }
    
    @AfterAll
    public static void tearDownClass() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        entityManager.getTransaction().begin();
                
        Usuario usuarioBorrar = entityManager.merge(usuarioRegistrado);
        
        entityManager.remove(usuarioBorrar);

        entityManager.getTransaction().commit();
        
        usuarioRegistrado = null;
        
        ManejadorConexiones.setConexionTest(false);
        
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConsultarUsuarioExisteCorreoElectronico(){
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        final String CORREO_ELECTRONICO_USUARIO_CONSULTAR = usuarioRegistrado.getCorreoElectronico();
        final Long ID_USUARIO_CONSULTAR = usuarioRegistrado.getId();
        final String NOMBRES_USUARIO_CONSULTAR = usuarioRegistrado.getNombres();
        final String APELLIDO_PATERNO_USUARIO_CONSULTAR = usuarioRegistrado.getApellidoPaterno();
        final String APELLIDO_MATERNO_USUARIO_CONSULTAR = usuarioRegistrado.getApellidoMaterno();
        final String CONTRASENIA_USUARIO_CONSULTAR = usuarioRegistrado.getContrasenia();
        
        Usuario usuarioRecuperado = assertDoesNotThrow(() -> usuariosDAO.recuperarUsuarioCorreoElectronico(CORREO_ELECTRONICO_USUARIO_CONSULTAR));
        
        assertNotNull(usuarioRecuperado);
        assertEquals(CORREO_ELECTRONICO_USUARIO_CONSULTAR, usuarioRecuperado.getCorreoElectronico());
        assertEquals(ID_USUARIO_CONSULTAR, usuarioRecuperado.getId());
        assertEquals(NOMBRES_USUARIO_CONSULTAR, usuarioRecuperado.getNombres());
        assertEquals(APELLIDO_PATERNO_USUARIO_CONSULTAR, usuarioRecuperado.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO_USUARIO_CONSULTAR, usuarioRecuperado.getApellidoMaterno());
        assertEquals(CONTRASENIA_USUARIO_CONSULTAR, usuarioRecuperado.getContrasenia());
        
        
    }
    
    @Test
    public void testConsultarUsuarioNoExisteCorreoElectronico(){
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        // Correo utilizado: juanram@gmail.com
        final String CORREO_ELECTRONICO_CONSULTAR = "0Xbk91WzaE7rj2pvlmPD8hJy8AwFaloktsNuLOJ7Zwc=";
      
        UsuarioNoExisteException ex = 
                assertThrows(UsuarioNoExisteException.class, () -> usuariosDAO.recuperarUsuarioCorreoElectronico(CORREO_ELECTRONICO_CONSULTAR));
        
        String mensajeExcepcionEsperado = "No existe un usuario con el correo electrónico: " + CORREO_ELECTRONICO_CONSULTAR;
        
        assertEquals(mensajeExcepcionEsperado, ex.getMessage());
    }
    
    @Test 
    public void testConsultarUsuarioId(){
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        final Long ID_USUARIO_CONSULTAR = usuarioRegistrado.getId();
        final String NOMBRES_USUARIO_CONSULTAR = usuarioRegistrado.getNombres();
        final String APELLIDO_PATERNO_USUARIO_CONSULTAR = usuarioRegistrado.getApellidoPaterno();
        final String APELLIDO_MATERNO_USUARIO_CONSULTAR = usuarioRegistrado.getApellidoMaterno();
        final String CORREO_ELECTRONICO_USUARIO_CONSULTAR = usuarioRegistrado.getCorreoElectronico();
        final String CONTRASENIA_USUARIO_CONSULTAR = usuarioRegistrado.getContrasenia();
        
        Usuario usuarioRecuperado = assertDoesNotThrow(() -> usuariosDAO.recuperarUsarioId(ID_USUARIO_CONSULTAR));

        assertNotNull(usuarioRecuperado);
        assertEquals(ID_USUARIO_CONSULTAR, usuarioRecuperado.getId());
        assertEquals(NOMBRES_USUARIO_CONSULTAR, usuarioRecuperado.getNombres());
        assertEquals(APELLIDO_PATERNO_USUARIO_CONSULTAR, usuarioRecuperado.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO_USUARIO_CONSULTAR, usuarioRecuperado.getApellidoMaterno());
        assertEquals(CORREO_ELECTRONICO_USUARIO_CONSULTAR, usuarioRecuperado.getCorreoElectronico());
        assertEquals(CONTRASENIA_USUARIO_CONSULTAR, usuarioRecuperado.getContrasenia());
        
    }
    
    @Test
    public void testConsultarUsuarioNoExisteId(){
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        final Long ID_USUARIO_CONSULTAR = 12L;
       
        UsuarioNoExisteException ex = assertThrows(UsuarioNoExisteException.class, () -> usuariosDAO.recuperarUsarioId(ID_USUARIO_CONSULTAR));
        
        String mensajeExcepcionEsperado = "No existe un usuario con el ID: " + ID_USUARIO_CONSULTAR;
        
        assertEquals(mensajeExcepcionEsperado, ex.getMessage());
    }
    
    
    
}
