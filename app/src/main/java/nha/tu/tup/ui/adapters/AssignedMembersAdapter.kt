package nha.tu.tup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class AssignedMembersAdapter(private val memberList: List<String>) : RecyclerView.Adapter<AssignedMembersAdapter.MemberViewHolder>() {


    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImageTxt: ImageView = itemView.findViewById(R.id.profile_image_txt)
        val usernameTxt: TextView = itemView.findViewById(R.id.username_txt)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.assigned_member_rv_item_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val currentMember = memberList[position]

        holder.apply {
            usernameTxt.text = currentMember
        }
    }
}
