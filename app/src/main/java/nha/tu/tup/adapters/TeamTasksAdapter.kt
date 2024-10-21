package nha.tu.tup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nha.tu.tup.R
import nha.tu.tup.models.Task

class TeamTasksAdapter(): RecyclerView.Adapter<TeamTasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val taskTitle = itemView.findViewById<TextView>(R.id.txtTaskTitle)
        val taskDue = itemView.findViewById<TextView>(R.id.txtTaskDue)
        val taskMemberNumber = itemView.findViewById<TextView>(R.id.txtTaskMemberNumber)
    }

    val differCallBacck = object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBacck)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_tasks_rv_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = differ.currentList[position]
        holder.apply {
            taskTitle.text = currentTask.taskTitle
//            taskDue.text = currentTask.taskDue
//            taskMemberNumber.text = currentTask.memberNumber.toString() + " Members"
            taskMemberNumber.text = "2 Members"

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(currentTask)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Task) -> Unit)? = null

    fun setOnItemClickListener(listener: (Task) -> Unit){
        onItemClickListener = listener
    }
}