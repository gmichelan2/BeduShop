package realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import org.json.JSONArray
import android.util.Log

//extension para la aplicacion, en ella se inicializara la base de datos de realm para que etse disponible en la aplicación para su uso
class MainApp: Application() {

    override fun onCreate(){
        super.onCreate()
        Realm.init(this)

        val array= JSONArray(getJsonFile("products.json"))
        val array2= JSONArray(getJsonFile("users.json"))
        Log.d("jsonusers", array2.toString())
        val config= RealmConfiguration.Builder().initialData { realm->


            for(i in 0 until array.length()){
                val p = realm.createObject(Product::class.java,i)
                Log.d("entra",p.toString())
                p.title=array.getJSONObject(i).getString("title")
                p.price=array.getJSONObject(i).getDouble("price")
                p.description=array.getJSONObject(i).getString("description")
                p.category=array.getJSONObject(i).getString("category")
                p.rating=array.getJSONObject(i).getString("rating").toFloat()
                p.image=array.getJSONObject(i).getString("image")
            }
            for (i in 0 until array.length()){
                val u= realm.createObject(User::class.java,i)
                Log.d("entra",u.toString())

                u.email=array2.getJSONObject(i).getString("email")
                Log.d("userdatabase",u.toString())

            }

        }
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .name("realmDB.realm")
            .build()

        Realm.setDefaultConfiguration(config)

    }

    //Traer el JSON de productos
    private fun getJsonFile(file:String):String{
        return applicationContext
            .assets
            .open(file).bufferedReader().use{it.readText()}
    }
}