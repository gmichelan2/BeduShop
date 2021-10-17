package com.example.bedushop

import activities.MainActivity
import adaptadores.UserProfileItemAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import AuxFragments.AddressFragment
import clases.User
import clases.UserProfileItem
import coil.api.load
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random

/**
 * Fragmento de perfil de usuario
 * **/
class UserProfileFragment : Fragment() {

    //url para traer las personas para el perfil de usuario de la app
    private val urlPerfil="https://reqres.in/api/users/"

    private lateinit var name:TextView
    private lateinit var email:TextView
    private lateinit var profileImage:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.user_profile_fragment, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name=view.findViewById(R.id.userprofile_user_text)
        email= view.findViewById(R.id.userprofile_email)
        profileImage=view.findViewById(R.id.userprofile_image)

        //llamo al método para generar un usuario random
        generarUsuario()

        val recycler= view.findViewById<RecyclerView>(R.id.userprofile_reciclerView)
        val appContext= requireContext().applicationContext


        //agrego este divider para que aparezcan líneas entre los items de la lista
        recycler.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        recycler.adapter=UserProfileItemAdapter(appContext, getItems(appContext),customListener())
        recycler.layoutManager=LinearLayoutManager(appContext)
        recycler.setHasFixedSize(true)

        //boton de logout
        val logout= view.findViewById<TextView>(R.id.userprofile_logout)
        logout.setOnClickListener {
            val intentLogout= Intent(activity, MainActivity::class.java)
            startActivity(intentLogout)
        }

    }

    private fun customListener():(UserProfileItem)->Unit ={
        val addressFragment= AddressFragment()
        addressFragment.show(parentFragmentManager,"fragment")
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String = "userprofileItems.json"): String? {

        val jsonString: String

        try {

            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

        } catch (ioException: IOException) {

            ioException.printStackTrace()
            return null
        }
        return jsonString

    }

    //convertir el string de json en tipos de datos genéricos
    fun getItems(context: Context): List<UserProfileItem> {

        val jsonString = getJsonDataFromAsset(context)

        val listItemType = object : TypeToken<List<UserProfileItem>>() {}.type

        return Gson().fromJson(jsonString, listItemType)

    }

    private fun generarUsuario(){

        val okHttp= OkHttpClient()
        val random= Random.nextInt(1,12)//para buscar un perfil random

        //mail de prueba janet.weaver@reqres.in
        val request= Request.Builder().url("$urlPerfil$random").build()

        val clientBuilder= okHttp.newBuilder()
        clientBuilder.build().newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body= response.body?.string()

                try{
                    val json= JSONObject(body).getJSONObject("data")

                    //Log.d("cuerpo del body", json.get("data").toString())
                    activity?.runOnUiThread {
                        name.text=json.getString("first_name")
                        email.text=json.getString("email")
                        profileImage.load(json.getString("avatar"))
                    }

                }catch(e:Error){
                    e.printStackTrace()
                }
            }

        })
    }


}