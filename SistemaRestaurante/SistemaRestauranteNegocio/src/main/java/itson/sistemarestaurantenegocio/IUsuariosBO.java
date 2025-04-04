
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Usuario;
import itson.sistemarestaurantedominio.dtos.CorreoContraseniaInicioSesionDTO;
import itson.sistemarestaurantenegocio.excepciones.ContraseniaIncorrectaException;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.excepciones.FormatoContraseniaInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.FormatoCorreoElectronicoInvalidoException;


public interface IUsuariosBO {
    
    
    public abstract Usuario iniciarSesion(CorreoContraseniaInicioSesionDTO contraseniaInicioSesionDTO) 
            throws FormatoContraseniaInvalidoException, FormatoCorreoElectronicoInvalidoException, 
            UsuarioInexistenteException, ContraseniaIncorrectaException;
    
    public abstract Usuario consultarUsuarioId(Long idUsuario) throws UsuarioInexistenteException;
}
