package nha.tu.tup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class SeeAllMemberAdapter2(private val memberList: List<String>) : RecyclerView.Adapter<SeeAllMemberAdapter2.MemberViewHolder>() {


    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val usernameTxt: TextView = itemView.findViewById(R.id.username_txt)
        val moreButton: ImageView = itemView.findViewById(R.id.more_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.see_all_members_rv_item_layout, parent, false))

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