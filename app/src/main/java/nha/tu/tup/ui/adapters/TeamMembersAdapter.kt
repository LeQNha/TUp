package nha.tu.tup.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R

class TeamMembersAdapter(private val avatars: List<Int>, private val context: Context) :
    RecyclerView.Adapter<TeamMembersAdapter.TeamMemberViewHolder>() {

    // Tạo ViewHolder
    inner class TeamMemberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
    }

    // Khởi tạo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.team_members_rv_item_layout, parent, false)
        return TeamMemberViewHolder(view)
    }

    // Gắn dữ liệu cho ViewHolder
    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        if (position == avatars.size) {
            // Đây là nút "add member"
            holder.imgAvatar.setImageResource(R.drawable.ic_sign_up)
        } else {
            // Hiển thị avatar thành viên
            holder.imgAvatar.setImageResource(avatars[position])
        }
    }

    // Trả về số lượng mục (bao gồm thêm mục cho nút "Thêm thành viên")
    override fun getItemCount(): Int {
//        return if (avatars.size >= 3) 4 else avatars.size + 1
        return avatars.size
    }
}
