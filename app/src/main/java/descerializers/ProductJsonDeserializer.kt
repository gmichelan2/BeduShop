package deserializers


import android.util.Log
import clases.Product
import com.google.gson.*
import org.json.JSONObject
import java.lang.reflect.Type

class ProductJsonDeserializer: JsonDeserializer<Product> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Product {

        var jsonObject= json?.asJsonObject

        var rating=jsonObject?.get("rating")
        rating=rating?.asJsonObject


        var rate=Gson().fromJson(rating?.get("rate"),JsonElement::class.java)
        Log.d("json object", "${rating}")
        //var prod= Product(1,"hola",0.2,"hola", "hola",2f,"hola")

        return Product(jsonObject!!.get("id").asInt,
            jsonObject?.get("title")?.asString,
            jsonObject?.get("price")!!.asDouble,
            jsonObject?.get("description")?.asString,
            jsonObject?.get("category")?.asString,
            rating?.get("rate")!!.asFloat,
            jsonObject?.get("image")?.asString)
    }
        //return Product(1,"hola",0.2,"hola", "hola",2f,"hola")


}