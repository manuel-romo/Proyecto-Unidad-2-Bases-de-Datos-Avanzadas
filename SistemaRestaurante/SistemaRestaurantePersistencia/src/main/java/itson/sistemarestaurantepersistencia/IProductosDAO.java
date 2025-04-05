
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import java.util.List;


public interface IProductosDAO {
    Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO) throws ProductoMismoNombreTipoExistenteException;
    
    List<Producto> consultarProductos();
    
    Producto buscarProductoPorNombreOCategoria(String nombreOCategoria);
}
