
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantepersistencia.excepciones.UsuarioNoExisteException;


public interface IUsuariosDAO {
    
    public abstract Usuario recuperarUsuarioCorreoElectronico(String correoElectronico) throws UsuarioNoExisteException; 
    
    public abstract Usuario recuperarUsarioId(Long idUsuario) throws UsuarioNoExisteException;
}
