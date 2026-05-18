## Getting Started

Cumple con las siguientes opciones del menú:
0. Precargar productos de prueba
1. Agregar nuevo producto
2. Listar productos
3. Eliminar producto
4. Buscar/Actualizar producto
7. Salir

Aún está pendiente:

5. Crear un pedido
6. Listar pedidos

Desde ya, mil disculpas.

## Detalles de implementación

1.- Se utilizaron métodos polimórficos para "Eliminar producto" y "Buscar / Actualizar producto". Los métodos correspondientes aceptan el id o el nombre
del producto de nuestro interés, manteniendo el nombre pero encontrándose 
sobrecargados para aceptar argumentos de tipos distintos (int / String).

2.- Se utilizaron una variable estática para llevar la cantidad de productos creados, la cual es utilizada para asignar un id distinto a cada uno de estos.


## Folder Structure

model
-Producto.java
-CatalogoProductos.java
-Pedido.java
-AlmacenPedidos.java
ui
-Menu.java
App.java
.gitignore

## Dependency Management

Por el momento, no hay dependencias que se administren con gestores tales como Gradle o Maven.
