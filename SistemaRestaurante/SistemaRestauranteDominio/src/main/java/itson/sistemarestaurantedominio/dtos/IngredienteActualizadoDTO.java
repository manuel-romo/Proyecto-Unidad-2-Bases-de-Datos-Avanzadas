
package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.UnidadIngrediente;


public class IngredienteActualizadoDTO {
    
    private Long id;
    private String nombre;
    private UnidadIngrediente unidad;
    private Float cantidad;
    private String direccionImagen;

    public IngredienteActualizadoDTO(Long id, String nombre, UnidadIngrediente unidad, Float cantidad, String direccionImagen) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.direccionImagen = direccionImagen;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadIngrediente getUnidad() {
        return unidad;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }
    
    
    
}
