

package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantepresentacion.interfaces.IMediador;


public class SistemaRestaurante {

    public static void main(String[] args) {
 
        // Creación de control (mediador)
        IMediador control = new Control();

        control.mostrarPantallaInicial();
 
    }
}
