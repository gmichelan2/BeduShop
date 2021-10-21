#Lista de productos
La lista de productos, consiste en un RecyclerView dentro de un fragmento.
Durante la cursada, la carga de los productos se realizó de 3 maneras distintas a medida que los temas iban evolucionando:
En primer lugar los obtuvimos a través de un JSON almacenado dentro de nuestros repositorios, leyendo el mismo, luego convirtiéndolo a un JSON Object y finalmente obtener una lista de productos tomando cada elemento y transformándolo en un producto utilizando GSON.
En segundo lugar se obtuvo la información mediante una API albergada en https://fakestoreapi.com/products.
Para realizar esto se utilizó la herramienta OkHttp para envíar una solicitud manera asíncrona y luego se formateo el JSON obtenido usando GSON contrastando con la clase Product. 
Como la clase Product define un rating bar de tipo flotante mientras que el JSON obtenido define al rating como un objeto con dos valores, en los cuáles uno de ellos es el que nos interesa, para armar el objeto Product, se se tuvo que generar un “deserializador” (ProductJsonDescerializer) y pasarlo como adaptador a GSON para que generara el producto con el formato necesario.
Finalmente se optó por guardar los productos en una base de datos no relacional manejada con  Realm. Para  obtener los productos se realiza una consulta a dicha base, obteniendo el listado con productos. 




//codigo


Cada ítem de la lista tiene asignado un listener que al seleccionarse, guarda el producto al que hace referencia y lo manda, utilizando safeArgs, al fragmento de detalle del producto.

Detalle de productos

Este fragmento recibe, mediante safeArgs, el objeto Producto envíado por el fragmento de lista de productos y lo muestra de manera detallada.
//imagen

Pulsando el botón “agregar a carrito”, el producto se agrega en cantidad 1 al carrito y la navegación direcciona  directamente al fragmento carrito. 
