package itson.sistemarestaurantedominio.dtos;
/**
 * Clase DTO que representa los datos necesarios para actualizar la información de un cliente
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class ClienteActualizadoDTO {
    
    /**
     * Declaración de atributos de la clase
     */
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    
    /**
     * Método constructor para instanciar la clase ClienteActualizadoDTO
     * @param id Representa el id del cliente
     * @param nombre Representa el nombre del cliente
     * @param apellidoPaterno Representa el apellido paterno del cliente
     * @param apellidoMaterno Representa el apellido materno del cliente
     * @param telefono Representa el teléfono del cliente
     * @param correoElectronico Representa el correo electrónico del cliente
     */
    public ClienteActualizadoDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Getters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
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

}