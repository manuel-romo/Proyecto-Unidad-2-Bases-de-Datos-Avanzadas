
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantenegocio.IProductosBO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import java.util.List;


public class ProductosBO implements IProductosBO{

    private IProductosDAO productosDAO;
    
    public ProductosBO(IProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }
    
    @Override
    public List<Producto> consultarProductos() {
        return null;
    }

    @Override
    public void registrarProducto() {
        
    }
    
}
