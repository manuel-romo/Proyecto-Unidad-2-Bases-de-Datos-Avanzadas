package itson.sistemarestaurantedominio.dtos;

import java.util.Calendar;
/**
 * Clase DTO que representa los datos necesarios para registrar un cliente
 * @author Yuri Germán García López
 * ID: 0000025283
 */
public class NuevoClienteDTO {
    
    /**
     * Declaración de atributos de la clase
     */
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private Calendar fechaRegistro;
    private Boolean esFrecuente;

    /**
     * Método constructor para instanciar la clase NuevoClienteDTO
     * @param nombre Representa el/los nombre del cliente
     * @param apellidoPaterno Representa el apellido paterno del cliente
     * @param apellidoMaterno Representa el apellido materno del cliente
     * @param telefono Representa el teléfono del cliente
     * @param correoElectronico Representa el correo electrónico del cliente
     * @param fechaRegistro Representa la fecha cuando se registró el cliente
     * @param esFrecuente Representa si el cliente registrado es frecuente o no
     */
    public NuevoClienteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, Calendar fechaRegistro, Boolean esFrecuente) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.esFrecuente = esFrecuente;
    }

    /**
     * Getters para cada atributo de la clase
     */
    
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

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public Boolean getEsFrecuente(){
        return esFrecuente;
    }
}