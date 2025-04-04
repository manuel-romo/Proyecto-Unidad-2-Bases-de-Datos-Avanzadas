
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantedominio.dtos.CorreoContraseniaInicioSesionDTO;
import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.ContraseniaIncorrectaException;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoContraseniaInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.FormatoCorreoElectronicoInvalidoException;
import itson.sistemarestaurantenegocio.utils.SeguridadUtils;
import itson.sistemarestaurantepersistencia.IUsuariosDAO;
import itson.sistemarestaurantepersistencia.excepciones.UsuarioNoExisteException;


public class UsuariosBO implements IUsuariosBO{

    private IUsuariosDAO usuariosDAO;
    private String REGEX_EMAIL_VALIDO = "[A-Za-z0-9._]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$";

    public UsuariosBO(IUsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }
    
    @Override
    public Usuario iniciarSesion(CorreoContraseniaInicioSesionDTO correoContraseniaInicioSesionDTO) 
            throws FormatoCorreoElectronicoInvalidoException, FormatoContraseniaInvalidoException, 
            UsuarioInexistenteException, ContraseniaIncorrectaException{
        
        String correoElectronico = correoContraseniaInicioSesionDTO.getCorreoElectronico();
        char[] contrasenia = correoContraseniaInicioSesionDTO.getContrasenia();
        
        
        if(correoElectronico.trim().isBlank()){
            throw new FormatoContraseniaInvalidoException("Debe ingresar un correo electrónico");
        }
        
        if(!correoElectronico.matches(REGEX_EMAIL_VALIDO)){
            throw new FormatoCorreoElectronicoInvalidoException("El correo electrónico debe tener el formato usuario@ejemplo.dominio");
        }
        
        if(contrasenia == null || contrasenia.length == 0){
            throw new FormatoContraseniaInvalidoException("Debe ingresar una contraseña");
        }
        
        String hashCorreoElectronico = SeguridadUtils.generarHashSha256(correoElectronico);
        
        
        String cadenaContrasenia = new String(contrasenia);
        
        String hashContrasenia = SeguridadUtils.generarHashSha256(cadenaContrasenia);
        
        
        try{
            Usuario usuarioConsultado = usuariosDAO.recuperarUsuarioCorreoElectronico(hashCorreoElectronico);
            
            if(!hashContrasenia.equals(usuarioConsultado.getContrasenia())){
            throw new ContraseniaIncorrectaException("La contraseña ingresada es incorrecta.");
        }
        
        return usuarioConsultado;
        
        } catch(UsuarioNoExisteException ex){
            throw new UsuarioInexistenteException("No existe un usuario con el correo electrónico ingresado.");
        }
        
       
    }
    
    
    @Override
    public Usuario consultarUsuarioId(Long idUsuario) throws UsuarioInexistenteException{
        
        try{
            Usuario usuarioConsultado = usuariosDAO.recuperarUsarioId(idUsuario);
            
            return usuarioConsultado;
            
        } catch(UsuarioNoExisteException ex){
            
            throw new UsuarioInexistenteException("No existe un usuario con el id recibido.");
        } 
             
    }
    
}
