package com.example.bedushop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load


/**
 * Adaptador para el recycler view
 * **/
class ShopRecyclerAdapter(
                          private val products:List<Product>,
                          private val listener: (Product)->Unit):RecyclerView.Adapter<ShopRecyclerAdapter.ViewHolder>()  {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val productTitle=view.findViewById<TextView>(R.id.product_title)
        private val productPrice=view.findViewById<TextView>(R.id.product_price)
        private val productRate=view.findViewById<RatingBar>(R.id.product_rating_bar)
        private val productImage=view.findViewById<ImageView>(R.id.product_image)

        fun bind(product:Product){
            productTitle.text=product.title
            productPrice.text="$ ${product.price.toString()}"
            productRate.rating=product.rate as Float
            productImage.load(product.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int){
        val product=products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener{listener(product)}
    }

    override fun getItemCount():Int{
        return products.size
    }
}