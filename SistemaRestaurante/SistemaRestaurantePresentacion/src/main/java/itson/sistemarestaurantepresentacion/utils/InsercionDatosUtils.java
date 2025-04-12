
package itson.sistemarestaurantepresentacion.utils;

import itson.sistemarestaurantedominio.Mesa;
import itson.sistemarestaurantedominio.TipoProducto;
import itson.sistemarestaurantedominio.UnidadIngrediente;
import itson.sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import itson.sistemarestaurantedominio.dtos.NuevoProductoDTO;
import itson.sistemarestaurantenegocio.excepciones.CantidadIngredienteInvalidaException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinCantidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinHabilitadoException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteSinUnidadException;
import itson.sistemarestaurantenegocio.excepciones.IngredienteYaExisteException;
import itson.sistemarestaurantenegocio.excepciones.MesaSinNumeroException;
import itson.sistemarestaurantenegocio.excepciones.NombreIngredienteInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.NombreProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.NumeroMesaInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.PrecioProductoInvalidoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinDireccionImagenException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinNombreException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinPrecioException;
import itson.sistemarestaurantenegocio.excepciones.ProductoSinTipoException;
import itson.sistemarestaurantenegocio.excepciones.ProductoYaExisteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite la inserción de datos para probar las operaciones
 * del sistema.
 * 
 * @author 
 */
public class InsercionDatosUtils {
    
    private static IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
    private static IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
    private static IComandasBO comandasBO = FabricaObjetoNegocio.crearComandasBO(); 
    private static IMesasBO mesasBO = FabricaObjetoNegocio.crearMesasBO();
    
    
    public static boolean hayRegistroPrevioMesas(){
        List<Mesa> listaMesas = mesasBO.consultarMesasDisponibles();
        
        return !listaMesas.isEmpty();
        
    }
    
    public static void insertarDatosPrueba(){
        
        try {
            // Inserción de ingredientes:
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Arroz", UnidadIngrediente.GRAMO, 6000F, "imagenes\\imagenesIngredientes\\imagenArroz.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Azucar", UnidadIngrediente.GRAMO, 5000F, "imagenes\\imagenesIngredientes\\imagenAzucar.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Canela", UnidadIngrediente.GRAMO, 3500F, "imagenes\\imagenesIngredientes\\imagenCanela.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Carne res", UnidadIngrediente.GRAMO, 20000F, "imagenes\\imagenesIngredientes\\imagenCarneRes.png",true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Cebolla", UnidadIngrediente.PIEZA, 40F, "imagenes\\imagenesIngredientes\\imagenCebolla.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Chile", UnidadIngrediente.PIEZA, 55F, "imagenes\\imagenesIngredientes\\imagenChile.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Flor de Jamaica", UnidadIngrediente.GRAMO, 2000F, "imagenes\\imagenesIngredientes\\imagenFloresJamaica.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Huevo", UnidadIngrediente.PIEZA, 200F, "imagenes\\imagenesIngredientes\\imagenFloresHuevo.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Limón", UnidadIngrediente.PIEZA, 150F, "imagenes\\imagenesIngredientes\\imagenLimon.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Tomate", UnidadIngrediente.PIEZA, 120F, "imagenes\\imagenesIngredientes\\imagenTomate.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Leche", UnidadIngrediente.MILILITRO, 6000F, "imagenes\\imagenesIngredientes\\imagenLeche.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Vinagre", UnidadIngrediente.MILILITRO, 5400F, "imagenes\\imagenesIngredientes\\imagenVinagre.png", true));
            ingredientesBO.registrarIngrediente(new NuevoIngredienteDTO("Aceite", UnidadIngrediente.MILILITRO, 3000F, "imagenes\\imagenesIngredientes\\imagenAceite.png", true));
            
            // Inserción de mesas:
            mesasBO.registrarMesa(1);
            mesasBO.registrarMesa(2);
            mesasBO.registrarMesa(3);
            mesasBO.registrarMesa(4);
            mesasBO.registrarMesa(5);
            mesasBO.registrarMesa(6);
            mesasBO.registrarMesa(7);
            mesasBO.registrarMesa(8);
            mesasBO.registrarMesa(9);
            mesasBO.registrarMesa(10);
            mesasBO.registrarMesa(11);
            mesasBO.registrarMesa(12);
            mesasBO.registrarMesa(13);
            mesasBO.registrarMesa(14);
            mesasBO.registrarMesa(15);
            mesasBO.registrarMesa(16);
            mesasBO.registrarMesa(17);
            mesasBO.registrarMesa(18);
            mesasBO.registrarMesa(19);
            mesasBO.registrarMesa(20);
            
            // Inserción de productos:
            productosBO.registrarProducto(new NuevoProductoDTO("Agua de jamaica", 20.30F, TipoProducto.BEBIDA, true, "/imagenAguaJamaica.png"));
            productosBO.registrarProducto(new NuevoProductoDTO("Arroz con leche", 35F, TipoProducto.POSTRE, true, "/imagenArrozLeche.png"));
            productosBO.registrarProducto(new NuevoProductoDTO("Carne machaca", 110.90F, TipoProducto.PLATILLO, true, "/imagenCarneMachaca.png"));
            productosBO.registrarProducto(new NuevoProductoDTO("Flan", 40.00F, TipoProducto.POSTRE, true, "/imagenFlan.png"));
            productosBO.registrarProducto(new NuevoProductoDTO("Sopes Sonorenses", 50.00F, TipoProducto.ENTRADA, true, "/imagenSopeSonorense.png"));
            productosBO.registrarProducto(new NuevoProductoDTO("Totopos", 45.50F, TipoProducto.ENTRADA, true, "/imagenTotopos.png"));
            productosBO.registrarProducto(new NuevoProductoDTO("Limonada", 35.99F, TipoProducto.BEBIDA, true, "/imagenLimonada.png"));
            
            
        } catch (NombreIngredienteInvalidoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CantidadIngredienteInvalidaException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredienteYaExisteException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredienteSinUnidadException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredienteSinNombreException ex) {
           Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredienteSinDireccionImagenException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredienteSinCantidadException ex) {
           Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumeroMesaInvalidoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MesaSinNumeroException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NombreProductoInvalidoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrecioProductoInvalidoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductoYaExisteException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductoSinNombreException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductoSinPrecioException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductoSinTipoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductoSinDireccionImagenException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredienteSinHabilitadoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public static void insertar20MesasPrueba(){
        try {
            // Inserción de mesas:
            mesasBO.registrarMesa(1);
            mesasBO.registrarMesa(2);
            mesasBO.registrarMesa(3);
            mesasBO.registrarMesa(4);
            mesasBO.registrarMesa(5);
            mesasBO.registrarMesa(6);
            mesasBO.registrarMesa(7);
            mesasBO.registrarMesa(8);
            mesasBO.registrarMesa(9);
            mesasBO.registrarMesa(10);
            mesasBO.registrarMesa(11);
            mesasBO.registrarMesa(12);
            mesasBO.registrarMesa(13);
            mesasBO.registrarMesa(14);
            mesasBO.registrarMesa(15);
            mesasBO.registrarMesa(16);
            mesasBO.registrarMesa(17);
            mesasBO.registrarMesa(18);
            mesasBO.registrarMesa(19);
            mesasBO.registrarMesa(20);
            
        } catch (NumeroMesaInvalidoException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MesaSinNumeroException ex) {
            Logger.getLogger(InsercionDatosUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   

}
