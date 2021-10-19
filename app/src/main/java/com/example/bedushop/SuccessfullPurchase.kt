package com.example.bedushop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SuccessfullPurchase: Fragment() {

    private lateinit var btnSuccess: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.successfull_purchase_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSuccess= view.findViewById(R.id.successfull_button)

        btnSuccess.setOnClickListener {
            findNavController().navigate(R.id.action_successfullPurchase_to_nav_home)
        }
    }

}