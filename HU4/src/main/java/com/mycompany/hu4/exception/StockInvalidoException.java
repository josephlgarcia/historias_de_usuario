/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.exception;

/**
 *
 * @author xxx
 */
public class StockInvalidoException extends DatoInvalidoException {
    
    public StockInvalidoException() {
        super("El stock no puede ser negativo");
    }
    
    public StockInvalidoException(String mensaje) {
        super(mensaje);
    }

    
}
