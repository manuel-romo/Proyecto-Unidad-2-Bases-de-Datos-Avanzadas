
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import itson.sistemarestaurantepresentacion.excepciones.SesionUsuarioInvalidaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdIngrediente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Control implements IMediador{

    private PantallaInicial pantallaInicial;
    private IniciarSesion formIniciarSesion;
    private MenuPrincipal formMenuPrincipal;
    
    private IVistaReceptoraIdIngrediente formReceptorRespuestaBusquedaIngrediente;
    
    
    private IngredientesPrincipal formIngredientesPrincipal;
    private RegistroIngrediente formRegistroIngrediente;
    private EditarIngrediente formEditarIngrediente;
    private BuscadorIngredientes formBuscadorIngredientes;
     
    /**
     * Método que permite mostrar la pantalla inicial del sistema.
     */
    @Override
    public void mostrarPantallaInicial() {
        pantallaInicial = new PantallaInicial(this);
        pantallaInicial.setVisible(true);
    }
    
    /**
     * Método que permite mostrar la pantalla inicial del sistema, cerrando 
     * la ventana abierta previamente.
     */
    @Override
    public void mostrarPantallaInicial(JFrame frameActual) {
        pantallaInicial = new PantallaInicial(this);
        pantallaInicial.setVisible(true);
    }

    /**
     * Método que permite crear y mostrar el formulario de inicio de sesión.
     */
    @Override
    public void mostrarInicioSesion(JFrame frameActual) {
        frameActual.dispose();
        IUsuariosBO usuariosBO = FabricaObjetoNegocio.crearUsuariosBO();
        formIniciarSesion = new IniciarSesion(this, usuariosBO);
        formIniciarSesion.setVisible(true);
    }
    
    @Override
    public void mostrarMenuPrincipal(JFrame frameActual) { 
        frameActual.dispose();
        IUsuariosBO usuariosBO = FabricaObjetoNegocio.crearUsuariosBO();

        formMenuPrincipal = new MenuPrincipal(this, usuariosBO);

        formMenuPrincipal.setVisible(true);
    }

    @Override
    public void mostrarIngredientesPrincipal(JFrame frameActual) {
        frameActual.dispose();
        IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
        formIngredientesPrincipal = new IngredientesPrincipal(this, ingredientesBO);
        formIngredientesPrincipal.setVisible(true);
    }

    @Override
    public void mostrarRegistroIngrediente(JFrame frameActual) {
        frameActual.dispose();
        IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
        formRegistroIngrediente = new RegistroIngrediente(this, ingredientesBO);
        formRegistroIngrediente.setVisible(true);
    }

    @Override
    public void mostrarEditarIngrediente(JFrame frameActual, Long idIngrediente) {
        frameActual.dispose();
        IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
        formEditarIngrediente = new EditarIngrediente(this, ingredientesBO, idIngrediente);
        formEditarIngrediente.setVisible(true);
    }

    @Override
    public void mostrarBuscadorIngredientes(IVistaReceptoraIdIngrediente vistaReceptoraIdIngrediente) {
        vistaReceptoraIdIngrediente.habilitar(false);
        formReceptorRespuestaBusquedaIngrediente = (IVistaReceptoraIdIngrediente)vistaReceptoraIdIngrediente;
        IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
        formBuscadorIngredientes = new BuscadorIngredientes(this, ingredientesBO);
        formBuscadorIngredientes.setVisible(true);
    }
    
    @Override
    public void actualizarVentanaResultadoBusquedaIngrediente(JFrame buscadorIngredientes, Long idIngrediente){
        
        formReceptorRespuestaBusquedaIngrediente.setIdIngrediente(idIngrediente);
        formReceptorRespuestaBusquedaIngrediente.habilitar(true);
        buscadorIngredientes.dispose();
    }
    
    @Override
    public void cerrarBuscador(JFrame buscadorCerrar){   
        formReceptorRespuestaBusquedaIngrediente.habilitar(true);
        buscadorCerrar.dispose();
    }

    
    
    
    
}
