package com.alezek.tecnobooks.model;

import java.util.ArrayList;
import java.util.List;

public class CatalogoProductos {
    private List<Producto> productos;
    private static int cantidadProductos = 0; // Para indexar los productos.

    public CatalogoProductos() {
        this.productos = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Catálogo de Productos:\n");
        for (Producto producto : productos) {
            sb.append(producto.toString()).append("\n");
        }
        return sb.toString();
    }

    public boolean catalogoVacio() {
        return productos.isEmpty();
    }

    public boolean precargarProductos() {
        if (!catalogoVacio()) {
            return false; // No se precargan nada. El catálogo ya tiene productos.
        }
        agregarNuevoProducto(new Producto("Laptop", 1500.00, 10, "Electrónica"));
        agregarNuevoProducto(new Producto("Smartphone", 800.00, 20, "Electrónica"));
        agregarNuevoProducto(new Producto("Libro de Java", 30.00, 50, "Libros"));
        agregarNuevoProducto(new Producto("Auriculares", 100.00, 15, "Accesorios"));
        return true;
    }

    public List<Producto> getListaProductos() {
        return new ArrayList<>(productos);
    }

    // Uso polimorfismo para sobrecargar el método existeProducto,
    // dependiendo del tipo de parámetro.
    // Si el producto existe, devuelvo su id. Caso contrario, devuelvo 0.
    public boolean existeProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return true;
            }
        }
        return false;
        // Así sería con streams:
        // return productos.stream().anyMatch(p -> p.getId() == id);
    }

    public boolean existeProducto(String nombre) {
        // return productos.stream().anyMatch(p -> nombre.equalsIgnoreCase(p.getNombre()));
        for (Producto producto : productos) {
            if (nombre.equalsIgnoreCase(producto.getNombre())) {
                return true;
            }
        }
        return false;
    }


    public boolean agregarNuevoProducto(Producto producto) {
        // No se permite agregar un producto nulo.
        if (producto == null) {
            return false;
        }
        // No se permite agregar un producto con un nombre
        // que ya existe en el catálogo.
        if (existeProducto(producto.getNombre())) {
            return false;
        }
        cantidadProductos++;
        return productos.add(producto);
    }

    // El método "eliminarProducto" es polimórfico.
    // Acepta eliminar por id o por nombre.
    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
        // Se podría utilizar "remove(indice)", pero así es
        // más fácil.
    }

    public boolean eliminarProducto(String nombre) {
        if (nombre == null) {
            return false;
        }
        return productos.removeIf(p -> nombre.equalsIgnoreCase(p.getNombre()));
    }

    // El método "buscarProducto" es polimórfico.
    // Esta sobrecargado para acepta búsqueda 
    // por id o por nombre.
    public Producto buscarProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // El método "buscarProducto" es polimórfico.
    // Esta sobrecargado para acepta búsqueda 
    // por id o por nombre.
    // public List<Producto> buscarProducto(String nombre) {
    public Producto buscarProducto(String nombre) {
        // List<Producto> resultados = new ArrayList<>();
        // En princio, no hay forma de insertar productos
        // repetidos, así que consideraré que devuelvo 
        // cero o uno.
        if (nombre == null || nombre.isBlank()) {
            return null;
        }
        for (Producto producto : productos) {
            if (nombre.equalsIgnoreCase(producto.getNombre())) {
                return producto;
            }
        }
        return null;
    }

    public boolean modificarProductoPorId(int id, Producto nuevoProducto) {
        if (nuevoProducto == null) {
            return false;
        }
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.set(i, nuevoProducto);
                return true;
            }
        }
        return false;
    }

    public boolean modificarProductoPorNombre(String nombre, Producto nuevoProducto) {
        if (nombre == null || nuevoProducto == null) {
            return false;
        }
        for (int i = 0; i < productos.size(); i++) {
            if (nombre.equalsIgnoreCase(productos.get(i).getNombre())) {
                productos.set(i, nuevoProducto);
                return true;
            }
        }
        return false;
    }
}
