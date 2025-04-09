/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.excepciones;

/**
 *
 * @author ramuk
 */
public class ProductoNoExisteException extends Exception{

    public ProductoNoExisteException() {
    }

    public ProductoNoExisteException(String message) {
        super(message);
    }
    
}
