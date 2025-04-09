/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.excepciones;

/**
 *
 * @author ramuk
 */
public class RegistroProductoNoExisteException extends Exception{

    public RegistroProductoNoExisteException() {
    }

    public RegistroProductoNoExisteException(String message) {
        super(message);
    }
    
}
