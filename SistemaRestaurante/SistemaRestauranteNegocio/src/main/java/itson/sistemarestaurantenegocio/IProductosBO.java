
package itson.sistemarestaurantenegocio;

import itson.sistemarestaurantedominio.Producto;
import java.util.List;


public interface IProductosBO {
    
    public abstract List<Producto> consultarProductos();
    
    public abstract void registrarProducto();
            
}
