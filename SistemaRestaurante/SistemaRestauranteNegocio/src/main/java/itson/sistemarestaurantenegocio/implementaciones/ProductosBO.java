
package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantenegocio.excepciones.NombreProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.PrecioProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoBuscadoNoExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ProductoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinPrecioException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinTipoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoYaExisteException;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.excepciones.ActualizacionProductoSinIdException;
import itson.sistemarestaurantepersistencia.excepciones.ProductoMismoNombreTipoExistenteException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinDireccionImagenException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinNombreException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinPrecioException;
import itson.sistemarestaurantepersistencia.excepciones.RegistroProductoSinTipoException;
import java.util.List;


public class ProductosBO implements IProductosBO {

    private final IProductosDAO productosDAO;

    public ProductosBO(IProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }

    @Override
    public Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO)
            throws NombreProductoInvalidoException, 
                   PrecioProductoInvalidoException, 
                   ProductoYaExisteException, 
                   ProductoSinNombreException, 
                   ProductoSinPrecioException, 
                   ProductoSinTipoException, 
                   ProductoSinDireccionImagenException {

        if (nuevoProductoDTO.getNombre() == null || nuevoProductoDTO.getNombre().isEmpty()) {
            throw new NombreProductoInvalidoException("Debe ingresar un nombre de producto.");
        }

        if (nuevoProductoDTO.getPrecio() == null) {
            throw new PrecioProductoInvalidoException("Debe ingresar un precio válido para el producto.");
        }

        if (nuevoProductoDTO.getTipo() == null) {
            throw new ProductoSinTipoException("Debe especificar un tipo para el producto.");
        }

        if (nuevoProductoDTO.getDireccionImagen() == null || nuevoProductoDTO.getDireccionImagen().isEmpty()) {
            throw new ProductoSinDireccionImagenException("Debe especificar una dirección de imagen para el producto.");
        }

        try {
            return productosDAO.registrarProducto(nuevoProductoDTO);
        } catch (ProductoMismoNombreTipoExistenteException ex) {
            throw new ProductoYaExisteException("Ya existe un producto con el mismo nombre y tipo.");
        } catch (RegistroProductoSinNombreException ex) {
            throw new ProductoSinNombreException("El producto no tiene nombre.");
        } catch (RegistroProductoSinPrecioException ex) {
            throw new ProductoSinPrecioException("El producto no tiene precio.");
        } catch (RegistroProductoSinTipoException ex) {
            throw new ProductoSinTipoException("El producto no tiene tipo.");
        } catch (RegistroProductoSinDireccionImagenException ex) {
            throw new ProductoSinDireccionImagenException("El producto no tiene dirección de imagen.");
        }
    }

    @Override
    public List<Producto> consultarProductos() {
        List<Producto> listaProductosConsultados = productosDAO.consultarProductos();
        
        return listaProductosConsultados;
    }

    @Override
    public List<Producto> consultarProductosNombre(String nombreProducto) {
        List<Producto> listaProductosConsultados = productosDAO.consultarProductosNombre(nombreProducto);
        
        return listaProductosConsultados;
    }

    public List<Producto> consultarProductosTipo(String tipoProducto) {
        List<Producto> listaProductosConsultados = productosDAO.consultarProductosTipo(tipoProducto);
        
        return listaProductosConsultados;

    }

    public Producto consultarProductoPorId(Long idProducto) throws ProductoBuscadoNoExisteException {
        
        Producto productoConsultado;
        try {
            productoConsultado = productosDAO.consultarProducto(idProducto);
        } catch (ProductoNoExisteException ex) {
            throw new ProductoBuscadoNoExisteException("No existe el Id del producto buscado.");
        }
        
        return productoConsultado;
    }

    @Override
    public void actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO) 
            throws NombreProductoInvalidoException, 
                   PrecioProductoInvalidoException, 
                   ProductoYaExisteException, 
                   ProductoSinIdException, 
                   ProductoSinNombreException, 
                   ProductoSinPrecioException, 
                   ProductoBuscadoNoExisteException,
                   ProductoSinTipoException, 
                   ProductoSinDireccionImagenException, 
                   ProductoNoExisteException {

        if (productoActualizadoDTO.getId() == null) {
            throw new ProductoSinIdException("El producto no tiene un ID especificado.");
        }

        if (productoActualizadoDTO.getNombre() == null || productoActualizadoDTO.getNombre().isEmpty()) {
            throw new NombreProductoInvalidoException("Debe ingresar un nombre de producto.");
        }

        if (productoActualizadoDTO.getPrecio() == null) {
            throw new PrecioProductoInvalidoException("Debe ingresar un precio válido para el producto.");
        }

        if (productoActualizadoDTO.getTipo() == null) {
            throw new ProductoSinTipoException("Debe especificar un tipo para el producto.");
        }

        if (productoActualizadoDTO.getDireccionImagen() == null || productoActualizadoDTO.getDireccionImagen().isEmpty()) {
            throw new ProductoSinDireccionImagenException("Debe especificar una dirección de imagen para el producto.");
        }

        try {
            productosDAO.actualizarProducto(productoActualizadoDTO);
        } catch (ProductoMismoNombreTipoExistenteException ex) {
            throw new ProductoYaExisteException("Ya existe un producto con el mismo nombre y tipo.");
        } catch (ProductoNoExisteException ex) {
            throw new ProductoNoExisteException("No existe el producto que se intentó actualizar.");
        } catch (RegistroProductoSinNombreException ex) {
            throw new ProductoSinNombreException("El producto no tiene un nombre.");
        } catch (RegistroProductoSinPrecioException ex) {
            throw new ProductoSinPrecioException("El producto no tiene precio.");
        } catch (RegistroProductoSinTipoException ex) {
            throw new ProductoSinTipoException("El producto no tiene tipo.");
        } catch (RegistroProductoSinDireccionImagenException ex) {
            throw new ProductoSinDireccionImagenException("El producto no tiene dirección de imagen.");
        } catch (ActualizacionProductoSinIdException ex){
            throw new ProductoSinIdException("El ingrediente no tiene id");
        }
    }
}