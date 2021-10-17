package realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import org.json.JSONArray

//extension para la aplicacion, en ella se inicializara la base de datos de realm para que etse disponible en la aplicación para su uso
class MainApp: Application() {

    override fun onCreate(){
        super.onCreate()
        Realm.init(this)

        val array= JSONArray(getJsonFile())
        val config= RealmConfiguration.Builder().initialData { realm->
            for(i in 0 until array.length()){
                val p = realm.createObject(Product::class.java,i)
                p.title=array.getJSONObject(i).getString("title")
                p.price=array.getJSONObject(i).getDouble("price")
                p.description=array.getJSONObject(i).getString("description")
                p.category=array.getJSONObject(i).getString("category")
                p.rating=array.getJSONObject(i).getString("rating").toFloat()
                p.image=array.getJSONObject(i).getString("image")
            }
        }
            .deleteRealmIfMigrationNeeded()
            .name("realmDB.realm")
            .build()
        Realm.setDefaultConfiguration(config)
    }

    //Traer el JSON de productos
    private fun getJsonFile():String{
        return applicationContext
            .assets
            .open("products.json").bufferedReader().use{it.readText()}
    }
}