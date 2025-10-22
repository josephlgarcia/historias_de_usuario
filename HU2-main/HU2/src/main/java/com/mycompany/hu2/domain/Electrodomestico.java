/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu2.domain;

/**
 *
 * @author Coder
 */
public class Electrodomestico extends Producto{

    public Electrodomestico(String nombre, double precio) {
        super(nombre, precio);
    }
    
    @Override
    public String getDescription() {
        return "Electrodomèstico: " + getNombre() + 
               " | Precio: $" + String.format("%.2f", getPrecio());
    }

    
   
    
}
