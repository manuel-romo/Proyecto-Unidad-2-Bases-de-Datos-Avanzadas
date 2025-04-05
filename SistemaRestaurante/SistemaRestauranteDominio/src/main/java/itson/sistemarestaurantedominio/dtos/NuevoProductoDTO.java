package itson.sistemarestaurantedominio.dtos;

import itson.sistemarestaurantedominio.TipoProducto;

public class NuevoProductoDTO {
    private String nombre;
    private Float precioFloat;
    private String precioCadena;
    private TipoProducto tipo;
    private Boolean habilitado;
    private String direccionImagen;
    
    public NuevoProductoDTO(){
    }

    public NuevoProductoDTO(String nombre, String precioCadena, TipoProducto tipo, Boolean habilitado, String direccionImagen) {
        this.nombre = nombre;
        this.precioCadena = precioCadena;
        this.tipo = tipo;
        this.habilitado = habilitado;
        this.direccionImagen = direccionImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecioFloat() {
        return precioFloat;
    }

    public void setPrecioFloat(Float precioFloat) {
        this.precioFloat = precioFloat;
    }

    public String getPrecioCadena() {
        return precioCadena;
    }

    public void setPrecioCadena(String precioCadena) {
        this.precioCadena = precioCadena;
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

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
}
