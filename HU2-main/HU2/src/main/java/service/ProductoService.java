/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mycompany.hu2.domain.Alimento;
import com.mycompany.hu2.domain.Compra;
import com.mycompany.hu2.domain.Electrodomestico;
import com.mycompany.hu2.domain.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class ProductoService {
    
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static HashMap<String, Integer> stock = new HashMap<>();
    private static ArrayList<Compra> historialCompras = new ArrayList<>();
    
    public static void agregarProducto() {
        try {
            // Seleccionar tipo de producto
            String[] tipos = {"Alimento", "Electrodoméstico"};
            String tipo = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de producto:",
                "Tipo de Producto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
            );
            
            if (tipo == null) return;
            
            // Solicitar nombre
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar duplicado
            if (stock.containsKey(nombre)) {
                JOptionPane.showMessageDialog(null, "El producto ya existe en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Solicitar precio
            String precioStr = JOptionPane.showInputDialog(null, "Ingrese el precio del producto:");
            if (precioStr == null || precioStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El precio no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double precio = Double.parseDouble(precioStr);
            
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Solicitar stock inicial
            String stockStr = JOptionPane.showInputDialog(null, "Ingrese el stock inicial:");
            if (stockStr == null || stockStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El stock no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int cantidadStock = Integer.parseInt(stockStr);
            
            if (cantidadStock < 0) {
                JOptionPane.showMessageDialog(null, "El stock no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear producto según tipo
            Producto producto;
            if (tipo.equals("Alimento")) {
                producto = new Alimento(nombre, precio);
            } else {
                producto = new Electrodomestico(nombre, precio);
            }
            
            // Agregar al inventario
            productos.add(producto);
            stock.put(nombre, cantidadStock);
            
            JOptionPane.showMessageDialog(null, 
                "Producto agregado exitosamente:\n" + nombre + " - $" + precio + " - Stock: " + cantidadStock,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void listarInventario() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario", "Inventario Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder lista = new StringBuilder("=== INVENTARIO ===\n\n");
        
        for (Producto p : productos) {
            int stockActual = stock.get(p.getNombre());
            lista.append(String.format("Producto: %s\n", p.getNombre()));
            lista.append(String.format("Precio: $%.2f\n", p.getPrecio()));
            lista.append(String.format("Stock: %d unidades\n", stockActual));
            lista.append(String.format("Descripción: %s\n", p.getDescription()));
            lista.append("─────────────────\n");
        }
        
        JOptionPane.showMessageDialog(null, lista.toString(), "Inventario", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void comprarProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Solicitar nombre del producto
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto a comprar:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            
            // Buscar producto
            Producto productoEncontrado = null;
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    productoEncontrado = p;
                    break;
                }
            }
            
            if (productoEncontrado == null) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Solicitar cantidad
            String cantidadStr = JOptionPane.showInputDialog(null, 
                "Stock disponible: " + stock.get(productoEncontrado.getNombre()) + "\nIngrese la cantidad a comprar:");
            if (cantidadStr == null || cantidadStr.trim().isEmpty()) return;
            
            int cantidad = Integer.parseInt(cantidadStr);
            
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar stock
            int stockActual = stock.get(productoEncontrado.getNombre());
            if (cantidad > stockActual) {
                JOptionPane.showMessageDialog(null, 
                    "Stock insuficiente. Disponible: " + stockActual, 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Actualizar stock
            stock.put(productoEncontrado.getNombre(), stockActual - cantidad);
            
            // Registrar compra
            Compra compra = new Compra(productoEncontrado.getNombre(), cantidad, productoEncontrado.getPrecio());
            historialCompras.add(compra);
            
            // Mostrar ticket parcial
            String ticket = "=== TICKET DE COMPRA ===\n\n" + compra.toString();
            JOptionPane.showMessageDialog(null, ticket, "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void mostrarEstadisticas() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos para mostrar estadísticas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Producto masCaro = productos.get(0);
        Producto masBarato = productos.get(0);
        double precioTotal = 0;
        
        for (Producto p : productos) {
            if (p.getPrecio() > masCaro.getPrecio()) {
                masCaro = p;
            }
            if (p.getPrecio() < masBarato.getPrecio()) {
                masBarato = p;
            }
            precioTotal += p.getPrecio();
        }
        
        double precioPromedio = precioTotal / productos.size();
        
        String estadisticas = String.format(
            "=== ESTADÍSTICAS ===\n\n" +
            "Total de productos: %d\n\n" +
            "Producto más caro:\n%s - $%.2f\n\n" +
            "Producto más barato:\n%s - $%.2f\n\n" +
            "Precio promedio: $%.2f",
            productos.size(),
            masCaro.getNombre(), masCaro.getPrecio(),
            masBarato.getNombre(), masBarato.getPrecio(),
            precioPromedio
        );
        
        JOptionPane.showMessageDialog(null, estadisticas, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void buscarProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String busqueda = JOptionPane.showInputDialog(null, "Ingrese el nombre (o parte) del producto a buscar:");
        if (busqueda == null || busqueda.trim().isEmpty()) return;
        
        StringBuilder resultados = new StringBuilder("=== RESULTADOS DE BÚSQUEDA ===\n\n");
        boolean encontrado = false;
        
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                encontrado = true;
                resultados.append(String.format("Producto: %s\n", p.getNombre()));
                resultados.append(String.format("Precio: $%.2f\n", p.getPrecio()));
                resultados.append(String.format("Stock: %d\n", stock.get(p.getNombre())));
                resultados.append(String.format("Descripción: %s\n", p.getDescription()));
                resultados.append("─────────────────\n");
            }
        }
        
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron productos con ese nombre", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, resultados.toString(), "Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void mostrarTicketFinal() {
        if (historialCompras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se realizaron compras", "Ticket Final", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder ticket = new StringBuilder("=== TICKET FINAL ===\n\n");
        double total = 0;
        
        for (Compra compra : historialCompras) {
            ticket.append(compra.toString()).append("\n");
            total += compra.getSubtotal();
        }
        
        ticket.append("\n─────────────────\n");
        ticket.append(String.format("TOTAL: $%.2f", total));
        
        JOptionPane.showMessageDialog(null, ticket.toString(), "Ticket Final", JOptionPane.INFORMATION_MESSAGE);
    }
}
