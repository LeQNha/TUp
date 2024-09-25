package nha.tu.tup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R
import nha.tu.tup.models.Team

class TeamAdapter(private val teamList: List<Team>): RecyclerView.Adapter<TeamAdapter.TeamItemHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamItemHolder {
        return TeamItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_rv_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: TeamItemHolder, position: Int) {
        val currentTeam = teamList[position]
        holder.apply {
            projectName.text = currentTeam.projectName
            createdDate.text = currentTeam.createdDate
        }
        holder.itemView.setOnClickListener{
            onItemClickListener?.let {
                it(currentTeam)
            }
        }
    }

    override fun getItemCount(): Int {
        return teamList.size
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