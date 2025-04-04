package itson.sistemarestaurantepersistencia;

import itson.sistemarestaurantedominio.Cliente;
import itson.sistemarestaurantedominio.dtos.NuevoClienteDTO;
import java.util.List;


public interface IClientesDAO {
    
    public abstract Cliente registrar(NuevoClienteDTO nuevoClienteDTO);   
    public abstract List<Cliente> consultar(String filtroBusqueda);
    
}
