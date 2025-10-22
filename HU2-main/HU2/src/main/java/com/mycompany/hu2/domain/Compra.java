/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu2.domain;

/**
 *
 * @author Coder
 */
public class Compra {
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    
    public Compra(String nombreProducto, int cantidad, double precioUnitario) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
    
    @Override
    public String toString() {
        return String.format("%s x%d - $%.2f c/u = $%.2f", 
            nombreProducto, cantidad, precioUnitario, getSubtotal());
    }
}
