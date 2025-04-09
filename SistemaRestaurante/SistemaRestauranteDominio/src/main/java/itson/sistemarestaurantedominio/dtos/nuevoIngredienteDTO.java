
package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.UnidadIngrediente;


public class NuevoIngredienteDTO {
    private String nombre;
    private UnidadIngrediente unidad;
    private Float cantidad;
    private String direccionImagen;

    public NuevoIngredienteDTO(String nombre, UnidadIngrediente unidad, Float cantidad, String direccionImagen) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.direccionImagen = direccionImagen;
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
