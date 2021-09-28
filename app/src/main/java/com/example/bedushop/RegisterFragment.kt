package com.example.bedushop

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.textfield.TextInputLayout


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name=view.findViewById<TextInputLayout>(R.id.register_name)
        var email=view.findViewById<TextInputLayout>(R.id.register_email)
        var phone=view.findViewById<TextInputLayout>(R.id.register_phone)
        var pass=view.findViewById<TextInputLayout>(R.id.register_password)
        var registerButton=view.findViewById<Button>(R.id.register_button)

        val options= navOptions {
            anim{
                enter=R.anim.slide_in_left
                exit=R.anim.slide_out_right
                popExit=R.anim.slide_out_right
                popEnter=R.anim.slide_in_left
            }
        }

        //chequeo que los campos esten cargados, en caso negativo devuelvo error en ca ainput usando Material
        registerButton.setOnClickListener {

            if(name.editText?.text.toString()==""){
                name.error=getString(R.string.name_error)
            }
            if(email.editText?.text.toString()==""){
                email.error=getString(R.string.email_error)
            }
            if(phone.editText?.text.toString()==""){
                phone.error=getString(R.string.phone_error)
            }
            if(pass.editText?.text.toString()==""){
                pass.error=getString(R.string.password_error)
            }

            if(name.editText?.text.toString() != "" && email.editText?.text.toString() != "" && phone.editText?.text.toString() != "" && pass.editText?.text.toString() != ""){
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment,null,options)
                Toast.makeText(context, "Registro Exitoso!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "Revise si completó todos los parámetros", Toast.LENGTH_SHORT).show()
            }

        }

        //Cada vez que se detecte un evento de carga de escritura, limpio el mensaje de error en cada campo
        name.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                name.error = null
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name.error = null
            }
        })

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

        phone.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                phone.error = null
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                phone.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                phone.error = null
            }
        })

        pass.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                pass.error = null
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pass.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pass.error = null
            }
        })


    }
}