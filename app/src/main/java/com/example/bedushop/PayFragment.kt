package com.example.bedushop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PayFragment: Fragment() {

    private lateinit var btnPay: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pay_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPay= view.findViewById(R.id.paid_pay_button)

        btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_pay_fragment_to_successfullPurchase)
        }
    }


}