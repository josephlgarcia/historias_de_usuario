/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hu4.ui;

import com.mycompany.hu4.domain.Product;
import com.mycompany.hu4.exception.CampoVacioException;
import com.mycompany.hu4.exception.ConexionException;
import com.mycompany.hu4.exception.DatoInvalidoException;
import com.mycompany.hu4.exception.DuplicadoException;
import com.mycompany.hu4.exception.EntidadNoEncontradaException;
import com.mycompany.hu4.exception.PersistenciaException;
import com.mycompany.hu4.exception.PrecioInvalidoException;
import com.mycompany.hu4.exception.StockInvalidoException;
import com.mycompany.hu4.repository.impl.ProductRepositoryImpl;
import com.mycompany.hu4.service.impl.ProductServiceImpl;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author xxx
 */
public class InitUI {

    // Crear instancias una sola vez para mantener los datos
    private static final ProductRepositoryImpl repo = new ProductRepositoryImpl();
    private static final ProductServiceImpl productService = new ProductServiceImpl(repo);

    // Contadores de operaciones
    private static int contadorAltas = 0;
    private static int contadorBajas = 0;
    private static int contadorActualizaciones = 0;

    public static void Initui() {
        boolean continuar = true;

        while (continuar) {
            String[] opciones = {
                "1. Agregar producto",
                "2. Listar inventario",
                "3. Actualizar precio",
                "4. Actualizar stock",
                "5. Eliminar producto",
                "6. Buscar por nombre",
                "7. Salir"
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
                mostrarResumenOperaciones();
                continue;
            }

            switch (seleccion.charAt(0)) {
                case '1':
                    agregarProducto();
                    break;
                case '2':
                    listarInventario();
                    break;
                case '3':
                    actualizarPrecio();
                    break;
                case '4':
                    actualizarStock();
                    break;
                case '5':
                    eliminarProducto();
                    break;
                case '6':
                    buscarPorNombre();
                    break;
                case '7':
                    continuar = false;
                    mostrarResumenOperaciones();
                    break;
            }
        }
    }

    private static void agregarProducto() {
        try {
            String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
            if (name == null || name.isBlank()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String precioStr = JOptionPane.showInputDialog(null, "Ingrese el precio del producto:");
            if (precioStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Double price = Double.parseDouble(precioStr);

            String stockStr = JOptionPane.showInputDialog(null, "Ingrese el stock inicial:");
            if (stockStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Integer stock = Integer.parseInt(stockStr);

            Product p = new Product(name, price, stock);
            Product productoGuardado = productService.registrar(p);

            contadorAltas++;

            JOptionPane.showMessageDialog(null,
                    "Producto agregado exitosamente\n"
                    + "ID: " + productoGuardado.getId() + "\n"
                    + "Nombre: " + productoGuardado.getName() + "\n"
                    + "Precio: $" + productoGuardado.getPrice() + "\n"
                    + "Stock: " + productoGuardado.getStock(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese valores numéricos válidos",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (CampoVacioException | PrecioInvalidoException | StockInvalidoException e) {
            // Excepciones de validación de datos
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
        } catch (DuplicadoException e) {
            // Producto duplicado
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Producto Duplicado",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ConexionException e) {
            // Error de conexión a la base de datos
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos. Verifique su conexión.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException e) {
            // Otros errores de persistencia
            JOptionPane.showMessageDialog(null,
                    "Error al guardar el producto: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarInventario() {
        try {
            List<Product> productos = productService.listar();

            if (productos.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No hay productos en el inventario",
                        "Inventario Vacío",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-5s %-30s %-15s %-10s\n", "ID", "Nombre", "Precio", "Stock"));
            sb.append("=".repeat(65)).append("\n");

            for (Product p : productos) {
                sb.append(String.format("%-5d %-30s $%-14.2f %-10d\n",
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock()));
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            textArea.setFont(new java.awt.Font("Monospaced", 0, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(600, 400));

            JOptionPane.showMessageDialog(null, scrollPane,
                    "Inventario de Productos",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (ConexionException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos. Verifique su conexión.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al listar productos: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void actualizarPrecio() {
        try {
            String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del producto:");
            if (idStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Long id = Long.parseLong(idStr);

            Product producto = productService.consultarPorId(id);

            String nuevoPrecioStr = JOptionPane.showInputDialog(null,
                    "Producto: " + producto.getName() + "\n"
                    + "Precio actual: $" + producto.getPrice() + "\n\n"
                    + "Ingrese el nuevo precio:");

            if (nuevoPrecioStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);

            producto.setPrice(nuevoPrecio);
            productService.actualizar(producto);

            contadorActualizaciones++;

            JOptionPane.showMessageDialog(null,
                    "Precio actualizado exitosamente\n\n"
                    + "Producto: " + producto.getName() + "\n"
                    + "Nuevo precio: $" + producto.getPrice(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese valores numéricos válidos",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DatoInvalidoException e) {
            // ID inválido
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
        } catch (EntidadNoEncontradaException e) {
            // Producto no encontrado
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Producto No Encontrado",
                    JOptionPane.WARNING_MESSAGE);
        } // Precio inválido
        catch (ConexionException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos. Verifique su conexión.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar el producto: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void actualizarStock() {
        try {
            String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del producto:");
            if (idStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Long id = Long.parseLong(idStr);

            Product producto = productService.consultarPorId(id);

            String nuevoStockStr = JOptionPane.showInputDialog(null,
                    "Producto: " + producto.getName() + "\n"
                    + "Stock actual: " + producto.getStock() + "\n\n"
                    + "Ingrese el nuevo stock:");

            if (nuevoStockStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Integer nuevoStock = Integer.parseInt(nuevoStockStr);

            producto.setStock(nuevoStock);
            productService.actualizar(producto);

            contadorActualizaciones++;

            JOptionPane.showMessageDialog(null,
                    "Stock actualizado exitosamente\n\n"
                    + "Producto: " + producto.getName() + "\n"
                    + "Nuevo stock: " + producto.getStock(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese valores numéricos válidos",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DatoInvalidoException e) {
            // ID inválido
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
        } catch (EntidadNoEncontradaException e) {
            // Producto no encontrado
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Producto No Encontrado",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ConexionException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos. Verifique su conexión.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar el producto: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void eliminarProducto() {
        try {
            String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del producto a eliminar:");
            if (idStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Long id = Long.parseLong(idStr);

            Product producto = productService.consultarPorId(id);

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar el siguiente producto?\n\n"
                    + "ID: " + producto.getId() + "\n"
                    + "Nombre: " + producto.getName() + "\n"
                    + "Precio: $" + producto.getPrice() + "\n"
                    + "Stock: " + producto.getStock(),
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                productService.eliminar(id);
                contadorBajas++;

                JOptionPane.showMessageDialog(null,
                        "Producto eliminado exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Ingrese un ID válido",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DatoInvalidoException e) {
            // ID inválido
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
        } catch (EntidadNoEncontradaException e) {
            // Producto no encontrado
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Producto No Encontrado",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ConexionException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos. Verifique su conexión.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar el producto: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void buscarPorNombre() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto a buscar:");
            if (nombre == null || nombre.isBlank()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            List<Product> resultados = productService.buscarPorNombre(nombre);

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No se encontraron productos con el nombre: " + nombre,
                        "Sin Resultados",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Resultados de búsqueda para: \"").append(nombre).append("\"\n\n");
            sb.append(String.format("%-5s %-30s %-15s %-10s\n", "ID", "Nombre", "Precio", "Stock"));
            sb.append("=".repeat(65)).append("\n");

            for (Product p : resultados) {
                sb.append(String.format("%-5d %-30s $%-14.2f %-10d\n",
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock()));
            }

            sb.append("\nTotal encontrados: ").append(resultados.size());

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            textArea.setFont(new java.awt.Font("Monospaced", 0, 12));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(600, 400));

            JOptionPane.showMessageDialog(null, scrollPane,
                    "Resultados de Búsqueda",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (CampoVacioException e) {
            // Nombre vacío
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ConexionException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos. Verifique su conexión.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al buscar productos: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarResumenOperaciones() {
        String mensaje = "=== RESUMEN DE OPERACIONES ===\n\n"
                + "Altas (productos agregados): " + contadorAltas + "\n"
                + "Bajas (productos eliminados): " + contadorBajas + "\n"
                + "Actualizaciones: " + contadorActualizaciones + "\n\n"
                + "¡Hasta luego!";

        JOptionPane.showMessageDialog(null,
                mensaje,
                "Resumen",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
