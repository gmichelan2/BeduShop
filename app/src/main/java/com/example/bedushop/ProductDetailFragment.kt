package com.example.bedushop

import activities.LoggedActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import realm.Product
import coil.api.load
import io.realm.Realm
import io.realm.kotlin.createObject
import realm.Cart


class ProductDetailFragment() : Fragment() {

    private lateinit var productTitle: TextView
    private lateinit var productRating: RatingBar
    private lateinit var productImage:ImageView
    private lateinit var productPrice: TextView
    private lateinit var productShare: TextView
    private lateinit var productAddCart: Button
    private lateinit var productDescription: TextView
    private lateinit var productDetailRateTag:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedElementEnterTransition=TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.product_detail_fragment, container, false)
        productTitle = view.findViewById(R.id.product_detail_title)
        productRating = view.findViewById(R.id.ratingBar)
        productImage = view.findViewById(R.id.product_detail_image)
        productPrice = view.findViewById(R.id.product_detail_price)
        productShare = view.findViewById(R.id.product_detail_share)
        productAddCart = view.findViewById(R.id.product_detail_add_cart_button)
        productDescription = view.findViewById(R.id.product_detail_description)
        productDetailRateTag=view.findViewById(R.id.product_detail_rate_value)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //obtengo lso safeArg enviados por la lista de productos
        val safeArgs: ProductDetailFragmentArgs by navArgs()
        val product=safeArgs.product


        //cargo el producto en la vista
        loadProduct(product)

        //seteo la animacion compartida en la vista de la imagen
        ViewCompat.setTransitionName(productImage, "")

        productAddCart.setOnClickListener {
            //conectarse a la db
            val realm= Realm.getDefaultInstance()
            val cart= realm.where(Cart::class.java).equalTo("id_product", product.id).findFirst()
            //verificar si el producto esta en la db
            //si esta sumarle uno, si no esta agregarlo a la db
            if(cart==null){//agregarlo a la db
                //fragment carrito vacio
                    realm.executeTransaction { transactionRealm->
                        val cart= transactionRealm.createObject(Cart::class.java, product.id)
                        cart.cant=1
                    }
            }else{
                realm.executeTransaction { transactionRealm->
                    cart.cant=cart.cant!!+1
                    transactionRealm.copyToRealmOrUpdate(cart)
                }
            }
            //navegar al carrito
            findNavController().navigate(R.id.action_productDetailFragment2_to_nav_cart)
            Toast.makeText(activity, "El producto ${product.title} ha sido agreado al carrito",Toast.LENGTH_SHORT).show()
        }
    }



    fun loadProduct(product: Product){

        productTitle.text=product.title
        productRating.rating=product.rating!!
        productImage.load(product.image)
        productPrice.text="$ ${product.price}"
        productShare.text="$ ${String.format("%.2f",product.price!!/6)}"
        productDescription.text=product.description
        productDetailRateTag.text=product.rating.toString()
    }

}