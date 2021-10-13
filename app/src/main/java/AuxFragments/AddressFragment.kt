package AuxFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.bedushop.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressFragment: BottomSheetDialogFragment() {

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

        btnClose.setOnClickListener {
            this.dismiss()
        }
    }

}