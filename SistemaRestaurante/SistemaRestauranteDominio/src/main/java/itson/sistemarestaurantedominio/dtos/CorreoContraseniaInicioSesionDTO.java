package itson.sistemarestaurantedominio.dtos;

public class CorreoContraseniaInicioSesionDTO {
    private String correoElectronico;
    private char[] contrasenia;

    public CorreoContraseniaInicioSesionDTO(String correoElectronico, char[] contrasenia) {
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public char[] getContrasenia() {
        return contrasenia;
    }
    
    
}
