# MiniTienda - Proyecto en Java

Este es un pequeño sistema de gestión para una tienda, desarrollado en Java, que permite realizar las siguientes operaciones a través de una interfaz gráfica de usuario (GUI) utilizando `JOptionPane`:

1. Agregar productos al inventario.
2. Listar el inventario completo de productos.
3. Realizar compras de productos.
4. Mostrar estadísticas (por implementar: más barato y más caro).
5. Buscar productos por nombre.
6. Salir del sistema mostrando el ticket final.

## Requisitos

* **Java**: Este proyecto fue desarrollado utilizando Java 8 o superior.
* **IDE recomendado**: IntelliJ IDEA, Eclipse o cualquier editor de texto que soporte Java.

## Estructura del Proyecto

### Clases y Funciones

#### 1. `MiniTienda.java`

Esta es la clase principal que contiene la lógica del sistema de tienda. Está compuesta por las siguientes funcionalidades:

* **Agregar Producto**: Permite al usuario agregar productos al inventario con nombre, precio y cantidad.
* **Listar Inventario**: Muestra un listado de todos los productos almacenados con su nombre, precio y cantidad.
* **Comprar Producto**: Permite al usuario comprar un producto en función de la cantidad deseada, siempre que haya suficiente stock.
* **Buscar Producto**: Permite buscar un producto por su nombre y mostrar su precio y cantidad disponible.
* **Salir**: Muestra el ticket final con el total de las compras realizadas.

#### Variables Principales:

* **`nombres`**: Una lista (`ArrayList`) que almacena los nombres de los productos.
* **`precios`**: Un arreglo de `Double` que almacena los precios de los productos.
* **`productos`**: Un `HashMap` que almacena el nombre del producto y su cantidad disponible.
* **`ticket`**: Una variable que acumula el total de las compras realizadas.
* **`contador`**: Un contador para mantener el registro de productos agregados.

#### Funcionalidades Detalladas:

1. **Agregar Producto** (`opcion = "1"`):
   El usuario puede ingresar el nombre, el precio y la cantidad de un nuevo producto para agregarlo al inventario.

2. **Listar Inventario** (`opcion = "2"`):
   El sistema muestra todos los productos almacenados en el inventario con su nombre, precio y cantidad disponible.

3. **Comprar Producto** (`opcion = "3"`):
   Permite al usuario comprar un producto, siempre que haya suficiente stock. Actualiza el inventario y suma al ticket final el costo de la compra.

4. **Mostrar Estadísticas** (`opcion = "4"`):
   Esta opción está preparada para implementar futuras mejoras, como mostrar el producto más barato y el más caro. Actualmente está vacía.

5. **Buscar Producto por Nombre** (`opcion = "5"`):
   Permite al usuario buscar un producto por nombre y visualizar sus detalles: nombre, precio y cantidad disponible.

6. **Salir y Mostrar Ticket** (`opcion = "6"`):
   Al salir del sistema, se muestra el total del ticket, es decir, el monto total de las compras realizadas.

## Uso

1. **Ejecuta el proyecto** desde tu IDE o consola.
2. **Selecciona una opción** del menú mostrado mediante `JOptionPane`.
3. **Sigue las instrucciones** para agregar productos, listar inventarios, realizar compras, buscar productos o salir.
4. El sistema mantiene un registro de las compras realizadas y muestra el total acumulado al final.

## Ejemplo de Interacciones

```text
Ingrese la opcion:
1. Agregar producto
2. Listar inventario
3. Comprar producto
4. Mostrar estadísticas (más barato y más caro)
5. Buscar producto por nombre
6. Salir con ticket final
```

### Caso de Agregar Producto

```text
Ingrese el nombre del producto: Zapatos
Ingrese el precio: 500.0
Ingrese la cantidad: 10
```

### Caso de Comprar Producto

```text
Ingrese el nombre del producto a comprar: Zapatos
Ingrese la cantidad a comprar: 2
Compra realizada. Nuevo stock: 8
```

### Caso de Listar Inventario

```text
Inventario de productos:
Nombre: Zapatos | Precio: 500.0 | Cantidad: 8
```

## Mejoras Futuras

* Implementar la opción de **Mostrar estadísticas** para obtener el producto más barato y más caro.
* Añadir soporte para persistencia de datos, como guardar el inventario en un archivo o base de datos.
* Mejorar la interfaz de usuario con gráficos o tablas.
