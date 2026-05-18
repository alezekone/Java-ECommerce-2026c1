package com.alezek.tecnobooks.ui;

import com.alezek.tecnobooks.model.CatalogoProductos;
import com.alezek.tecnobooks.model.Producto;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void mostrar() {
        CatalogoProductos catalogo = new CatalogoProductos();
        Scanner sc = new Scanner(System.in);
        String inputString; // Para almacenar la entrada del usuario, que puede ser un id o un nombre.
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("Menú:");
            System.out.println("0. Precargar productos de prueba");
            System.out.println("1. Agregar nuevo producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Buscar/Actualizar producto");
            System.out.println("5. Crear un pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");
            try {
                // opcion = sc.nextInt(); <-- No uso esto. Tiene problemas.
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue; // Me salto el resto del bloque y voy al principio
                          // del while, para mostrar el menú de nuevo.
            }
            // Usaré la forma moderna del switch. Se usa a partir de Java 14.
            // No necesita break, porque no "cae" al siguiente caso (conocido
            // como Fall-through condition).
            switch (opcion) {
                case 0 -> {
                    System.out.println("==================================");
                    System.out.println("Precargando productos de prueba...");
                    if (!catalogo.precargarProductos()) {
                        System.out.println("No se cargan productos de prueba.");
                        System.out.println("El catálogo ya tiene productos.");
                    }
                    System.out.println("==================================");
                }
                case 1 -> {
                    System.out.println("==================================");
                    System.out.println("Agregaremos un nuevo producto...");
                    
                    IO.print("Ingresa el nombre del nuevo producto: ");
                    String nombreProducto = sc.nextLine();

                    IO.print("Ingresa el precio del nuevo producto: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Por favor, ingresa un número válido para el precio.");
                        sc.next(); // Descarta la entrada inválida
                    }
                    double precioProducto = Double.parseDouble(sc.nextLine());

                    IO.print("Ingresa la cantidad incorporada de este producto: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Por favor, ingresa un número válido para la cantidad.");
                        sc.next(); // Descarta la entrada inválida
                    }
                    int cantidadProducto = Integer.parseInt(sc.nextLine());

                    IO.print("Ingresa la categoría del nuevo producto: ");
                    String categoriaProducto = sc.nextLine();

                    Producto nuevoProducto = new Producto(nombreProducto, precioProducto, cantidadProducto, categoriaProducto);
                    if(!catalogo.agregarNuevoProducto(nuevoProducto)) {
                        System.out.println("No se pudo agregar el producto. Ya existe uno con el mismo nombre.");
                    }
                    System.out.println("==================================");
                }

                case 2 -> {
                    System.out.println("==================================");
                    System.out.println("Listando productos...");
                    List<Producto> listaProductos = catalogo.getListaProductos();
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos en el catálogo.");
                    } else {
                        //Forma 1:
                        //System.out.println(listaProductos.toString());
                        //Forma 2:
                        //for (Producto producto : listaProductos) {
                        //    System.out.println(producto);
                        //}
                        //Forma 3: la que yo prefiero.
                        System.out.println(catalogo.toString());
                    }
                    System.out.println("==================================");
                }
                case 3 -> {
                    System.out.println("==================================");
                    System.out.println("Eliminar producto");
                    System.out.print("Indique el id o el nombre del producto a eliminar: ");
                    inputString = sc.nextLine();
                    try {
                        opcion = Integer.parseInt(inputString);
                        if (catalogo.eliminarProducto(opcion)) {
                            System.out.println("Producto eliminado exitosamente.");
                        } else {
                            System.out.println("No se encontró un producto con ese id.");
                        }
                    } catch (NumberFormatException e) {
                        if (catalogo.eliminarProducto(inputString)) {
                            System.out.println("Producto eliminado exitosamente.");
                        } else {
                            System.out.println("No se encontró un producto con ese nombre.");
                        }
                    }
                    System.out.println("==================================");
                }
                case 4 -> {
                    System.out.println("==================================");
                    System.out.println("Buscar/Actualizar producto");
                    System.out.print("Indique el id o el nombre del producto a buscar / actualizar: ");
                    inputString = sc.nextLine();
                    Producto productoBuscado = null;
                    try {
                        opcion = Integer.parseInt(inputString);
                        productoBuscado = catalogo.buscarProducto(opcion);
                        if (productoBuscado != null) {
                            System.out.println("Producto encontrado:");
                            System.out.println(productoBuscado.toString());
                        } else {
                            System.out.println("No se encontró un producto con ese id.");
                        }
                    } catch (NumberFormatException e) {
                        productoBuscado = catalogo.buscarProducto(inputString);
                        if (productoBuscado != null) {
                            System.out.println("Producto encontrado:");
                            System.out.println(productoBuscado.toString());
                        } else {
                            System.out.println("No se encontró un producto con ese nombre.");
                        }
                    }

                    if (productoBuscado != null) {
                        String respuesta = "";
                        do {
                            System.out.print("¿Desea actualizar este producto? (s/n): ");
                            respuesta = sc.nextLine();
                        } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
                        
                        if (respuesta.equalsIgnoreCase("s")) {
                            // Aquí podríamos reutilizar el código de creación de un nuevo producto,
                            // pero para no complicar el código, lo haré de forma explícita.
                            System.out.println("Ingresa el nuevo nombre del producto (deja en blanco para mantener el actual): ");
                            String nuevoNombre = sc.nextLine();
                            if (!nuevoNombre.isBlank()) {
                                productoBuscado.setNombre(nuevoNombre);
                            }

                            System.out.println("Ingresa el nuevo precio del producto (deja en blanco para mantener el actual): ");
                            String nuevoPrecioString = sc.nextLine();
                            if (!nuevoPrecioString.isBlank()) {
                                while (!nuevoPrecioString.matches("\\d+(\\.\\d+)?")) {
                                    System.out.println("Por favor, ingresa un número válido para el precio.");
                                    nuevoPrecioString = sc.nextLine();
                                }
                                double nuevoPrecio = Double.parseDouble(nuevoPrecioString);
                                productoBuscado.setPrecio(nuevoPrecio);
                            }

                            System.out.println("Ingresa la nueva cantidad del producto (deja en blanco para mantener la actual): ");
                            String nuevaCantidadString = sc.nextLine();
                            if (!nuevaCantidadString.isBlank()) {
                                while (!nuevaCantidadString.matches("\\d+")) {
                                    System.out.println("Por favor, ingresa un número válido para la cantidad.");
                                    nuevaCantidadString = sc.nextLine();
                                }
                                int nuevaCantidad = Integer.parseInt(nuevaCantidadString);
                                productoBuscado.setStock(nuevaCantidad);
                            }

                            System.out.println("Ingresa la nueva categoría del producto (deja en blanco para mantener la actual): ");
                            String nuevaCategoria = sc.nextLine();
                            if (!nuevaCategoria.isBlank()) {
                                productoBuscado.setCategoria(nuevaCategoria);
                            }

                            System.out.println("Producto actualizado exitosamente:");
                            System.out.println(productoBuscado.toString());
                        }
                    }


                    System.out.println("==================================");
                }
                case 5 -> {
                    System.out.println("==================================");
                    System.out.println("Crear un pedido");
                    System.out.println("==================================");
                }
                case 6 -> {
                    System.out.println("==================================");
                    System.out.println("Listar pedidos");
                    System.out.println("==================================");
                }
                case 7 -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("Opción no válida");
            }
        }
        sc.close();
    }
}