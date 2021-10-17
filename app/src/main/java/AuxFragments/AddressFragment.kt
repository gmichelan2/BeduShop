package AuxFragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.bedushop.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddressFragment(): BottomSheetDialogFragment() {

    //permiso de geolocalización
    companion object{
        const val PERMISSION_ID = 33
    }

    //Clase para el manejo de la localización
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var geocoder:Geocoder
    lateinit var latitude:String
    lateinit var longitude:String
    lateinit var btnAddress: TextView
    lateinit var addresText: TextView

    private lateinit var btnClose: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_address, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClose=view.findViewById(R.id.fragment_address_close)
        btnAddress=view.findViewById(R.id.user_profile_address_button)
        addresText=view.findViewById(R.id.user_profile_address_text)
        mFusedLocationClient=LocationServices.getFusedLocationProviderClient(requireActivity())
        latitude=""
        longitude=""

        geocoder= Geocoder(requireContext(), Locale.getDefault())

        btnAddress.setOnClickListener {
            getLocation()

        }

        // val adresses= geocoder.getFromLocation(latitude, longitude,1)
        btnClose.setOnClickListener {
            this.dismiss()
        }
    }

    //consulta el status del permiso de la aplicación
    fun checkGranted(permission:String):Boolean{
        return ActivityCompat.checkSelfPermission(requireActivity(),permission)== PackageManager.PERMISSION_GRANTED
    }

    //chequeo si la app tiene permisos de geolocalización
    private fun checkPermissions():Boolean{
        if(checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) && checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION)){
            return true
        }
        return false
    }

    //pedir permisos requeridos para que funcione la localización
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    //consultar si el GPS esta prendido
    private fun isLocationEnabled():Boolean{
        var locationManager: LocationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        if(checkPermissions()){
            if(isLocationEnabled()){
                mFusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()){ location->
                    latitude=location?.latitude.toString()
                    longitude=location?.longitude.toString()
                    val address= geocoder.getFromLocation(latitude.toDouble(), longitude.toDouble(),1)
                    val addressString="${address.get(0).getAddressLine(0)}"
                    addresText.text=addressString

                }
            }
        }else{
            //si no hay permisos debe pedirlos
            requestPermissions()
        }
    }

}