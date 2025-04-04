
package itson.sistemarestaurantepersistencia.implementaciones;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantepersistencia.IUsuariosDAO;
import itson.sistemarestaurantepersistencia.excepciones.UsuarioNoExisteException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class UsuariosDAO implements IUsuariosDAO{
    
    @Override
    public Usuario recuperarUsuarioCorreoElectronico(String correoElectronico) throws UsuarioNoExisteException{
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = """
                           SELECT usr FROM Usuario usr 
                           WHERE usr.correoElectronico = :correoElectronico
                           """;
        
        
        TypedQuery<Usuario> query = entityManager.createQuery(jpqlQuery, Usuario.class);
        
        query.setParameter("correoElectronico", correoElectronico);
        
        Usuario usuario;
        
        try{
            
            usuario = query.getSingleResult();
            
        } catch(NoResultException ex){
            
            throw new UsuarioNoExisteException("No existe un usuario con el correo electrónico: " + correoElectronico);
            
        }

        return usuario;
        
    }
    
    @Override
    public Usuario recuperarUsarioId(Long idUsuario) throws UsuarioNoExisteException{
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
        
        if(usuario == null){
            
            throw new UsuarioNoExisteException("No existe un usuario con el ID: " + idUsuario);
            
        }
        
        return usuario;
    }


}
