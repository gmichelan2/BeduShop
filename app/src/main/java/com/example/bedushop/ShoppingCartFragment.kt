package com.example.bedushop

import adaptadores.ShoppingCartRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import realm.Cart
import realm.Product

/**
 * Fragmento del carrito de compras
 * **/
class ShoppingCartFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var btnPurchase: Button
    private lateinit var emptyCartButton:Button
    private lateinit var empty_cart:ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.shopping_cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //intancio el recycler view
        recycler= view.findViewById(R.id.shopping_cart_recyclerView)
        recycler.layoutManager= LinearLayoutManager(requireContext())


        //tomo el boton de compra
        btnPurchase=view.findViewById(R.id.shopping_cart_button)

        //agarro el boton de carrito vacio para darle la navegabilidad
        emptyCartButton=view.findViewById(R.id.empty_shoppingcart_button)

        empty_cart=view.findViewById(R.id.empty_cart_layout)
        empty_cart.visibility=View.GONE

        //inicializo realm y busco los elementos en el carrito para mostrarlos
        val realm= Realm.getDefaultInstance()
        val cartList=realm.where(Cart::class.java).findAll()

        if(cartList.isEmpty()){//si el carrito esta vacío voy al fragmento de carrito vacio
            //btnPurchase.visibility=View.GONE
             findNavController().navigate(R.id.action_nav_cart_to_emptyCartFragment,null,null)
        }else{
            recycler.adapter=ShoppingCartRecyclerAdapter(btnPurchase,empty_cart,cartList)
        }

        btnPurchase.setOnClickListener {
            var total=calcularTotal(cartList, realm).toString()
            val action=ShoppingCartFragmentDirections.actionNavCartToPayFragment(total)
            findNavController().navigate(action,null)
        }

        emptyCartButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cart_to_nav_home, null,null)
        }
    }

    private fun calcularTotal(cartList: List<Cart>, realm:Realm):Double{
        var total=0.0

        for(i in 0..cartList.size-1){
            var cart= cartList.get(i)
            var product= realm.where(Product::class.java).equalTo("id", cart.id_product).findFirst()
            var parcial= cart.cant?.times(product?.price!!)
            total+=parcial!!
        }

        return total
    }

}