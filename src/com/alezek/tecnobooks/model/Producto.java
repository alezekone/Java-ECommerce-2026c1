package com.alezek.tecnobooks.model;

public class Producto {
    private static int idProducto = 0;
    
    private final int id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    // El id se asigna automáticamente, por eso no lo recibimos como parámetro.
    // Pero...tengo un problema: al crear un nuevo producto, ya le estoy asignando un id,
    // sin embargo, si el producto recibe el nombre de un producto que ya existe
    // en el catálogo, no debería aceptarse, y el id que le asigné se desperdicia. Esto se soluciona si el id se asigna
    public Producto(String nombre, double precio, int stock, String categoria) {
        this.id = ++idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // ******* Getters *******
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getCategoria() {
        return categoria;
    }

    // ******* Setters *******
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // ******* toString *******
    @Override
    public String toString() {
        return "Producto {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    
}
