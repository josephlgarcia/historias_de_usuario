/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hu2;


import javax.swing.JOptionPane;
import service.ProductoService;

/**
 *
 * @author Coder
 */
public class HU2 {
    
    public static void main(String[] args) {
        boolean continuar = true;
        
        while (continuar) {
            String[] opciones = {
                "1. Agregar producto",
                "2. Listar inventario",
                "3. Comprar producto",
                "4. Estadísticas",
                "5. Buscar producto",
                "6. Salir"
            };
            
            String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción:",
                "Sistema de Inventario",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            if (seleccion == null) {
                continuar = false;
                continue;
            }
            
            switch (seleccion.charAt(0)) {
                case '1':
                    ProductoService.agregarProducto();
                    break;
                case '2':
                    ProductoService.listarInventario();
                    break;
                case '3':
                    ProductoService.comprarProducto();
                    break;
                case '4':
                    ProductoService.mostrarEstadisticas();
                    break;
                case '5':
                    ProductoService.buscarProducto();
                    break;
                case '6':
                    continuar = false;
                    ProductoService.mostrarTicketFinal();
                    break;
            }
        }
        
    }
    
    
}
