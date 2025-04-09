/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemarestaurantepersistencia.excepciones;

/**
 *
 * @author ramuk
 */
public class RegistroProductoBuscadoNoExisteException extends Exception{

    public RegistroProductoBuscadoNoExisteException() {
    }

    public RegistroProductoBuscadoNoExisteException(String message) {
        super(message);
    }
    
}
