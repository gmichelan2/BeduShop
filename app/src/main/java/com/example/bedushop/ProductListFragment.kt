package com.example.bedushop

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ProductListFragment(private var listener: (Product)->Unit={}) : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        val appContext = requireContext().applicationContext




        recycler.adapter= ShopRecyclerAdapter(getProducts(appContext),listener)
    }



    //obtener el contenido del json como string
    private fun getJsonDataFromAsset(context: Context, fileName: String = "products.json"): String? {

        val jsonString: String

        try {

            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

        } catch (ioException: IOException) {

            ioException.printStackTrace()

            return null

        }

        return jsonString

    }

    //convertir el strin de json en tipos de datos genéricos
    fun getProducts(context: Context): List<Product> {

        val jsonString = getJsonDataFromAsset(context)

        val listProductType = object : TypeToken<List<Product>>() {}.type

        return Gson().fromJson(jsonString, listProductType)

    }


}