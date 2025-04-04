
package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.UnidadIngrediente;


public class NuevoIngredienteDTO {
    private String nombre;
    private UnidadIngrediente unidad;
    private String cantidadCadena;
    private Float cantidadFloat;
    private String direccionImagen;

    public NuevoIngredienteDTO(String nombre, UnidadIngrediente unidad, String cantidadCadena, String direccionImagen) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidadCadena = cantidadCadena;
        this.direccionImagen = direccionImagen;
    }

    public Float getCantidadFloat() {
        return cantidadFloat;
    }

    public void setCantidadFloat(Float cantidadFloat) {
        this.cantidadFloat = cantidadFloat;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadIngrediente getUnidad() {
        return unidad;
    }

    public String getCantidadCadena() {
        return cantidadCadena;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    
    
    
    
}
