package activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bedushop.R

class MainActivity : AppCompatActivity() {

    companion object{
        val PREFS_NAMES="com.example.sharedPreferences"
        val USER="USER_ID"
    }

    private lateinit var preferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //oculto la action bar
        var actionBar= supportActionBar;
        actionBar?.hide()

        preferences=getSharedPreferences(PREFS_NAMES, Context.MODE_PRIVATE)


    }

}