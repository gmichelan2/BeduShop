package com.example.bedushop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.net.Uri


/**
Activity que maneja la aplicación y los intercambios de fragmentos, una vez que el usuario se ha logueado
**/
class LoggedActivity : AppCompatActivity() {


    /*private val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_logged_graph) as NavHostFragment
    val navController = navHostFragment.navController*/

    private val productListFragment= ProductListFragment(){
       // Toast.makeText(this,it.category.toString(),Toast.LENGTH_SHORT).show()
        var detailFragment= ProductDetailFragment(it)
        var transaction= supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,detailFragment).addToBackStack(null).commit()

    }
    private val shoppingCartFragment= ShoppingCartFragment()
    private val userProfileFragment = UserProfileFragment()
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loged)

        //seteo el fragmento por defecto el cual va a ser la lista con el recycler view
        replaceFragment(productListFragment)

        bottomNav=findViewById(R.id.bottom_navigation)

        //De acuerdo a la opción seleccionada en el bottomNav es el fragmento que inserto
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home-> replaceFragment(productListFragment)

                R.id.nav_cart-> replaceFragment(shoppingCartFragment)

                R.id.nav_profile-> replaceFragment(userProfileFragment)
            }
            true
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
            R.id.option_info->{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bedu.org/"))
                startActivity(browserIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //método para reemplazar los fragmentos de acuerdo al botón seleccionado en el navBottomMenu
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.commit()
        }
    }
}