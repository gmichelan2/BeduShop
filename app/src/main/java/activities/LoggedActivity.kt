package activities

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.net.Uri
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import clases.User
import com.example.bedushop.*
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import kotlin.random.Random


/**
Activity que maneja la aplicación y los intercambios de fragmentos, una vez que el usuario se ha logueado
**/
class LoggedActivity : AppCompatActivity() {


    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loged)


        //seteo el fragmento por defecto el cual va a ser la lista con el recycler view
       // replaceFragment(productListFragment)

        bottomNav=findViewById(R.id.bottom_navigation)

        //Busco el navHostFragment
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.logged_nav_host_fragment) as NavHostFragment

        //Seteo el botomNav pra qeu funcione con el navHostFragment

        bottomNav.setupWithNavController(navHostFragment.navController)//setea automaticamente las redirecciones en el bottomNav view

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.option_search -> Toast.makeText(this,"Opción no disponible", Toast.LENGTH_SHORT).show()
            R.id.option_info ->{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bedu.org/"))
                startActivity(browserIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }




    //método para reemplazar los fragmentos de acuerdo al botón seleccionado en el navBottomMenu
//    private fun replaceFragment(fragment: Fragment){
//        if(fragment!=null){
//            val transaction= supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainer, fragment)
//            transaction.commit()
//        }
//    }
}