
package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionProductoSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.ProductoNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinPrecioException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinTipoException;
import java.util.List;


public interface IProductosDAO {
    public abstract Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO) 
            throws ProductoMismoNombreTipoExistenteException,
            RegistroProductoSinNombreException,
            RegistroProductoSinPrecioException,
            RegistroProductoSinTipoException,
            RegistroProductoSinDireccionImagenException;
    
    public abstract List<Producto> consultarProductos();
    
    public abstract List<Producto> consultarProductosNombre(String nombreProductos);
    
    public abstract List<Producto> consultarProductosTipo(String tipoProductos);
    
    public abstract Producto consultarProducto(Long idProducto) throws ProductoNoExisteException;
    
    public abstract void actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO)
            throws ProductoMismoNombreTipoExistenteException,
            RegistroProductoSinNombreException,
            RegistroProductoSinPrecioException,
            RegistroProductoSinTipoException,
            RegistroProductoSinDireccionImagenException,
            ProductoNoExisteException,
            ActualizacionProductoSinIdException;

}
