package itson.sistemarestaurantedominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Clase que representa el mapeo de la entidad cliente 
 * @author Yuri Germán García López
 * ID: 00000252583
 */

/**
 * Creación de la tabla clientes
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    /**
     * Constructor por defecto
     */
    public Cliente() {
    }

    /**
     * Constructor sin referencia al correo electrónico
     * @param nombres Representa el/los nombres  del cliente
     * @param apellidoPaterno Representa el apellido paterno del cliente
     * @param apellidoMaterno Representa el apellido materno del cliente
     * @param telefono Representa el número de teléfono del cliente
     * @param fechaRegistro Representa la fecha cuando se registró el cliente
     * @param esFrecuente Representa si un cliente es frecuente o no
     */
    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, Calendar fechaRegistro, Boolean esFrecuente) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.esFrecuente = esFrecuente;
    }

    /**
     * Método constructor con referencia a todos sus atributos
     * @param nombres Representa el/los nombres completo del cliente
     * @param apellidoPaterno Representa el apellido paterno del cliente
     * @param apellidoMaterno Representa el apellido materno del cliente
     * @param telefono Representa el número de teléfono del cliente
     * @param correoElectronico Representa el correo electrónico del cliente
     * @param fechaRegistro Representa la fecha cuando se registró el cliente
     * @param esFrecuente Representa si un cliente es frecuente o no
     */
    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, Calendar fechaRegistro, Boolean esFrecuente) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.esFrecuente = esFrecuente;
    }

    /**
     * Creación de la columna id_cliente dentro de la tabla clientes
     * Genera un id_cliente autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;
    
    /**
     * Creación de la columna nombres dentro de la tabla clientes
     * NO ADMITE valores nulos
     */
    @Column(name = "nombres", nullable = false)
    private String nombres;
    
    /**
     * Creación de la columna apellido_paterno dentro de la tabla clientes
     * NO ADMITE valores nulos
     */
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;
    
    /**
     * Creación de la columna apellido_materno dentro de la tabla clientes
     * NO ADMITE calores nulos
     */
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;
    
    /**
     * Creación de la columna telefono dentro de la tabla clientes
     * NO ADMITE valores nulos
     * Es un atributo único
     */
    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;
    
    /**
     * Creación de la columna correo_electronico dentro de la tabla clientes
     * ADMITE valores nulos
     * Es un atributo único
     */
    @Column(name = "correo_electronico", nullable = true, unique = true)
    private String correoElectronico;
    
    /**
     * Creación de la columna fecha_registro dentro de la tabla clientes
     * NO ADMITE valores nulos
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    private Calendar fechaRegistro;
    
    /**
     * Creación de la columna es_frecuente dentro de la tabla clientes
     * NO ADMITE valores nulos
     */
    @Column(name = "es_frecuente", nullable = false)
    private Boolean esFrecuente;
    
    /**
     * Relación OneToMany con comandas
     */
    @OneToMany(mappedBy = "cliente")
    private List<Comanda> comandas = new ArrayList();

    /**
     * Getter y Setters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(Boolean esFrecuente) {
        this.esFrecuente = esFrecuente;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
    
    /**
     * Método que permite asignarle una comanda a un cliente
     * @param comanda Representa un objeto de tipo comanda
     */
    public void agregarComanda(Comanda comanda){
        comandas.add(comanda);
        comanda.setCliente(this);
    }

    /**
     * Método que genera hashCode para objetos de la clase entidad Cliente
     * @return hash de los objetos de la clase
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * Método que compara objetos de la clase a partir de su id
     * @param object Representa un objeto de la clase a comparar
     * @return valor booleano para identificar coincidencias
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Método toString que concatena e imprime los objetos de la clase
     * @return String con los atributos del cliente
     */
    @Override
    public String toString() {
        return "edu.student.itson.dissof.sistemarestaurantedominio.dtos.Cliente[ id=" + id + " ]";
    }
    
}