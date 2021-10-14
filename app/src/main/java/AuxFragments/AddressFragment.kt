package AuxFragments


import android.app.Activity
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        mFusedLocationClient=LocationServices.getFusedLocationProviderClient(requireActivity())

        geocoder= Geocoder(requireContext(), Locale.getDefault())


       // val adresses= geocoder.getFromLocation(latitude, longitude,1)
        btnClose.setOnClickListener {
            this.dismiss()
        }
    }

}