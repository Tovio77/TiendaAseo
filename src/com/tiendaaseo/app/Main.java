package com.tiendaaseo.app;

import com.tiendaaseo.dao.ProductoDAO;
import com.tiendaaseo.modelo.Producto;

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();
        int opcionSeleccionada;

        do {
            System.out.println("\n===== MÓDULO DE PRODUCTOS - TIENDA DE ASEO =====");
            System.out.println("1. Insertar producto");
            System.out.println("2. Consultar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcionSeleccionada = Integer.parseInt(teclado.nextLine());

            switch (opcionSeleccionada) {
                case 1:
                    insertarProducto(teclado, productoDAO);
                    break;
                case 2:
                    listarProductos(productoDAO);
                    break;
                case 3:
                    actualizarProducto(teclado, productoDAO);
                    break;
                case 4:
                    eliminarProducto(teclado, productoDAO);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionSeleccionada != 5);

        teclado.close();
    }

    private static void insertarProducto(Scanner teclado, ProductoDAO productoDAO) {
        System.out.print("Nombre del producto: ");
        String nombreProducto = teclado.nextLine();
        System.out.print("Descripción: ");
        String descripcion = teclado.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(teclado.nextLine());
        System.out.print("Cantidad en stock: ");
        int stock = Integer.parseInt(teclado.nextLine());
        System.out.print("ID de categoría (ej: 1 a 30): ");
        int idCategoria = Integer.parseInt(teclado.nextLine());

        Producto nuevoProducto = new Producto(nombreProducto, descripcion, precio, stock, idCategoria);
        boolean exito = productoDAO.insertarProducto(nuevoProducto);

        System.out.println(exito ? "Producto insertado correctamente." : "No se pudo insertar el producto.");
    }

    private static void listarProductos(ProductoDAO productoDAO) {
        List<Producto> listaProductos = productoDAO.consultarProductos();

        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- Listado de productos ---");
            for (Producto producto : listaProductos) {
                System.out.println(producto);
            }
        }
    }

    private static void actualizarProducto(Scanner teclado, ProductoDAO productoDAO) {
        System.out.print("ID del producto a actualizar: ");
        int idProducto = Integer.parseInt(teclado.nextLine());

        Producto productoExistente = productoDAO.consultarProductoPorId(idProducto);
        if (productoExistente == null) {
            System.out.println("No existe un producto con ese ID.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombreProducto = teclado.nextLine();
        System.out.print("Nueva descripción: ");
        String descripcion = teclado.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(teclado.nextLine());
        System.out.print("Nueva cantidad en stock: ");
        int stock = Integer.parseInt(teclado.nextLine());
        System.out.print("Nuevo ID de categoría: ");
        int idCategoria = Integer.parseInt(teclado.nextLine());

        Producto productoActualizado = new Producto(idProducto, nombreProducto, descripcion, precio, stock, idCategoria);
        boolean exito = productoDAO.actualizarProducto(productoActualizado);

        System.out.println(exito ? "Producto actualizado correctamente." : "No se pudo actualizar el producto.");
    }

    private static void eliminarProducto(Scanner teclado, ProductoDAO productoDAO) {
        System.out.print("ID del producto a eliminar: ");
        int idProducto = Integer.parseInt(teclado.nextLine());

        boolean exito = productoDAO.eliminarProducto(idProducto);
        System.out.println(exito ? "Producto eliminado correctamente." : "No se pudo eliminar el producto.");
    }
}