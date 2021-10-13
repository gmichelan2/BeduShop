package com.example.bedushop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.navArgs
import clases.Product
import coil.api.load

class ProductDetailFragment() : Fragment() {

    private lateinit var productTitle: TextView
    private lateinit var productRating: RatingBar
    private lateinit var productImage:ImageView
    private lateinit var productPrice: TextView
    private lateinit var productShare: TextView
    private lateinit var productAddCart: Button
    private lateinit var productDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.product_detail_fragment, container, false)
        productTitle = view.findViewById(R.id.product_detail_title)
        productRating = view.findViewById(R.id.ratingBar)
        productImage = view.findViewById(R.id.product_detail_image)
        productPrice = view.findViewById(R.id.product_detail_price)
        productShare = view.findViewById(R.id.product_detail_share)
        productAddCart = view.findViewById(R.id.product_detail_add_cart_button)
        productDescription = view.findViewById(R.id.product_detail_description)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: ProductDetailFragmentArgs by navArgs()
        val product=safeArgs.product
        loadProduct(product)

        productAddCart.setOnClickListener {
            Toast.makeText(activity, "El producto ${product.title} ha sido agreado al carrito",Toast.LENGTH_SHORT).show()
        }
    }



    fun loadProduct(product: Product){


        productTitle.text=product.title
       // productRating.rating=product.rate
        productImage.load(product.image)
        productPrice.text="$ ${product.price.toString()}"
        productShare.text="$ ${String.format("%.2f",product.price/6).toString()}"
        productDescription.text=product.description
    }

}