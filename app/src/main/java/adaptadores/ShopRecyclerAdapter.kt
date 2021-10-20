package adaptadores



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.bedushop.R



/**
 * Adaptador para el recycler view
 * **/
class ShopRecyclerAdapter(
                          private val products:List<realm.Product>,
                          private val listener: (realm.Product, ImageView)->Unit):
    RecyclerView.Adapter<ShopRecyclerAdapter.ViewHolder>()  {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val productTitle=view.findViewById<TextView>(R.id.product_title)
        private val productPrice=view.findViewById<TextView>(R.id.product_price)
        private val productRate=view.findViewById<RatingBar>(R.id.product_rating_bar)
        private val productImage=view.findViewById<ImageView>(R.id.product_image)
        private val productRatingBarLabel=view.findViewById<TextView>(R.id.product_rating_bar_label)

        fun getProductImage():ImageView{
            return productImage
        }


        fun bind(product: realm.Product){
            productTitle.text=product.title
            productPrice.text="$ ${product.price}"
            productRate.rating=product.rating!!
            productImage.load(product.image)
            productRatingBarLabel.text=product.rating.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val product=products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener{listener(product,holder.getProductImage())}
    }

    override fun getItemCount():Int{
        return products.size
    }
}