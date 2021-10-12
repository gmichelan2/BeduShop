package adaptadores


import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import clases.UserProfileItem
import com.example.bedushop.R

class UserProfileItemAdapter(private val context:Context,
                             private val items: List<UserProfileItem>):
                                RecyclerView.Adapter<UserProfileItemAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val itemIcon= view.findViewById<ImageView>(R.id.userprofile_item_icon)
        private val itemText= view.findViewById<TextView>(R.id.user_profile_item_text)
        //private val context=context;


       fun bind(item: UserProfileItem){

          // var id=context.getResources().getIdentifier("com.example.bedushop:drawable/"+item.icon,null, null)
           //itemIcon.setImageResource(id)
           Log.d("Entro el item en bind", item.title + item.icon)
          //itemText.text=item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item=items[position]
        Log.d("Este es el item", item.title + item.icon)
        holder.bind(item)
    }

    override fun getItemCount():Int{
        return items.size
    }
}