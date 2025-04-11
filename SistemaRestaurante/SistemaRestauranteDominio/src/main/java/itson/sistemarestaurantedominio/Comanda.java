
package itson.sistemarestaurantedominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "comandas")
public class Comanda implements Serializable {

    public Comanda() {
    }
    
    public Comanda(EstadoComanda estado) {
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Long id;

    @Column(name = "folio", nullable = false)
    private String folio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_creacion", nullable = false)
    private Calendar fechaHoraCreacion; 
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoComanda estado;
    
    @OneToMany(mappedBy = "comanda")
    private List<ProductoComanda> productosSolicitados;
    
    @ManyToOne()
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn(name = "id_mesa", nullable = true)
    private Mesa mesa;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Calendar getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Calendar fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public List<ProductoComanda> getProductosSolicitados() {
        return productosSolicitados;
    }

    public void setProductosSolicitados(List<ProductoComanda> productosSolicitados) {
        this.productosSolicitados = productosSolicitados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
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
        if (!(object instanceof Comanda)) {
            return false;
        }
        Comanda other = (Comanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.student.itson.dissof.sistemarestaurantedominio.dtos.Comanda[ id=" + id + " ]";
    }
    
}
