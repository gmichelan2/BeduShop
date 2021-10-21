package activities

import android.animation.AnimatorInflater
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
import android.util.Log
import android.view.View
import androidx.core.view.marginBottom
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bedushop.*
import okhttp3.*



/**
Activity que maneja la aplicación y los intercambios de fragmentos, una vez que el usuario se ha logueado
**/
class LoggedActivity : AppCompatActivity() {


    private lateinit var bottomNav: BottomNavigationView
    private var bottomShow=true
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var hostFragment: FragmentContainerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loged)

        hostFragment=findViewById<FragmentContainerView>(R.id.logged_nav_host_fragment)

        //seteo el fragmento por defecto el cual va a ser la lista con el recycler view
        // replaceFragment(productListFragment)

        bottomNav = findViewById(R.id.bottom_navigation)

        //Busco el navHostFragment
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.logged_nav_host_fragment) as NavHostFragment

        //Seteo el botomNav pra qeu funcione con el navHostFragment

        bottomNav.setupWithNavController(navHostFragment.navController)//setea automaticamente las redirecciones en el bottomNav view

        //seteo una animacion para que aparezca el bottom nav
        lifecycleScope.launchWhenResumed {
            navHostFragment.findNavController()
                .addOnDestinationChangedListener { _, destination, _ ->
                    Log.d("destination", destination.toString())
                    when (destination.id) {
                        R.id.nav_home -> {
                           showBottomNav()
                        }

                        R.id.nav_cart -> {
                            showBottomNav()
                        }
                        R.id.nav_profile -> {
                            showBottomNav()
                        }
                        R.id.emptyCartFragment -> {
                            showBottomNav()
                        }
                        else -> {
                            AnimatorInflater.loadAnimator(this@LoggedActivity,R.animator.down).apply {
                                setTarget(bottomNav)
                                start() }
                            bottomShow=false
                            hostFragment.setPadding(0,0,0,0)

                        }
                    }
                }
        }
    }


    private fun showBottomNav(){
        if (!bottomShow) {
            AnimatorInflater.loadAnimator(this@LoggedActivity,R.animator.climb).apply {
                setTarget(bottomNav)
                start() }
            bottomShow=true
            hostFragment.setPadding(0,0,0,80)

        }
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