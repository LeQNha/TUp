package nha.tu.tup.ui.acitvities.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.ActivityTaskListBinding
import nha.tu.tup.models.Task
import nha.tu.tup.adapters.TeamTasksAdapter

class TaskList : AppCompatActivity() {

    private lateinit var binding : ActivityTaskListBinding
    private lateinit var teamTasksAdapter: TeamTasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskListRvSetUp()
    }

    private fun taskListRvSetUp(){
        val taskList = listOf<Task>(
            Task("Learn material", "22h", 3),
            Task("Assemble the part", "3d", 2),
            Task("Write the code", "1w", 4),
            Task("Buy hat", "4h", 1),
            Task("Write report", "1m", 2),
            Task("Set up", "1w", 4)
        )

        teamTasksAdapter = TeamTasksAdapter(taskList)
        val taskListLayoutManager = LinearLayoutManager(this)
        binding.taskListRv.apply {
            layoutManager = taskListLayoutManager
            adapter = teamTasksAdapter
        }

        teamTasksAdapter.setOnItemClickListener {
            val intent = Intent(this, LeaderTaskScreen::class.java)
            startActivity(intent)
        }
    }
}