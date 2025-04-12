
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

/**
 * Clase que representa el objeto de negocio Ingrediente, utilizado para crear
 * objetos de tipo Producto; además son descontados en cantidad cada vez que estos
 * son preparados.
 * 
 * @author Manuel Romo López
 */
@Entity
@Table(
    name = "ingredientes",
    uniqueConstraints = @UniqueConstraint(columnNames = {"nombre", "unidad"})
)
public class Ingrediente implements Serializable {

    public Ingrediente() {
    }

    /**
     * Constructor que recibe el nombre, cantidad, unidad, dirección de imagen y 
     * el valor que determina si el producto está habilitado.
     * @param nombre Objeto String que representa el nombre del Ingrediente.
     * @param cantidad Objeto Float que representa la cantidad del Ingrediente en
     * inventario.
     * @param unidad Objeto UnidadIngrediente que representa la unidad de medida
     * de la cantidad de este Ingrediente.
     * @param direccionImagen Objeto String que representa la dirección de la imagen
     * del ingrediente dentro del sistema.
     * @param habilitado Objeto Boolean, que indica si el Ingrediente está habilitado, es 
     * decir, puede ser utilizado para crear productos, o no.
     */
    public Ingrediente(String nombre, Float cantidad, UnidadIngrediente unidad, String direccionImagen, Boolean habilitado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.direccionImagen = direccionImagen;
        this.habilitado = habilitado;
    }

    /**
     * Objeto Long que representa el Id del ingrediente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long id;
    
    /**
     * Objeto String que representa el nombre del ingrediente.
     */
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    
    /**
     * Objeto Float que representa la cantidad del ingrediente en inventario.
     */
    @Column(name = "cantidad", nullable = false)
    private Float cantidad;
    
    /**
     * Objeto UnidadIngrediente que representa la unidad de medida del ingrediente.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad", nullable = false)
    private UnidadIngrediente unidad;
    
    /**
     * Objeto de tipo String que representa la dirección de la imagen del Ingrediente
     * dentro del sistema.
     */
    @Column(name = "direccion_imagen", nullable = false)
    private String direccionImagen;
    
    /**
     * Objeto Boolean que indica si este Ingrediente está habilitado o no.
     */
    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado;
    
    /**
     * Objeto {@literal List<IngredienteProducto>} que representa la relación
     * Uno a Muchos existente con la entidad IngredienteProducto, que representa
     * el uso de este Ingrediente por un Producto.
     */
    @OneToMany(mappedBy = "ingrediente")
    private List<IngredienteProducto> productos = new ArrayList<>();
    
    /**
     * Método que permite obtener el Id de este Ingrediente.
     * @return Objeto Long que representa el Id del Ingrediente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite establecer el Id de este Ingrediente.
     * @param id Objeto Long que representa el Id del Ingrediente a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el nombre de este Ingrediente.
     * @return Objeto String que representa el nombre de este Ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite establecer el nombre de este Ingrediente.
     * @param nombre Objeto String que representa el nuevo nombre del
     * Ingrediente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que permite obtener la cantidad de este Ingrediente en inventario.
     * @return Objeto Float que representa la cantidad disponible de este Ingrediente
     * en inventario.
     */
    public Float getCantidad() {
        return cantidad;
    }

    /**
     * Método que permite establecer la cantidad de este Ingrediente en inventario.
     * @param cantidad Objeto Float que representa la nueva cantidad disponible 
     * de este Ingrediente en inventario.
     */
    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método que permite obtener la unidad de medida de este Ingrediente.
     * @return Objeto UnidadIngrediente que representa la unidad de medida
     * de este Ingrediente.
     */
    public UnidadIngrediente getUnidad() {
        return unidad;
    }

    /**
     * Método que permite establecer la undiad de medida de este Ingrediente.
     * @param unidad Objeto UnidadIngrediente que representa la nueva unidad de
     * medida de este Ingrediente.
     */
    public void setUnidad(UnidadIngrediente unidad) {
        this.unidad = unidad;
    }

    /**
     * Método que permite obtener la lista de objetos IngredienteProducto que
     * hacen referencia este Ingrediente.
     * @return Objeto {@literal List<IngredienteProducto>} que representa la lista 
     * de objetos IngredienteProducto que hacen referencia este Ingrediente.
     */
    public List<IngredienteProducto> getProductos() {
        return productos;
    }

    /**
     * Método que permite establecer la lista de objetos IngredienteProducto que
     * hacen referencia este Ingrediente.
     * @param productos Objeto {@literal List<IngredienteProducto>} que representa 
     * la nueva lista de objetos IngredienteProducto que hacen referencia este Ingrediente.
     */
    public void setProductos(List<IngredienteProducto> productos) {
        this.productos = productos;
    }

    /**
     * Método que permite obtener la dirección de imagen de este Ingrediente.
     * @return Objeto String que representa la nueva dirección de imagen representativa 
     * de este Ingrediente.
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }

    /**
     * Método que permite establecer la dirección de imagen de este Ingrediente.
     * @param direccionImagen Objeto String que representa la dirección de imagen
     * representativa de este Ingrediente.
     */
    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
    
    /**
     * Método que permite agregar un objeto IngredienteProducto a la lista de
     * ingredientesProducto de este Ingrediente, garantizando que al objeto 
     * Producto al que hace referencia el objeto IngredienteProducto, se le 
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
    
    /**
     * Método que permite obtener el hashCode de este objeto Ingrediente, a paritr
     * de su atributo id.
     * @return Dato int que representa el hash de este objeto Ingrediente.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Método que permite comparar con otros objetos, este objeto de tipo Ingrediente,
     * para determinar si son iguales, tomando como referencia el valor de su atributo id.
     * @param object Objeto Object que representa el objeto con el que se va a comparar
     * este atributo Ingrediente.
     * @return 
     */
    @Override
    public boolean equals(Object object) { 
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Método que permite obtener un Strnig con el valor del atributo Id de
     * este Ingrediente.
     * @return Objeto String que contiene el valor del atributo Id de
     * este Ingrediente.
     */
    @Override
    public String toString() {
        return "Ingrediente[ id=" + id + " ]";
    }
    
}
