package nha.tu.tup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R
import nha.tu.tup.models.Team
import java.text.SimpleDateFormat
import java.util.Locale

class TeamAdapter(): RecyclerView.Adapter<TeamAdapter.TeamItemHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamItemHolder {
        return TeamItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_rv_item_layout,parent,false))
    }

    private var differCallback = object: DiffUtil.ItemCallback<Team>(){
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: TeamItemHolder, position: Int) {
        val currentTeam = differ.currentList[position]
        holder.apply {
            projectName.text = currentTeam.teamName

            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            createdDate.text = currentTeam.createdDate?.let {
                sdf.format(it.toDate())
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickListener?.let {
                it(currentTeam)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class TeamItemHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val projectName = itemView.findViewById<TextView>(R.id.item_team_name)
        val createdDate = itemView.findViewById<TextView>(R.id.item_team_created_date)
    }

    private var onItemClickListener: ((Team) -> Unit)? = null

    fun setOnItemClickListener(listener: (Team) -> Unit){
        onItemClickListener = listener
    }
}