package activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.bedushop.R

class MainActivity : AppCompatActivity() {

    companion object{
        val PREFS_NAMES="com.example.sharedPreferences"
        val USER="USER_ID"
        val CHANNEL_SHOPPPING= "BEDUSHOP"
    }


    private lateinit var preferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //oculto la action bar
        var actionBar= supportActionBar;
        actionBar?.hide()

        preferences=getSharedPreferences(PREFS_NAMES, Context.MODE_PRIVATE)

        //Para android Oreo en adelante, s obligatorio registrar el canal de notificación
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel(){
        val name= getString(R.string.channel_shopping)
        val descriptionText= getString(R.string.shopping_description)
        val importance= NotificationManager.IMPORTANCE_DEFAULT
        val channel= NotificationChannel(CHANNEL_SHOPPPING, name, importance).apply{
            description=descriptionText
        }
        val notificationManager:NotificationManager= getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

}