
package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.TipoProducto;

public class ProductoActualizadoDTO {

    private Long id;
    private String nombre;
    private Float precio;
    private TipoProducto tipo;
    private String direccionImagen;

    public ProductoActualizadoDTO(Long id, String nombre, Float precio, TipoProducto tipo, String direccionImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.direccionImagen = direccionImagen;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }
}