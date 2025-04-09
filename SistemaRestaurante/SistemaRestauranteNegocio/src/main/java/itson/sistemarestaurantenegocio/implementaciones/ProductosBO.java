package itson.sistemarestaurantenegocio.implementaciones;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantenegocio.excepciones.*;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.excepciones.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductosBO implements IProductosBO {

    private static final Logger LOG = Logger.getLogger(ProductosBO.class.getName());
    private final IProductosDAO productosDAO;

    public ProductosBO(IProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }

    @Override
    public List<Producto> consultarProductos() {
        return productosDAO.consultarProductos();
    }

    @Override
    public List<Producto> consultarProductosNombre(String nombreProducto) {
        return productosDAO.consultarProductosNombre(nombreProducto);
    }

    @Override
    public List<Producto> consultarProductosTipo(String tipoProducto) {
        return productosDAO.consultarProductosTipo(tipoProducto);
    }

    @Override
    public Producto consultarProductoPorId(Long idProducto) throws ProductoBuscadoNoExisteException {
        try {
            return productosDAO.consultarProducto(idProducto);
        } catch (ProductoNoExisteException ex) {
            LOG.log(Level.WARNING, "No se encontró el producto con ID: {0}", idProducto);
            throw new ProductoBuscadoNoExisteException("No existe un producto con el ID especificado.");
        }
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

        // Validaciones
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
    public void actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO)
            throws NombreProductoInvalidoException, 
                   PrecioProductoInvalidoException, 
                   ProductoYaExisteException, 
                   ProductoSinIdException, 
                   ProductoSinNombreException, 
                   ProductoSinPrecioException, 
                   ProductoSinTipoException, 
                   ProductoNoExisteException,
                   ProductoSinDireccionImagenException {

        // Validaciones
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
        } catch (ActualizacionProductoSinIdException ex) {
            throw new ProductoSinIdException("El producto no tiene un ID especificado.");
        }
    }
}