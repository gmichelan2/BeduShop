package adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import realm.Product
import coil.api.load
import com.example.bedushop.R
import io.realm.Realm
import io.realm.kotlin.delete
import realm.Cart


/*Adaptador para la lista de items del carrito*/
class ShoppingCartRecyclerAdapter(
                        private val cartList:List<Cart>):RecyclerView.Adapter<ShoppingCartRecyclerAdapter.ViewHolder>()

 {

     class ViewHolder(view: View, adapter:ShoppingCartRecyclerAdapter): RecyclerView.ViewHolder(view){
         private val cartItemTitle=view.findViewById<TextView>(R.id.cart_item_title)
         private val cartItemPrice=view.findViewById<TextView>(R.id.cart_item_price)
         private val cartItemCant=view.findViewById<TextView>(R.id.cart_item_cant)
         private val cartBtnSubs=view.findViewById<ImageView>(R.id.cart_item_sub)
         private val cartBtnAdd=view.findViewById<ImageView>(R.id.cart_item_add)
         private val cartItemImage=view.findViewById<ImageView>(R.id.cart_item_image)
         private val adapter=adapter


         fun bind(product: Product, cant:Int){
             cartItemTitle.text=product.title
             cartItemPrice.text="$ ${product.price}"
             cartItemCant.text=cant.toString()
             cartItemImage.load(product.image)
             cartBtnSubs.setOnClickListener {
                    productAction("substract", product)
             }

             //al apretar el boton más debo sumar una cantidad y agregarlo tambien a la cantidad de la base de datos
             cartBtnAdd.setOnClickListener {
                 productAction("add", product)
             }
         }

         private fun productAction(action:String, product:Product){
             val realm= Realm.getDefaultInstance()
             val cart= realm.where(Cart::class.java).equalTo("id_product",product.id).findFirst()
             if(action=="add"){//si es suma sólo agrego uno a la cantidad del producto
                 realm.executeTransaction { realmTransaction->
                     cart?.cant=cart?.cant?.plus(1)
                     realmTransaction.copyToRealmOrUpdate(cart)
                     adapter.notifyDataSetChanged()

                 }

             }else{//si es resta
                 if(cart?.cant==1){//verifico si la cantidad es 1, elimino el item cart
                     realm.executeTransaction { realmTransaction->
                        cart.deleteFromRealm()
                         adapter.notifyDataSetChanged()
                     }
                 }else{//si no solo resto uno al valor
                     realm.executeTransaction { realmTransaction->
                         cart?.cant=cart?.cant?.plus(-1)
                         realmTransaction.copyToRealmOrUpdate(cart)
                         adapter.notifyDataSetChanged()
                     }
                 }
             }
         }
     }



     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view=LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
         return ViewHolder(view, this)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int){
         val cart=cartList[position]
         val realm= Realm.getDefaultInstance()
         val product= realm.where(Product::class.java).equalTo("id",cart.id_product).findFirst()
         holder.bind(product!!, cart.cant!!)//nunca va a haber un producto nulo cargado en carrito ni una cantidad nula
         //holder.itemView.setOnClickListener{listener(product)}
     }

     override fun getItemCount():Int{
         return cartList.size
     }
 }