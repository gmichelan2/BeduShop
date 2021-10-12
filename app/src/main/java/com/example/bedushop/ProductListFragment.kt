package com.example.bedushop

import activities.LoggedActivity
import adaptadores.ShopRecyclerAdapter
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import clases.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class ProductListFragment(private var listener: (Product)->Unit={}) : Fragment() {

    private val url="https://fakestoreapi.com/products"
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

        getProducts2()
    }


    private fun getProducts2(){
        val okHttpClient= OkHttpClient()

        val request= Request.Builder().url(url).build()

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val body= response.body?.string()
                val listProductType = object : TypeToken<List<Product>>() {}.type
                val productList = Gson().fromJson<List<Product>>(body,listProductType)
                activity?.runOnUiThread {
                    progressBar.visibility=View.GONE
                    recycler.adapter= ShopRecyclerAdapter(productList,listener)
                }

            }

        })

    }


}