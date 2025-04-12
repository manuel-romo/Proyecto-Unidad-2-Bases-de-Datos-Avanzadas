package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.UnidadIngrediente;

/**
 * Clase DTO que contiene los nuevos valores de los atributos del objeto
 * Igrediente con el id del parámetro, que será modificado.
 * 
 * @author Manuel Romo López
 */
public class IngredienteActualizadoDTO {
    /**
     * Objeto Long que representa el Id del objeto Ingrediente que será modificado
     */
    private Long id;
    /**
     * Objeto String que representa el nuevo nombre del objeto Ingrediente.
     */
    private String nombre;
    /**
     * Objeto UnidadIngrediente que representa la nueva unidad del objeto 
     * Ingrediente.
     */
    private UnidadIngrediente unidad;
    /**
     * Objeto Float que representa la nueva cantidad del objeto Ingrediente.
     */
    private Float cantidad;
    /**
     * Objeto String que representa la nueva dirección de imagen del objeto 
     * Ingrediente.
     */
    private String direccionImagen;
    /**
     * Objeto String que representa el nuevo estado de habilitado que tendrá
     * el objeto Ingrediente.
     */
    private Boolean habilitado;

    public IngredienteActualizadoDTO(Long id, String nombre, UnidadIngrediente unidad, Float cantidad, String direccionImagen, Boolean habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.direccionImagen = direccionImagen;
        this.habilitado = habilitado;
    }

    /**
     * Método que permite obtener el id del ingrediente que será modificado.
     * @return Objeto Long que representa el Id del ingrediente que será modificado.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el nuevo nombre del ingrediente que será modificado.
     * @return Objeto String que representa el nuveo nombre del ingrediente que será modificado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener la nueva unidad del ingrediente que será modificado.
     * @return Objeto UnidadIngrediente que representa la nueva unidad del ingrediente
     * que será modificado.
     */
    public UnidadIngrediente getUnidad() {
        return unidad;
    }

    /**
     * Método que permite obtene la nueva cantidad del ingrediente que será modificado.
     * @return Objeto Float que representa la nueva cantidad del ingrediente
     * que será modificado.
     */
    public Float getCantidad() {
        return cantidad;
    }

    /**
     * Método que permite obtener la nueva dirección del ingrediente que será
     * modificado.
     * @return Objeto String que representa la nueva dirección del ingrediente que
     * será modificado.
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }

    /**
     * Método que permite obtener el nuevo estado de habilitado del ingrediente
     * que será modificado.
     * @return Objeto Boolean que representa el nuevo valor de estado del ingrediente
     * que será modificado.
     */
    public Boolean getHabilitado() {
        return habilitado;
    }
  
}