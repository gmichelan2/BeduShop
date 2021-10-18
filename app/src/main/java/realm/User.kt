package realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open public class User(): RealmObject() {

    @PrimaryKey
    var id:Int?=null
    var email:String?=null

}