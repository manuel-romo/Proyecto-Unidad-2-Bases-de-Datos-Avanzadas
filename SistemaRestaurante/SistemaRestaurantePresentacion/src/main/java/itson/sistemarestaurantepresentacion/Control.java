
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.excepciones.UsuarioInexistenteException;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import itson.sistemarestaurantepresentacion.excepciones.SesionUsuarioInvalidaException;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Control implements IMediador{

    PantallaInicial pantallaInicial;
    IniciarSesion formIniciarSesion;
    MenuPrincipal formMenuPrincipal;
    
    IngredientesPrincipal formIngredientesPrincipal;
    RegistroIngrediente formRegistroIngrediente;
    EditarIngrediente formEditarIngrediente;
     
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
        IUsuariosBO usuariosBO = FabricaObjetoNegocio.crearUsuariosBO();
        IIngredientesBO ingredientesBO = FabricaObjetoNegocio.crearIngredientesBO();
        formIngredientesPrincipal = new IngredientesPrincipal(this, usuariosBO, ingredientesBO);
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
    
    
    
}
