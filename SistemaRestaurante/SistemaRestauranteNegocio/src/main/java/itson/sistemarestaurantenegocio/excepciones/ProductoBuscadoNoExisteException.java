/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantenegocio.excepciones;

/**
 *
 * @author ramuk
 */
public class ProductoBuscadoNoExisteException extends Exception{

    public ProductoBuscadoNoExisteException() {
    }

    public ProductoBuscadoNoExisteException(String message) {
        super(message);
    }
    
}
