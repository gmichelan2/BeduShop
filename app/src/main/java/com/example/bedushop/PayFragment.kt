package com.example.bedushop

import activities.LoggedActivity
import activities.MainActivity
import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.realm.Realm
import realm.Cart

class PayFragment: Fragment() {

    private lateinit var btnPay: Button
    private lateinit var paySubtotal:TextView
    private lateinit var payTotal:TextView

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
        paySubtotal=view.findViewById(R.id.pay_subtotal)
        payTotal=view.findViewById(R.id.pay_total)

        val safeArgs:PayFragmentArgs by navArgs()
        val subt=safeArgs.total

        paySubtotal.text=(Math.round(subt.toDouble() * 100.0) / 100.0).toString()

        payTotal.text=(Math.round((subt.toDouble()+30) * 100.0) / 100.0).toString()

        btnPay.setOnClickListener {
            val realm= Realm.getDefaultInstance()
            realm.executeTransaction { transaction->
                realm.delete(Cart::class.java)
            }

            //creo el pending intent para que la notificación me redireccione
            val pendingIntent=NavDeepLinkBuilder(requireContext())
                .setComponentName(LoggedActivity::class.java)
                .setGraph(R.navigation.nav_logged_graph)
                .setDestination(R.id.successfullPurchase)
                .createPendingIntent()


            simpleNotification(pendingIntent)

            findNavController().navigate(R.id.action_pay_fragment_to_successfullPurchase,null,null)
        }
    }

    private fun simpleNotification(pendingIntent:PendingIntent){
        var builder= NotificationCompat.Builder(requireContext(), MainActivity.CHANNEL_SHOPPPING)
            .setSmallIcon(R.drawable.bolsa_bedu)
            .setContentTitle(getString(R.string.notify_title))
            .setContentText(getString(R.string.notify_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //lanzamos la notificación
        with(NotificationManagerCompat.from(requireContext())){
            notify(20, builder.build())
        }
    }


}