/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.exception;

/**
 *
 * @author xxx
 */
public class StockInsuficienteException extends AppException {
    
    public StockInsuficienteException(String nombreProducto, int disponible, int solicitado) {
        super(String.format("Stock insuficiente para '%s'. Disponible: %d, Solicitado: %d", 
              nombreProducto, disponible, solicitado));
    }
    
}
