package com.tiendaaseo.dao;

import com.tiendaaseo.conexion.Conexion;
import com.tiendaaseo.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {

    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre_producto, descripcion, precio, stock, id_categoria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexionBD = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexionBD.prepareStatement(sql)) {

            sentencia.setString(1, producto.getNombreProducto());
            sentencia.setString(2, producto.getDescripcion());
            sentencia.setDouble(3, producto.getPrecio());
            sentencia.setInt(4, producto.getStock());
            sentencia.setInt(5, producto.getIdCategoria());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException excepcion) {
            System.out.println("Error al insertar el producto: " + excepcion.getMessage());
            return false;
        }
    }

    public List<Producto> consultarProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conexionBD = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexionBD.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Producto producto = new Producto(
                        resultado.getInt("id_producto"),
                        resultado.getString("nombre_producto"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock"),
                        resultado.getInt("id_categoria")
                );
                listaProductos.add(producto);
            }

        } catch (SQLException excepcion) {
            System.out.println("Error al consultar los productos: " + excepcion.getMessage());
        }

        return listaProductos;
    }

   
    public Producto consultarProductoPorId(int idProducto) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        Producto producto = null;

        try (Connection conexionBD = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexionBD.prepareStatement(sql)) {

            sentencia.setInt(1, idProducto);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                producto = new Producto(
                        resultado.getInt("id_producto"),
                        resultado.getString("nombre_producto"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock"),
                        resultado.getInt("id_categoria")
                );
            }

        } catch (SQLException excepcion) {
            System.out.println("Error al buscar el producto: " + excepcion.getMessage());
        }

        return producto;
    }

    
     
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre_producto = ?, descripcion = ?, precio = ?, stock = ?, id_categoria = ? WHERE id_producto = ?";

        try (Connection conexionBD = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexionBD.prepareStatement(sql)) {

            sentencia.setString(1, producto.getNombreProducto());
            sentencia.setString(2, producto.getDescripcion());
            sentencia.setDouble(3, producto.getPrecio());
            sentencia.setInt(4, producto.getStock());
            sentencia.setInt(5, producto.getIdCategoria());
            sentencia.setInt(6, producto.getIdProducto());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException excepcion) {
            System.out.println("Error al actualizar el producto: " + excepcion.getMessage());
            return false;
        }
    }

   
    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conexionBD = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexionBD.prepareStatement(sql)) {

            sentencia.setInt(1, idProducto);
            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el producto: " + excepcion.getMessage());
            return false;
        }
    }
}