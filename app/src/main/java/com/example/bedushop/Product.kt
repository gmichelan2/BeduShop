package com.example.bedushop

import android.os.Parcel
import android.os.Parcelable


//data class de producto
 class Product(val id:Int,
               val title:String,
               val price:Double,
               val description:String,
               val category:String,
               val rate:Float,
               val image:String
               ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeFloat(rate)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)

        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}