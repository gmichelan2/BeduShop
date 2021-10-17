package realm

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Product(@PrimaryKey
                   var id:Int?=null,
                   var title:String?=null,
                   var price:Double?=null,
                   var description:String?= null,
                   var category :String?= null,
                   var rating:Float?=null,
                   var image:String?=null ): RealmObject(),Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat()!!,
        parcel.readString()!!
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id!!)
        parcel.writeString(title!!)
        parcel.writeDouble(price!!)
        parcel.writeString(description!!)
        parcel.writeString(category!!)
        parcel.writeFloat(rating!!)
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