

package itson.sistemarestaurantepresentacion;

import itson.sistemarestaurantenegocio.IUsuariosBO;
import itson.sistemarestaurantenegocio.fabrica.FabricaObjetoNegocio;


public class SistemaRestaurante {

    public static void main(String[] args) {
        
        IUsuariosBO usuariosBO = FabricaObjetoNegocio.crearUsuariosBO();
        
        InciarSesion formularioInicioSesion = new InciarSesion(usuariosBO);
        
        formularioInicioSesion.setVisible(true);
        
    }
}
