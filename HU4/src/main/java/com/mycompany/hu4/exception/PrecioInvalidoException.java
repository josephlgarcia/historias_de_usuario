/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.exception;

/**
 *
 * @author xxx
 */
public class PrecioInvalidoException extends DatoInvalidoException {
    
    public PrecioInvalidoException() {
        super("El precio debe ser mayor a cero");
    }
    
    public PrecioInvalidoException(String mensaje) {
        super(mensaje);
    }
    
}
