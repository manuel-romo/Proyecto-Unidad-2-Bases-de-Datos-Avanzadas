
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
    
    public abstract void mostrarProductosPrincipal(JFrame frameActual);
    public abstract void mostrarRegistroProducto(JFrame frameActual);
    public abstract void mostrarEditarProductos(JFrame frameActual, Long idProducto);
    public abstract void mostrarBuscadorProductos(IVistaReceptoraIdProducto vistaReceptoraIdProducto);
    public abstract void actualizarVentanaResultadoBusquedaProductos(JFrame buscadorProductos, Long idProducto);
    public abstract void cerrarBuscador(JFrame buscadorCerrar);
    public abstract void cerrarBuscadorProductos(JFrame buscadorCerrar);
    
    public abstract void mostrarSeleccionMesaComanda(JFrame frameActual);
    public abstract void mostrarCreacionComanda(JFrame frameActual, Long idMesa);
    
}
