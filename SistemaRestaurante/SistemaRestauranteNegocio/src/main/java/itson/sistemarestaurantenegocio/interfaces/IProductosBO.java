
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantenegocio.excepciones.NombreProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.PrecioProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoYaExisteException;
import java.util.List;


public interface IProductosBO {
    
    public abstract List<Producto> consultarProductos();
    
    public abstract Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO)throws NombreProductoInvalidoException, PrecioProductoInvalidoException, ProductoYaExisteException;
    
    public abstract Producto buscarProductoPorNombreOCategoria(String nombreOCategoria);
}
