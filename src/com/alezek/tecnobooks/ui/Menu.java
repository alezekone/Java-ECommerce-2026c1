package com.alezek.tecnobooks.ui;

import java.util.Scanner;

public class Menu {
    public static void mostrar() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("Menú:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Buscar/Actualizar producto");
            System.out.println("5. Crear un pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue; // Me salto el resto del bloque y voy al
                          // principio del while, para mostrar el menú
                          // de nuevo.
            }
            // opcion = scanner.nextInt();
            // Usaré la forma moderna del switch. Se usa a partir de Java 14.
            // No necesita break, porque no "cae" al siguiente caso (conocido
            // como Fall-through condition).
            switch (opcion) {
                case 1 -> System.out.println("Agregar producto");
                case 2 -> System.out.println("Listar productos");
                case 3 -> System.out.println("Eliminar producto");
                case 4 -> System.out.println("Buscar/Actualizar producto");
                case 5 -> System.out.println("Crear un pedido");
                case 6 -> System.out.println("Listar pedidos");
                case 7 -> System.out.println("Saliendo del programa");
                default -> System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }
}