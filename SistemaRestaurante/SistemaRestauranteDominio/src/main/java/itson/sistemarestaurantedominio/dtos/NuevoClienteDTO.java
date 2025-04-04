package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.TipoUsuario;
import java.util.Calendar;

/**
 * @author
 */
public class NuevoClienteDTO {
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private String contrasenia;
    private Calendar fechaRegistro;
    private TipoUsuario tipoUsuario;

    public NuevoClienteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, String contrasenia, Calendar fechaRegistro, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

}