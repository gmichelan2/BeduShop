package adaptadores


import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import clases.UserProfileItem
import com.example.bedushop.R

class UserProfileItemAdapter(private val context:Context,
                             private val items: List<UserProfileItem>,
                            private val listener: (UserProfileItem)->Unit):
                                RecyclerView.Adapter<UserProfileItemAdapter.ViewHolder>() {

    class ViewHolder(view: View, context:Context):RecyclerView.ViewHolder(view){
        private val itemIcon= view.findViewById<ImageView>(R.id.userprofile_item_icon)
        private val itemText= view.findViewById<TextView>(R.id.user_profile_item_text)
        private val context=context;


       fun bind(item: UserProfileItem){

           var id=context.resources.getIdentifier("com.example.bedushop:drawable/"+item.icon,null, null)
           itemIcon.setImageResource(id)
           itemIcon.setImageResource(id)
           itemText.text=item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.userprofile_list_item, parent, false)
        return ViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item=items[position]
        holder.bind(item)
        if(item.title=="Mis direcciones"){//si es el primer item le coloco el listener
            holder.itemView.setOnClickListener{
                listener(item)
            }
        }

    }

    override fun getItemCount():Int{
        return items.size
    }
}