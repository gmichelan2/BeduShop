#Carrito

Este fragmento contiene un RecyclerView, un botón y un ConstraintLayout que contiene una imágen, un texto y un botón. 
Al iniciar el fragmento, se consulta en la base de datos creada con Realm, la colección de elementos del carrito (Cart) para ver si hay productos. En caso positivo se muestran seguidos de un botón para pagar, en caso negativo, se oculta el botón de pago y se visibiliza el layout de carrito vacío.
A medida que se cargan elementos al carrito el RecyclerView se alimenta y va mostrando los productos. 
Cada ítem de la lista muestra el producto y un contador con botonera que actúa de la siguiente manera dependiendo del caso:
Si se presiona el botón “+” se suma uno a la cantidad.
Si se presiona el botón “-” y sólo queda un producto, este se elimina de la lista, si quedan más de uno se resta uno.
Si en el detalle del producto se agrega un producto al carrito y en éste último ya hay existencias del mismo, el contador suma uno más.

El secreto para que esto funcione de manera correcta es atender cada cambio en la base de datos y pedirle al RecyclerView que ejecute los cambios a la vista. 
Una propuesta para mejorar esto sería agregar un observador para que realice las modificaciones.
