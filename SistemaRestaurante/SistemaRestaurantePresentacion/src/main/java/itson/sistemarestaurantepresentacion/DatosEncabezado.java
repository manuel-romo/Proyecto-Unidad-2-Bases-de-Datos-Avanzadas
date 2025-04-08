
package itson.sistemarestaurantepresentacion;


public class DatosEncabezado {
 
    private static DatosEncabezado instance;
    private String nombresUsuario;
    private String apellidoPaternoUsuario;
    private String nombreRestaurante;
    private String direccionLogotipoRestaurante;
    private String direccionImagenUsuario;
    
    public static DatosEncabezado getInstance(){
        if(instance == null){
            instance = new DatosEncabezado();
        }
        
        return instance;
    }
    
    public void setValores(
            String nombresCliente, 
            String apellidoPaternoCliente, 
            String direccionLogotipoRestaurante,
            String direccionImangenUsuario){
        
        this.nombresUsuario = nombresCliente;
        this.apellidoPaternoUsuario = apellidoPaternoCliente;
        this.direccionLogotipoRestaurante = direccionLogotipoRestaurante;
        this.direccionImagenUsuario = direccionImangenUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidoPaternoUsuario() {
        return apellidoPaternoUsuario;
    }

    public void setApellidoPaternoUsuario(String apellidoPaternoUsuario) {
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getDireccionLogotipoRestaurante() {
        return direccionLogotipoRestaurante;
    }

    public void setDireccionLogotipoRestaurante(String direccionLogotipoRestaurante) {
        this.direccionLogotipoRestaurante = direccionLogotipoRestaurante;
    }

    public String getDireccionImagenUsuario() {
        return direccionImagenUsuario;
    }

    public void setDireccionImagenUsuario(String direccionImagenUsuario) {
        this.direccionImagenUsuario = direccionImagenUsuario;
    }

    
    
    
    
    
}
