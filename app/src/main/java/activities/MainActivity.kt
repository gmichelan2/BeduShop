package activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bedushop.R

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //oculto la action bar
        var actionBar= supportActionBar;
        actionBar?.hide()


//        val host: NavHostFragment = supportFragmentManager
//            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment? ?: return


    }
}