package nha.tu.tup.ui.acitvities.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import nha.tu.tup.R
import nha.tu.tup.databinding.ActivityCreateNewTaskBinding
import nha.tu.tup.adapters.AssignedMembersAdapter
import nha.tu.tup.adapters.TeamMembersAdapter
import nha.tu.tup.models.Task
import nha.tu.tup.models.Team
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.ui.fragments.dialogFragments.AssignMemberDialogFragment
import nha.tu.tup.ui.fragments.dialogFragments.SeeAllAssignedMembersDialogFragment
import java.util.Date

class CreateNewTask : BaseActivity() {

    private lateinit var binding : ActivityCreateNewTaskBinding
    private lateinit var teamMembersAdapter: TeamMembersAdapter

    private lateinit var team: Team
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTeam()
        assignedMemberRvSetUp()
        onClickListenerSetUp()
    }

    private fun onClickListenerSetUp(){
        binding.seeAllAssignedMembersBtn.setOnClickListener {
            openSeeAllAssignedMemberDialogFragment()
        }
        binding.openAssignMemberDialogFragmentBtn.setOnClickListener {
            openAssignMemberDialogFragment()
        }
        binding.saveBtn.setOnClickListener {
            val taskTitle = binding.taskTitleEditTxt.text.toString()
            val taskDescription = binding.taskDescriptionEditTxt.text.toString()
            val task = Task(taskTitle = taskTitle, taskDescription = taskDescription, taskCreatedDate = Timestamp(Date()), teamId = team.teamId)

            taskViewModel.addNewTask(task)

            binding.taskTitleEditTxt.text.clear()
            binding.taskDescriptionEditTxt.text.clear()

            onBackPressed()
        }
    }

    private fun getTeam(){
        val parcelTeam = intent.getParcelableExtra<Team>("team")
        parcelTeam?.let {
            team = it
        }
    }

    private fun assignedMemberRvSetUp(){
        val avatars = listOf(
            R.drawable.default_profile_picture,
            R.drawable.ic_analytics,
            R.drawable.ic_filter,
            R.drawable.ic_project,
            R.drawable.default_profile_picture,
            R.drawable.default_profile_picture
        )

        teamMembersAdapter = TeamMembersAdapter(avatars, this)
        val assignedMemberLayoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false)

        binding.memberAssignedRv.apply {
            layoutManager = assignedMemberLayoutManager
            adapter = teamMembersAdapter
        }
    }

    private fun openSeeAllAssignedMemberDialogFragment(){
        val dialog = SeeAllAssignedMembersDialogFragment()
        dialog.show(supportFragmentManager, "SeeAllAssignedMembersDialogFragment")
    }
    private fun openAssignMemberDialogFragment(){
        val dialog = AssignMemberDialogFragment()
        dialog.show(supportFragmentManager, "AssignMemberDialogFragment")
    }
}