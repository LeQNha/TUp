package nha.tu.tup.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class FriendRequestAdapter (private val requestList: List<String>) : RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder>() {

    class FriendRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val mutualFriendsAndTime: TextView = itemView.findViewById(R.id.mutual_friends_and_time)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val confirmButton: Button = itemView.findViewById(R.id.confirm_btn)
        val deleteButton: Button = itemView.findViewById(R.id.delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_request_rv_item_layout, parent, false)
        return FriendRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) {
        val currentFriendRequest = requestList[position]
        holder.apply {
            userName.text = currentFriendRequest

            confirmButton.setOnClickListener {
                Log.i("Test", "Test click")
            }
        }
    }

    override fun getItemCount(): Int {
        return requestList.size
    }
}