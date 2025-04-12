
package itson.sistemarestaurantenegocio.interfaces;

import itson.sistemarestaurantedominio.Producto;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantedominio.dtos.ProductoActualizadoDTO;
import itson.sistemarestaurantenegocio.excepciones.IdProductoNuloException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinIdException;
import itson.sistemarestaurantenegocio.excepciones.NombreProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.PrecioProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoConsultadoNoExisteException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinIdException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinPrecioException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinTipoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoYaExisteException;
import itson.sistemarestaurantepersistencia.excepciones.ProductoNoExisteException;
import java.util.List;


public interface IProductosBO {

    /**
     * Consulta todos los productos registrados.
     *
     * @return Una lista de productos.
     */
    public abstract List<Producto> consultarProductos();

    /**
     * Consulta productos que coincidan con un nombre.
     *
     * @param nombreProducto El nombre del producto a buscar.
     * @return Una lista de productos que coincidan con el nombre.
     */
    public abstract List<Producto> consultarProductosNombre(String nombreProducto);

    /**
     * Consulta productos que coincidan con un tipo.
     *
     * @param tipoProducto El tipo del producto a buscar.
     * @return Una lista de productos que coincidan con el tipo.
     */
    public abstract List<Producto> consultarProductosTipo(String tipoProducto);

    /**
     * Consulta un producto por su ID.
     *
     * @param idProducto El ID del producto a buscar.
     * @return El producto encontrado.
     * @throws ProductoConsultadoNoExisteException Si no existe un producto con el ID especificado.
     */
    public abstract Producto consultarProductoPorId(Long idProducto) throws ProductoConsultadoNoExisteException;

    /**
     * Registra un nuevo producto.
     *
     * @param nuevoProductoDTO Los datos del producto a registrar.
     * @return El producto registrado.
     * @throws NombreProductoInvalidoException Si el nombre del producto es inválido.
     * @throws PrecioProductoInvalidoException Si el precio del producto es inválido.
     * @throws ProductoYaExisteException Si el producto ya existe.
     * @throws ProductoSinNombreException Si el producto no tiene nombre.
     * @throws ProductoSinPrecioException Si el producto no tiene precio.
     * @throws ProductoSinTipoException Si el producto no tiene tipo.
     * @throws ProductoSinDireccionImagenException Si el producto no tiene dirección de imagen.
     */
    public abstract Producto registrarProducto(NuevoProductoDTO nuevoProductoDTO)
            throws NombreProductoInvalidoException, 
                   PrecioProductoInvalidoException, 
                   ProductoYaExisteException, 
                   ProductoSinNombreException, 
                   ProductoSinPrecioException, 
                   ProductoSinTipoException, 
                   ProductoSinDireccionImagenException;

    /**
     * Actualiza un producto existente.
     *
     * @param productoActualizadoDTO Los datos actualizados del producto.
     * @throws NombreProductoInvalidoException Si el nombre del producto es inválido.
     * @throws PrecioProductoInvalidoException Si el precio del producto es inválido.
     * @throws ProductoYaExisteException Si ya existe un producto con el mismo nombre y tipo.
     * @throws ProductoSinIdException Si el producto no tiene un ID especificado.
     * @throws ProductoSinNombreException Si el producto no tiene nombre.
     * @throws ProductoSinPrecioException Si el producto no tiene precio.
     * @throws ProductoSinTipoException Si el producto no tiene tipo.
     * @throws ProductoSinDireccionImagenException Si el producto no tiene dirección de imagen.
     * @throws ProductoConsultadoNoExisteException Si el producto no existe.
     */
    public abstract void actualizarProducto(ProductoActualizadoDTO productoActualizadoDTO)
            throws NombreProductoInvalidoException, 
                   PrecioProductoInvalidoException, 
                   ProductoYaExisteException, 
                   ProductoSinIdException, 
                   ProductoSinNombreException, 
                   ProductoSinPrecioException, 
                   ProductoSinTipoException,
                   ProductoSinDireccionImagenException,
                   ProductoConsultadoNoExisteException;
    
    public abstract boolean consultarDisponibilidadProducto(Long idProducto,  float cantidadProducto)
            throws IdProductoNuloException,
            ProductoConsultadoNoExisteException,
            IngredienteSinIdException,
            IngredienteConsultadoNoExisteException;
    
    public void eliminarIngredientes();

}
