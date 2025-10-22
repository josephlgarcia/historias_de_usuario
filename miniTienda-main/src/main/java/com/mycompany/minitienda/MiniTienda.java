/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.minitienda;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class MiniTienda {

    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        Double[] precios = new Double[2000];
        HashMap<String, Integer> productos = new HashMap<>();
        double ticket = 0;
        int contador = 0;

        String opcion;
        do {
            opcion = JOptionPane.showInputDialog("Ingrese la opcion:\n"
                    + "1. Agregar producto\n"
                    + "2. Listar inventario\n"
                    + "3. Comprar producto\n"
                    + "4. Mostrar estadísticas (más barato y más caro)\n"
                    + "5. Buscar producto por nombre\n"
                    + "6. Salir con ticket final");

            switch (opcion) {
                case "1":
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto: ");
                    Double precio = Double.valueOf(JOptionPane.showInputDialog("Ingrese el precio: "));
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad: "));

                    nombres.add(nombre);
                    precios[contador] = precio;
                    productos.put(nombre, cantidad);

                    contador++;

                    break;
                case "2":
                    StringBuilder inventario = new StringBuilder("Inventario de productos:\n");

                    for (int i = 0; i < nombres.size(); i++) {
                        inventario.append("Nombre: ").append(nombres.get(i))
                                .append(" | Precio: ").append(precios[i])
                                .append(" | Cantidad: ").append(productos.get(nombres.get(i)))
                                .append("\n");
                    }

                    JOptionPane.showMessageDialog(null, inventario.toString());
                    break;
                case "3":
                    String nombreComprar = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar: ");
                    int cantidadComprar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a comprar; "));

                    int productoEncontrado = nombres.indexOf(nombreComprar);

                    if (productoEncontrado != -1) {
                        String nombreEncontrado = nombres.get(productoEncontrado);
                        int stockActual = productos.get(nombreEncontrado);

                        if (cantidadComprar <= stockActual) {
                            productos.put(nombreEncontrado, stockActual - cantidadComprar);
                            JOptionPane.showMessageDialog(null, "Compra realizada. Nuevo stock: " + productos.get(nombreEncontrado));
                            ticket += cantidadComprar * precios[productoEncontrado];
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay suficiente stock para comprar " + cantidadComprar + " unidades.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el producto");
                    }
                    
                    break;

                case "4":

                          break;
                case "5":
                    String nombreBuscar = JOptionPane.showInputDialog("Ingrese el nombre del producto a buscar: ");
                    productoEncontrado = nombres.indexOf(nombreBuscar);
                    
                    if (productoEncontrado != -1) {
                        JOptionPane.showMessageDialog(null, "Nombre: " + nombres.get(productoEncontrado)
                                + "\nPrecio: " + precios[productoEncontrado]
                                + " \nCantidad: " + productos.get(nombres.get(productoEncontrado)));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el producto");
                    }
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, ticket);
                    break;
                default:
                    throw new AssertionError();
            }
        
        } while (!opcion.equals("6"));
    }
}
