package nha.tu.tup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class SearchFriendAdapter (private val userNameList: List<String>) : RecyclerView.Adapter<SearchFriendAdapter.SearchFriendViewHolder>() {

    class SearchFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.user_name_txt)
        val mutualFriends: TextView = itemView.findViewById(R.id.mutual_friends_txt)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val addButton: Button = itemView.findViewById(R.id.add_friend_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_friend_rv_item_layout, parent, false)
        return SearchFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFriendViewHolder, position: Int) {
        val user = userNameList[position]
//        holder.userName.text = user.name
//        holder.mutualFriends.text = "${user.mutualFriends} mutual friends"
//        holder.profileImage.setImageResource(user.imageResId)
        holder.apply {
            userName.text = user
        }
    }

    override fun getItemCount(): Int {
        return userNameList.size
    }
}