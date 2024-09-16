package nha.tu.tup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R
import nha.tu.tup.models.Task

class TeamTasksAdapter(val teamTaskList: List<Task>): RecyclerView.Adapter<TeamTasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val taskTitle = itemView.findViewById<TextView>(R.id.txtTaskTitle)
        val taskDue = itemView.findViewById<TextView>(R.id.txtTaskDue)
        val taskMemberNumber = itemView.findViewById<TextView>(R.id.txtTaskMemberNumber)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamTasksAdapter.TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_tasks_rv_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: TeamTasksAdapter.TaskViewHolder, position: Int) {
        val currentTask = teamTaskList[position]
        holder.apply {
            taskTitle.text = currentTask.taskTitle
            taskDue.text = currentTask.taskDue
            taskMemberNumber.text = currentTask.memberNumber.toString() + " Members"

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(currentTask)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return teamTaskList.size
    }

    private var onItemClickListener: ((Task) -> Unit)? = null

    fun setOnItemClickListener(listener: (Task) -> Unit){
        onItemClickListener = listener
    }
}