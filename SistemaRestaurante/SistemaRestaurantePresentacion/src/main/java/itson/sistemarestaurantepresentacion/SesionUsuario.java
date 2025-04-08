
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.excepciones.SesionUsuarioInvalidaException;

/**
 * Clase que representa la sesión actual del usuario.
 * @author 
 */
public class SesionUsuario {
    
    private static SesionUsuario instance;
    private Long idUsuario;
    
    private SesionUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public static SesionUsuario crearInstancia(Long idUsuario){
        if(instance == null){
            instance = new SesionUsuario(idUsuario);
        }
        return instance;
    }
    
    public static SesionUsuario getInstance() throws SesionUsuarioInvalidaException{
        if(instance == null){
            throw new SesionUsuarioInvalidaException("La sesión del usuario es inválida.");
        }
        
        return instance;
    }
    
    public Long getIdUsuario(){
        return idUsuario;
    }
    
    public void cerrarSesion(){
        instance = null;
    }
}
