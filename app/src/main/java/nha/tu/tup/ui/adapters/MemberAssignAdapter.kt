package nha.tu.tup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class MemberAssignAdapter (private val memberList: List<String>) : RecyclerView.Adapter<MemberAssignAdapter.MemberAssignViewHolder>() {


    inner class MemberAssignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val usernameTxt: TextView = itemView.findViewById(R.id.username_txt)
        val assignButton: Button = itemView.findViewById(R.id.assign_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAssignViewHolder {
        return MemberAssignViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.assign_member_rv_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberAssignViewHolder, position: Int) {
        val currentMember = memberList[position]

        holder.apply {
            usernameTxt.text = currentMember
        }
    }
}