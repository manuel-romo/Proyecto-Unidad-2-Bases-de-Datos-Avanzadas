
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
    public abstract void mostrarBuscadorIngredientes(JFrame framePadre);
    
}
