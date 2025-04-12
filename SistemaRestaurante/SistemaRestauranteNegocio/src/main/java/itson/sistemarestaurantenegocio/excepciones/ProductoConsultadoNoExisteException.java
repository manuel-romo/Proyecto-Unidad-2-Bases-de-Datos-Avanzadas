/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.excepciones;

/**
 *
 * @author ramuk
 */
public class ProductoConsultadoNoExisteException extends Exception{

    public ProductoConsultadoNoExisteException() {
    }

    public ProductoConsultadoNoExisteException(String message) {
        super(message);
    }
    
}
