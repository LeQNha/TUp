package nha.tu.tup.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.util.Listener
import nha.tu.tup.R
import nha.tu.tup.models.User

class SearchFriendAdapter () : RecyclerView.Adapter<SearchFriendAdapter.SearchFriendViewHolder>() {

    class SearchFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.user_name_txt)
        val mutualFriends: TextView = itemView.findViewById(R.id.mutual_friends_txt)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val addFriendButton: Button = itemView.findViewById(R.id.add_friend_button)
    }

    val differCallback = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_friend_rv_item_layout, parent, false)
        return SearchFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFriendViewHolder, position: Int) {
        val user = differ.currentList[position]
//        holder.userName.text = user.name
//        holder.mutualFriends.text = "${user.mutualFriends} mutual friends"
//        holder.profileImage.setImageResource(user.imageResId)
        holder.apply {
            userName.text = user.username
            addFriendButton.setOnClickListener {
                onAddFriendBtnClickListener?.invoke(user)

//                addFriendButton.setText("Requested")
//                addFriendButton.setBackgroundColor(Color.parseColor("#C2E1FD"))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onAddFriendBtnClickListener : (((User)) -> Unit)? = null

    fun setOnAddFriendBtnClickListener(listener: (User) -> Unit){
        onAddFriendBtnClickListener = listener
    }
}