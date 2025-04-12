
package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantenegocio.interfaces.IIngredientesBO;
import itson.sistemarestaurantenegocio.interfaces.IUsuariosBO;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;
import itson.sistemarestaurantenegocio.interfaces.IComandasBO;
import itson.sistemarestaurantenegocio.interfaces.IMesasBO;
import itson.sistemarestaurantenegocio.interfaces.IProductosBO;
import itson.sistemarestaurantepresentacion.interfaces.IMediador;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdIngrediente;
import itson.sistemarestaurantepresentacion.interfaces.IVistaReceptoraIdProducto;
import javax.swing.JFrame;


public class Control implements IMediador{

    private IVistaReceptoraIdIngrediente formReceptorRespuestaBusquedaIngrediente;
    private IVistaReceptoraIdProducto formReceptorRespuestaBusquedaProducto;
    
    
    private PantallaInicial pantallaInicial;
    private InicioSesion formInicioSesion;
    private MenuPrincipal formMenuPrincipal;
    
    private IngredientesPrincipal formIngredientesPrincipal;
    private RegistroIngrediente formRegistroIngrediente;
    private EdicionIngrediente formEdicionIngrediente;
    private BuscadorIngredientes formBuscadorIngredientes;
    
    private ProductoPrincipal formProductosPrincipal;
    private RegistroProducto formRegistroProducto;
    private EdicionProducto formEdicionProducto;
    private BuscadorProductos formBuscadorProductos;
    
    private ComandasPrincipal formComadasPrincipal;
    private SeleccionMesaComanda formSeleccionMesaComanda;
    private InformacionComanda formInformacionComanda;
    private SeleccionCantidadProductoComanda formSeleccionCantidadProductoComanda;
     
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
        formInicioSesion = new InicioSesion(this, usuariosBO);
        formInicioSesion.setVisible(true);
    }
    
    @Override
    public void mostrarMenuPrincipal(JFrame frameActual) { 
        frameActual.dispose();
        IUsuariosBO usuariosBO = FabricaObjetoNegocio.crearUsuariosBO();
        IMesasBO mesasBO = FabricaObjetoNegocio.crearMesasBO();
        formMenuPrincipal = new MenuPrincipal(this, usuariosBO, mesasBO);

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
        formEdicionIngrediente = new EdicionIngrediente(this, ingredientesBO, idIngrediente);
        formEdicionIngrediente.setVisible(true);
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
    public void cerrarBuscadorIngredientes(JFrame buscadorCerrar){   
        formReceptorRespuestaBusquedaIngrediente.habilitar(true);
        buscadorCerrar.dispose();
    }

    @Override
    public void mostrarProductosPrincipal(JFrame frameActual) {
        frameActual.dispose();
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        formProductosPrincipal = new ProductoPrincipal(this, productosBO);
        formProductosPrincipal.setVisible(true);
    }

    @Override
    public void mostrarRegistroProducto(JFrame frameActual) {
        frameActual.dispose();
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        formRegistroProducto = new RegistroProducto(this, productosBO);
        formRegistroProducto.setVisible(true);
    }

    @Override
    public void mostrarEditarProductos(JFrame frameActual, Long idProducto) {
        frameActual.dispose();
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        formEdicionProducto = new EdicionProducto(this, productosBO, idProducto);
        formEdicionProducto.setVisible(true);
    }

    @Override
    public void mostrarBuscadorProductos(IVistaReceptoraIdProducto vistaReceptoraIdProducto) {
        vistaReceptoraIdProducto.habilitar(false);
        formReceptorRespuestaBusquedaProducto = (IVistaReceptoraIdProducto)vistaReceptoraIdProducto;
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        formBuscadorProductos = new BuscadorProductos(this, productosBO);
        formBuscadorProductos.setVisible(true);
    }

    @Override
    public void actualizarVentanaResultadoBusquedaProductos(JFrame buscadorProductos, Long idProducto) {
        formReceptorRespuestaBusquedaProducto.setIdProducto(idProducto);
        formReceptorRespuestaBusquedaProducto.habilitar(true);
        buscadorProductos.dispose();
    }
    
    @Override
    public void cerrarBuscadorProductos(JFrame buscadorCerrar) {
        formReceptorRespuestaBusquedaProducto.habilitar(true);
        buscadorCerrar.dispose();
    }
    
    
    @Override
    public void mostrarComandasPrincipal(JFrame frameActual){
        IComandasBO comandasBO = FabricaObjetoNegocio.crearComandasBO();
        formComadasPrincipal = new ComandasPrincipal(this, comandasBO);
        formComadasPrincipal.setVisible(true);
        frameActual.dispose();
    }

    @Override
    public void mostrarSeleccionMesaComanda(JFrame frameActual) {
        IMesasBO mesasBO = FabricaObjetoNegocio.crearMesasBO();
        formSeleccionMesaComanda = new SeleccionMesaComanda(this, mesasBO);
        formSeleccionMesaComanda.setVisible(true);
        frameActual.dispose();
    }

    @Override
    public void mostrarCreacionComanda(JFrame frameActual, Long idMesa) {
        
        IComandasBO comandasBO = FabricaObjetoNegocio.crearComandasBO();
        IMesasBO mesasBO = FabricaObjetoNegocio.crearMesasBO();
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        
        formInformacionComanda = new InformacionComanda(this, mesasBO, comandasBO, productosBO, idMesa);
        formInformacionComanda.setVisible(true);
        frameActual.dispose();
        
    }

    @Override
    public void mostrarSeleccionCantidadProducto(JFrame frameActual, Long idProducto) {
        
        IProductosBO productosBO = FabricaObjetoNegocio.crearProductosBO();
        frameActual.setEnabled(false);
        formSeleccionCantidadProductoComanda = new SeleccionCantidadProductoComanda(this, productosBO, idProducto);
        formSeleccionCantidadProductoComanda.setVisible(true);
        
    }
    
    @Override
    public void actualizarVentanaCantidadProductoSeleccionada(JFrame seleccionProductos, Long idProducto, float cantidad) {
        formInformacionComanda.setCantidadProducto(idProducto, cantidad);
        seleccionProductos.dispose();
        formInformacionComanda.setVisible(true);
    }

    @Override
    public void cerrarSeleccionCantidadProducto(JFrame seleccionCantidadadProductoCerrar) {
        seleccionCantidadadProductoCerrar.dispose();
        formInformacionComanda.setVisible(true);
    }
    
    
    
    
}
