
package itson.sistemarestaurantenegocio.fabrica;

import itson.sistemarestaurantenegocio.implementaciones.ClientesBO;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.implementaciones.ComandasBO;
import itson.sistemarestaurantenegocio.implementaciones.IngredientesBO;
import itson.sistemarestaurantenegocio.implementaciones.MesasBO;
import itson.sistemarestaurantenegocio.implementaciones.ProductosBO;
import itson.sistemarestaurantenegocio.implementaciones.UsuariosBO;
import itson.sistemarestaurantenegocio.interfaces.IClientesBO;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantepersistencia.IClientesDAO;
import itson.sistemarestaurantepersistencia.IComandasDAO;
import itson.sistemarestaurantepersistencia.IIngredientesDAO;
import itson.sistemarestaurantepersistencia.IMesasDAO;
import itson.sistemarestaurantepersistencia.IProductosDAO;
import itson.sistemarestaurantepersistencia.IUsuariosDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ClientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ComandasDAO;
import itson.sistemarestaurantepersistencia.implementaciones.IngredientesDAO;
import itson.sistemarestaurantepersistencia.implementaciones.MesasDAO;
import itson.sistemarestaurantepersistencia.implementaciones.ProductosDAO;
import itson.sistemarestaurantepersistencia.implementaciones.UsuariosDAO;


public class FabricaObjetoNegocio {
    
    public static IUsuariosBO crearUsuariosBO(){
        IUsuariosDAO usuariosDAO = new UsuariosDAO();
        IUsuariosBO usuariosBO = new UsuariosBO(usuariosDAO);
        return usuariosBO;   
    }
    
    public static IComandasBO crearComandasBO(){
        IComandasDAO comandasDAO = new ComandasDAO();
        IComandasBO comandasBO = new ComandasBO(comandasDAO);
        return comandasBO;   
    }
    
    public static IProductosBO crearProductosBO(){
        IProductosDAO productosDAO = new ProductosDAO();
        IIngredientesDAO ingredientesDAO = new IngredientesDAO();
        IProductosBO productosBO = new ProductosBO(productosDAO, ingredientesDAO);
        return productosBO;   
    }
    
    public static IIngredientesBO crearIngredientesBO(){
        IIngredientesDAO ingredientesDAO = new IngredientesDAO();
        IIngredientesBO ingredientesBO = new IngredientesBO(ingredientesDAO);
        return ingredientesBO;   
    }
    
    public static IMesasBO crearMesasBO(){
        IMesasDAO mesasDAO = new MesasDAO();
        IMesasBO mesasBO = new MesasBO(mesasDAO);
        return mesasBO;   
    }
    
    public static IClientesBO crearClientesBO(){
        IClientesDAO clientesDAO = new ClientesDAO();
        IClientesBO clientesBO = new ClientesBO(clientesDAO);
        return clientesBO;
    }
}
