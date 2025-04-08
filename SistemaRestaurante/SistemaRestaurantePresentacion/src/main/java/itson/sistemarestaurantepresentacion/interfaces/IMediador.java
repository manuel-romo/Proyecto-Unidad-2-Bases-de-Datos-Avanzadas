
package itson.sistemarestaurantepresentacion.interfaces;

import javax.swing.JFrame;

public interface IMediador {
    
    public void mostrarPantallaInicial();
    public void mostrarPantallaInicial(JFrame frameActual);
    public void mostrarInicioSesion(JFrame frameActual);
    public void mostrarMenuPrincipal(JFrame frameActual);
    public void mostrarIngredientesPrincipal(JFrame frameActual);
    public void mostrarRegistroIngrediente(JFrame frameActual);
    public void mostrarEditarIngrediente(JFrame frameActual, Long idIngrediente);
    
}
