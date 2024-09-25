package nha.tu.tup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class MemberAddAdapter(private val memberList: List<String>) : RecyclerView.Adapter<MemberAddAdapter.MemberAddViewHolder>() {


    inner class MemberAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val usernameTxt: TextView = itemView.findViewById(R.id.username_txt)
        val addButton: Button = itemView.findViewById(R.id.add_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAddViewHolder {
        return MemberAddViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.add_member_rv_item_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberAddViewHolder, position: Int) {
        val currentMember = memberList[position]

        holder.apply {
            usernameTxt.text = currentMember
        }
    }
}