
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantenegocio.excepciones.NombreProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.PrecioProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoYaExisteException;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductosBO implements IProductosBO{

    private IProductosDAO productosDAO;
    
    public ProductosBO(IProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }
    
    @Override
    public List<Producto> consultarProductos() {
        return productosDAO.consultarProductos();
    }

    @Override
    public Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO) 
            throws NombreProductoInvalidoException, PrecioProductoInvalidoException, ProductoYaExisteException{
        
        if(nuevoProductoDTO.getNombre() == null || nuevoProductoDTO.getNombre().isEmpty()){
            throw new NombreProductoInvalidoException("Debe ingresar un nombre de producto.");
        }
        
        if(nuevoProductoDTO.getPrecioCadena()== null || nuevoProductoDTO.getPrecioCadena().isEmpty()){
            throw new PrecioProductoInvalidoException("Debe ingresar un precio para el producto.");
        }
        
        try {
            Float precioFloat = Float.valueOf(nuevoProductoDTO.getPrecioCadena());
            nuevoProductoDTO.setPrecioFloat(precioFloat);
        } catch (NumberFormatException ex){
            throw new PrecioProductoInvalidoException("Debe ingresar un precio para el producto");
        }
        
        try{
            return productosDAO.registrarProducto(nuevoProductoDTO);
        } catch (ProductoMismoNombreTipoExistenteException ex){
            throw new ProductoYaExisteException("Ya existe un producto con el mismo nombre y tipo");
        }
    }

    @Override
    public Producto buscarProductoPorNombreOCategoria(String nombreOCategoria) {
        return productosDAO.buscarProductoPorNombreOCategoria(nombreOCategoria);
    }
}
