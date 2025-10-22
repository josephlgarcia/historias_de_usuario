# Sistema de Inventario - HU2

Sistema de gestión de inventario desarrollado en Java con interfaz gráfica usando Swing. Permite administrar productos, realizar compras y generar estadísticas.

## Descripción

Este proyecto es un sistema de inventario que facilita la gestión de productos mediante una interfaz intuitiva de ventanas emergentes (JOptionPane). Ideal para pequeños negocios o como proyecto educativo.

## Características

- **Gestión de productos**: Agregar nuevos productos al inventario
- **Visualización**: Listar todos los productos disponibles
- **Compras**: Sistema de compra con control de stock
- **Estadísticas**: Análisis de datos del inventario
- **Búsqueda**: Localizar productos específicos
- **Ticket final**: Resumen de transacciones al salir

## Requisitos

- Java JDK 8 o superior
- NetBeans IDE (recomendado) o cualquier IDE compatible con Maven
- Sistema operativo: Windows, Linux o macOS

## Estructura del Proyecto

```
HU2/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── mycompany/
│                   └── hu2/
│                       ├── HU2.java (Clase principal)
│                       └── service/
│                           └── ProductoService.java
├── pom.xml
└── README.md
```

## Instalación

1. **Clonar o descargar el proyecto**
   ```bash
   git clone <url-del-repositorio>
   cd HU2
   ```

2. **Abrir en NetBeans**
   - Abrir NetBeans IDE
   - File → Open Project
   - Seleccionar la carpeta del proyecto

3. **Compilar el proyecto**
   - Click derecho en el proyecto → Build


### Ejecutar la aplicación

1. En NetBeans: Click derecho en el proyecto → Run
2. Desde línea de comandos:
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="com.mycompany.hu2.HU2"
   ```

### Menú Principal

Al iniciar, aparecerá un menú con las siguientes opciones:

1. **Agregar producto**: Registrar nuevos productos en el inventario
2. **Listar inventario**: Visualizar todos los productos disponibles
3. **Comprar producto**: Realizar transacciones de compra
4. **Estadísticas**: Ver métricas y análisis del inventario
5. **Buscar producto**: Encontrar productos específicos
6. **Salir**: Cerrar la aplicación y ver ticket final

## Arquitectura

El proyecto sigue una arquitectura en capas:

- **Capa de presentación**: `HU2.java` - Interfaz de usuario con JOptionPane
- **Capa de servicio**: `ProductoService.java` - Lógica de negocio
- **Patrón utilizado**: Separación de responsabilidades

## Ejemplo de uso

```java
// La aplicación se ejecuta en un bucle hasta que el usuario seleccione "Salir"
// Cada opción llama a un método específico del ProductoService:

ProductoService.agregarProducto();      // Opción 1
ProductoService.listarInventario();     // Opción 2
ProductoService.comprarProducto();      // Opción 3
ProductoService.mostrarEstadisticas();  // Opción 4
ProductoService.buscarProducto();       // Opción 5
ProductoService.mostrarTicketFinal();   // Opción 6
```

