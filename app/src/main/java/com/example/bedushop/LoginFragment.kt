package com.example.bedushop

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.textfield.TextInputLayout
/**
 * Fragmento que contiene la interfaz de login y la lógica del mismo
 * **/
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Efectos entre transiciones
        val options= navOptions {
            anim{
                enter=R.anim.slide_in_right
                exit=R.anim.slide_out_left
                popEnter=R.anim.slide_in_left
                popExit=R.anim.slide_out_right
            }
        }

        val linkRegistro= view.findViewById<TextView>(R.id.link_registro)

        linkRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment, null, options)
        }


        val botonSesion=view.findViewById<Button>(R.id.boton_sesion)
        val email=view.findViewById<TextInputLayout>(R.id.email)
        val password=view.findViewById<TextInputLayout>(R.id.password)

        //Toma los datos recibidos porlos inputs y de acuerdo a lo recibido avanza o devuelve error
            botonSesion.setOnClickListener {

                if (email.editText?.text.toString()=="") {
                    email.error = getString(R.string.email_error)
                }
                if (password.editText?.text.toString() == "") {
                    password.error= getString(R.string.password_error)
                }

                if (email.editText?.text.toString() != "" && password.editText?.text.toString() != "") {
                    Log.d(email.editText.toString(),"el log")
                    Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                    val intent=Intent(activity, LoggedActivity::class.java)
                    startActivity(intent)
                }
            }

            //Para escuchar los cambios realizados en los inputs y limpiar el mensaje de error
            email.editText?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    email.error = null
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    email.error = null
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    email.error = null
                }
            })
            password.editText?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    password.error = null
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    password.error = null
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    password.error = null
                }
            })
        }

        }

