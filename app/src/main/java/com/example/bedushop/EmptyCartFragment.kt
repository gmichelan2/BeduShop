package com.example.bedushop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class EmptyCartFragment : Fragment() {
    private lateinit var emptyCartBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyCartBtn=view.findViewById(R.id.empty_cart_button)

        emptyCartBtn.setOnClickListener {
            findNavController().navigate(R.id.action_emptyCartFragment_to_nav_home)
        }
    }

}