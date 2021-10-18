package com.example.bedushop

import adaptadores.ShoppingCartRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.shopping_cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler= view.findViewById(R.id.shopping_cart_recyclerView)
        recycler.layoutManager= LinearLayoutManager(requireContext())
        btnPurchase=view.findViewById(R.id.shopping_cart_button)

        val realm= Realm.getDefaultInstance()
        val cartList=realm.where(Cart::class.java).findAll()

        if(cartList.isEmpty()){
            btnPurchase.visibility=View.GONE
            //findNavController().navigate(R.id.action_nav_cart_to_emptyCartFragment,null,null)
        }else{
            recycler.adapter=ShoppingCartRecyclerAdapter(cartList)
        }

        btnPurchase.setOnClickListener {
            var total=calcularTotal(cartList, realm).toString()
            val action=ShoppingCartFragmentDirections.actionNavCartToPayFragment(total)
            findNavController().navigate(action,null)
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