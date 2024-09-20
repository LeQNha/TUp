package nha.tu.tup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class FriendListAdapter(private val friendList: List<String>) : RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder>() {


    inner class FriendListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val usernameTxt: TextView = itemView.findViewById(R.id.user_name_txt)
        val moreButton: ImageView = itemView.findViewById(R.id.more_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        return FriendListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friend_list_rv_item_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        val currentFriend = friendList[position]

        holder.apply {
            usernameTxt.text = currentFriend
        }
    }
}