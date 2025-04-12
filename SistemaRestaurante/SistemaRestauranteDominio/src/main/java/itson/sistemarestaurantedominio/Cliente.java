
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


@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    public Cliente() {
    }

    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, Calendar fechaRegistro) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico, Calendar fechaRegistro) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;
    
    @Column(name = "nombres", length = 50, nullable = false)
    private String nombres;
    
    @Column(name = "apellido_paterno", length = 50, nullable = false)
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno", length = 50, nullable = false)
    private String apellidoMaterno;
    
    @Column(name = "telefono", length = 10, nullable = false, unique = true)
    private String telefono;
    
    @Column(name = "correo_electronico", length = 320, nullable = true, unique = true)
    private String correoElectronico;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false)
    private Calendar fechaRegistro;
    
    @OneToMany(mappedBy = "cliente")
    private List<Comanda> comandas = new ArrayList();

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

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
    
    public void agregarComanda(Comanda comanda){
        comandas.add(comanda);
        comanda.setCliente(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "edu.student.itson.dissof.sistemarestaurantedominio.dtos.Cliente[ id=" + id + " ]";
    }
    
}
