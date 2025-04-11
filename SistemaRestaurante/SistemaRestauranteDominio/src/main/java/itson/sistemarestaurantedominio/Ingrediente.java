
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
import javax.persistence.UniqueConstraint;


@Entity
@Table(
    name = "ingredientes",
    uniqueConstraints = @UniqueConstraint(columnNames = {"nombre", "unidad"})
)
public class Ingrediente implements Serializable {

    public Ingrediente() {
    }

    public Ingrediente(String nombre, Float cantidad, UnidadIngrediente unidad, String direccionImagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.direccionImagen = direccionImagen;
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "cantidad", nullable = false)
    private Float cantidad;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad", nullable = false)
    private UnidadIngrediente unidad;
    
    @Column(name = "direccion_imagen", nullable = false)
    private String direccionImagen;
    
    @OneToMany(mappedBy = "ingrediente")
    private List<IngredienteProducto> productos = new ArrayList<>();
    
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

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public UnidadIngrediente getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadIngrediente unidad) {
        this.unidad = unidad;
    }

    public List<IngredienteProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<IngredienteProducto> productos) {
        this.productos = productos;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
    
    /**
     * Método que permite agregar un objeto IngredienteProducto a la lista de
     * ingredientesProducto de este Ingrediente, garantizando que al objeto 
     * Producto al que hace referencia el objeto IngredienteProducto se le 
     * añada a su lista de ingredientesProducto ese mismo objeto IngredienteProducto.
     * @param ingredienteProducto Objeto IngredienteProducto a agregar a lista de
     * ingredientesProducto de este Ingrediente.
     */
    public void addProducto(IngredienteProducto ingredienteProducto){
        
        if(ingredienteProducto != null && !productos.contains(ingredienteProducto)){
            productos.add(ingredienteProducto);
            ingredienteProducto.setIngrediente(this);
            
            if(ingredienteProducto.getProducto() != null && !ingredienteProducto.getProducto().getIngredientes().contains(ingredienteProducto)){
                ingredienteProducto.getProducto().getIngredientes().add(ingredienteProducto);
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
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.student.itson.dissof.sistemarestaurantedominio.dtos.Ingrediente[ id=" + id + " ]";
    }
    
}
