package realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open public class Cart:RealmObject() {
    @PrimaryKey
    var id_product:Int?=null
    var cant:Int?=null

}