
package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.UnidadIngrediente;


/**
 * Clase DTO que contiene los datos necesarios para registrar un nuevo ingrediente,
 * a partir de los datos que ingresa el usuario en su registro.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class NuevoIngredienteDTO {
    /**
     * Objeto String que representa el nombre del nuevo ingrediente.
     */
    private String nombre;
    /**
     * Objeto UnidadIngrediente que representa la unidad del nuevo ingrediente.
     */
    private UnidadIngrediente unidad;
    /**
     * Objeto Float que representa la cantidad en inventario del nuevo ingrediente.
     */
    private Float cantidad;
    /**
     * Objeto String que representa la dirección de la imagen del ingrediente
     * en el sistema.
     */
    private String direccionImagen;
    
    /**
     * Objeto Boolean que indica si el ingrediente está habilitado o no
     */
    private Boolean habilitado;

    /**
     * Constructor de la clase que recibe nombre, unidad, cantidad y dirección de imagen.
     * @param nombre Objeto String que representa el nombre del nuevo ingrediente.
     * @param unidad Objeto UnidadIngrediente que representa la unidad del nuevo ingrediente.
     * @param cantidad Objeto Float que representa la cantidad en inventario del nuevo ingrediente.
     * @param direccionImagen Objeto String que representa la dirección de la imagen del nuevo ingrediente.
     * en el sistema.
     * @param Boolean Objeto Boolean que indica si el ingrediente estará habilitado o no.
     */
    public NuevoIngredienteDTO(String nombre, UnidadIngrediente unidad, Float cantidad, String direccionImagen, Boolean habilitado) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.direccionImagen = direccionImagen;
        this.habilitado = habilitado;
    }

    
    /**
     * Método que permite obtener el nombre del nuevo ingrediente.
     * @return Objeto String que representa el nombre del nuevo ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener la unidad del nuevo igrediente.
     * @return Objeto Objeto UnidadIngrediente que representa la unidad del nuevo ingrediente.
     */
    public UnidadIngrediente getUnidad() {
        return unidad;
    }

    /**
     * Método que permite obtener la cantidad del nuevo ingrediente.
     * @return Objeto Float que representa la cantidad en inventario del nuevo ingrediente.
     */
    public Float getCantidad() {
        return cantidad;
    }

    /**
     * Método que permite obtener la dirección del nuevo ingrediente.
     * @return Objeto String que representa la dirección de la imagen del nuevo ingrediente.
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }
    
    /**
     * Método que permite obtenre el valor del atributo habilitado que tendrá
     * el nuevo ingrediente.
     * @return Objeto Boolean, true si el nuevo ingrediente estará habilitado, false
     * en caso contrario.
     */
    public Boolean getHabilitado() {
        return habilitado;
    }
}
