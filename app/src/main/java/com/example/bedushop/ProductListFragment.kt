package com.example.bedushop


import adaptadores.ShopRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import realm.Product
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import deserializers.ProductJsonDeserializer
import io.realm.Realm
import okhttp3.*
import java.io.IOException

class ProductListFragment(private var listener: (realm.Product)->Unit={}) : Fragment() {

    private val url="https://fakestoreapi.com/products" //cuando traia los productos desde una api
    private lateinit var recycler:RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar=view.findViewById(R.id.productlist_progressBar)
        recycler = view.findViewById(R.id.productsRecyclerView)

        listener={
            val action= ProductListFragmentDirections.actionProductListFragment2ToProductDetailFragment2(it)
            findNavController().navigate(action,null)
        }

        //cambio la consigna y en lugar de traer todo dede una API rest debo traer todo desde una base de datos.
        //getProducts2()
        loadProducts()

    }

    //cargo los productos al adaptador desde una base de datos usando realm
    private fun loadProducts(){
        val realm= Realm.getDefaultInstance()
        val productos= realm.where(realm.Product::class.java).findAll()
        progressBar.visibility=View.GONE
        recycler.adapter= ShopRecyclerAdapter(productos,listener)
    }

//funcion utilizada para traer los productos desde un servicio web, como cambio la consigna ahora no se usa
    private fun getProducts(){
        val okHttpClient= OkHttpClient()

        val request= Request.Builder().url(url).build()

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val body= response.body?.string()
                val listProductType = object : TypeToken<List<Product>>() {}.type
                val gson= GsonBuilder().registerTypeAdapter(Product::class.java, ProductJsonDeserializer()).create()
                val productList = gson.fromJson<List<Product>>(body,listProductType)
                activity?.runOnUiThread {
                    progressBar.visibility=View.GONE
                    recycler.adapter= ShopRecyclerAdapter(productList,listener)
                }

            }

        })

    }


}