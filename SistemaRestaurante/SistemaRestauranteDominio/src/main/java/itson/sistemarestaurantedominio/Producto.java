
package itson.sistemarestaurantedominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    public Producto(String nombre, Float precio, TipoProducto tipo, Boolean habilitado) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.habilitado = habilitado;
    }

    public Producto() {
    } 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "precio", nullable = false)
    private Float precio;
    
    @Enumerated(EnumType.STRING)
    @Column(name  = "tipo", nullable = false)
    private TipoProducto tipo;
    
    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado;
    
    @OneToMany(mappedBy = "producto")
    private List<IngredienteProducto> ingredientes = new ArrayList<>();
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<IngredienteProducto> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteProducto> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    public void agregarIngrediente(IngredienteProducto ingrediente){
        if(ingrediente != null && !ingredientes.contains(ingrediente)){
            ingredientes.add(ingrediente);
            ingrediente.setProducto(this);
            
            if(ingrediente.getIngrediente() != null && !ingrediente.getIngrediente().getProductos().contains(ingrediente)){
                ingrediente.getIngrediente().getProductos().add(ingrediente);
            }
        }
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.student.itson.dissof.sistemarestaurantedominio.dtos.Producto[ id=" + id + " ]";
    }
    
}
