package nha.tu.tup.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R
import nha.tu.tup.models.User

class FriendRequestAdapter () : RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder>() {

    class FriendRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val mutualFriendsAndTime: TextView = itemView.findViewById(R.id.mutual_friends_and_time)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val confirmButton: ImageView = itemView.findViewById(R.id.accept_btn)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_btn)
    }

    val differCallback = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_request_rv_item_layout, parent, false)
        return FriendRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) {
        val currentFriendRequest = differ.currentList[position]
        holder.apply {
            userName.text = currentFriendRequest.username

            confirmButton.setOnClickListener {
                Log.i("Test", "Test click")
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}