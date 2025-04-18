
package itson.sistemarestaurantepresentacion.interfaces;

import javax.swing.JFrame;

public interface IMediador {
    
    public abstract void mostrarPantallaInicial();
    public abstract void mostrarPantallaInicial(JFrame frameActual);
    public abstract void mostrarInicioSesion(JFrame frameActual);
    public abstract void mostrarMenuPrincipal(JFrame frameActual);
    
    public abstract void mostrarIngredientesPrincipal(JFrame frameActual);
    public abstract void mostrarRegistroIngrediente(JFrame frameActual);
    public abstract void mostrarEditarIngrediente(JFrame frameActual, Long idIngrediente);
    public abstract void mostrarBuscadorIngredientes(IVistaReceptoraIdIngrediente vistaReceptoraIdIngrediente);
    public abstract void actualizarVentanaResultadoBusquedaIngrediente(JFrame buscadorIngredientes, Long idIngrediente);
    public abstract void cerrarBuscadorIngredientes(JFrame buscadorCerrar);
    
    public abstract void mostrarProductosPrincipal(JFrame frameActual);
    public abstract void mostrarRegistroProducto(JFrame frameActual);
    public abstract void mostrarEditarProductos(JFrame frameActual, Long idProducto);
    public abstract void mostrarBuscadorProductos(IVistaReceptoraIdProducto vistaReceptoraIdProducto);
    public abstract void actualizarVentanaResultadoBusquedaProductos(JFrame buscadorProductos, Long idProducto);
    public abstract void cerrarBuscadorProductos(JFrame buscadorCerrar);
    
    public abstract void mostrarComandasPrincipal(JFrame frameActual);
    public abstract void mostrarSeleccionMesaComanda(JFrame frameActual);
    public abstract void mostrarCreacionComanda(JFrame frameActual, Long idMesa);
    public abstract void mostrarSeleccionCantidadProducto(JFrame frameActual, Long idMesa);
    public abstract void actualizarVentanaCantidadProductoSeleccionada(JFrame seleccionProductos, Long idProducto, float cantidad);
    public abstract void cerrarSeleccionCantidadProducto(JFrame seleccionCantidadadProductoCerrar);
    
    public abstract void mostrarClientesPrincipal(JFrame framActual);
    public abstract void mostrarRegistroClienteFrecuente(JFrame frameActual);
    public abstract void mostrarEditarCliente(JFrame frameActual, Long idIngrediente);
    public abstract void mostrarBuscadorClientes(IVistaReceptoraIdCliente vistaReceptoraIdCliente);
    public abstract void actualizarVentanaResultadoBusquedaCliente(JFrame buscadorClientes, Long idCliente);
    public abstract void cerrarBuscadorClientes(JFrame buscadorCerrar);
}
