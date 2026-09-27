package com.tiendaaseo.modelo;


public class Producto {

    private int idProducto;
    private String nombreProducto;
    private String descripcion;
    private double precio;
    private int stock;
    private int idCategoria;

    public Producto() {
    }

    public Producto(String nombreProducto, String descripcion, double precio, int stock, int idCategoria) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public Producto(int idProducto, String nombreProducto, String descripcion, double precio, int stock, int idCategoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "ID: " + idProducto
                + " | Nombre: " + nombreProducto
                + " | Descripción: " + descripcion
                + " | Precio: $" + precio
                + " | Stock: " + stock
                + " | ID Categoría: " + idCategoria;
    }
}