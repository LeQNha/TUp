package nha.tu.tup.ui.acitvities.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.ActivityTeamScreenBinding
import nha.tu.tup.models.Task
import nha.tu.tup.ui.acitvities.task.CreateNewTask
import nha.tu.tup.ui.acitvities.task.LeaderTaskScreen
import nha.tu.tup.adapters.TeamMembersAdapter
import nha.tu.tup.adapters.TeamTasksAdapter
import nha.tu.tup.models.Team
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.ui.fragments.dialogFragments.AddNewMemberDialogFragment
import nha.tu.tup.ui.fragments.dialogFragments.SeeAllMembersDialogFragment
import nha.tu.tup.ui.fragments.dialogFragments.SeeAllTasksDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale

class TeamScreen : BaseActivity() {

    private lateinit var binding: ActivityTeamScreenBinding
    lateinit var teamMembersAdapter: TeamMembersAdapter

    lateinit var team: Team
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTeam()
        teamMembersRvSetUp()
        teamTasksRvSetUp()
        onClickListenerSetUp()
    }

    private fun onClickListenerSetUp() {
        binding.addNewTaskBtn.setOnClickListener {
            val intent = Intent(this, CreateNewTask::class.java).apply {
                putExtra("team", team)
            }
            startActivity(intent)
        }
        binding.seeAllMemberBtn.setOnClickListener {
            openSeeALLMembersDialogFragment()
        }
        binding.addNewMemberBtn.setOnClickListener {
            openAddNewMembersDialogFragment()
        }
        binding.seeAllTasksBtn.setOnClickListener {
            openSeeAllTasksDialogFragment()
        }
    }

    private fun getTeam() {
        val parcelTeam = intent.getParcelableExtra<Team>("team")
        parcelTeam?.let {
            team = it
        }

        setTeamDetail()
    }

    private fun getTasks() {

    }

    private fun setTeamDetail() {
        binding.teamName.text = team.teamName

        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        binding.teamCreatedDate.text = sdf.format(team.createdDate?.toDate())
    }

    private fun teamMembersRvSetUp() {
        val avatars = listOf(
            R.drawable.default_profile_picture,
            R.drawable.ic_analytics,
            R.drawable.ic_filter,
            R.drawable.ic_project,
            R.drawable.default_profile_picture,
            R.drawable.default_profile_picture
        )
        teamMembersAdapter = TeamMembersAdapter(avatars, this)
        val teamMembersLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.teamMembersRecyclerView.apply {
            layoutManager = teamMembersLayoutManager
            adapter = teamMembersAdapter
        }
    }

    private fun teamTasksRvSetUp() {
//        val tasks = listOf(
//            Task("Learn material", "22h", 3),
//            Task("Assemble the part", "3d", 2),
//            Task("Write the code", "1w", 4)
//        )


        val teamTasksAdapter = TeamTasksAdapter()
        taskViewModel.getTasks(teamId = team.teamId)
        taskViewModel._tasks.observe(this) { taskList ->
            teamTasksAdapter.differ.submitList(taskList) // Đảm bảo submitList được gọi khi taskList thay đổi
        }
        val teamTasksLayoutManager = LinearLayoutManager(this)
        binding.teamTasksRecyclerView.apply {
            layoutManager = teamTasksLayoutManager
            adapter = teamTasksAdapter
        }

        teamTasksAdapter.setOnItemClickListener {
            val intent = Intent(this, LeaderTaskScreen::class.java)
            startActivity(intent)
        }

//        teamTasksAdapter = TeamTasksAdapter(tasks)
//        val teamTasksLayoutManager = LinearLayoutManager(this)
//        binding.teamTasksRecyclerView.apply {
//            layoutManager = teamTasksLayoutManager
//            adapter = teamTasksAdapter
//        }
//        teamTasksAdapter.setOnItemClickListener {
//            val intent = Intent(this, LeaderTaskScreen::class.java)
//            startActivity(intent)
//        }
    }

    private fun openSeeALLMembersDialogFragment() {
        val dialog = SeeAllMembersDialogFragment()
//        val dialog = AddNewMemberDialogFragment()
        dialog.show(supportFragmentManager, "SeeAllMemberDialogFragment")
    }

    private fun openAddNewMembersDialogFragment() {
        val dialog = AddNewMemberDialogFragment()
        dialog.show(supportFragmentManager, "AddNewMemberDialogFragment")
    }

    private fun openSeeAllTasksDialogFragment() {
        val dialog = SeeAllTasksDialogFragment()
        dialog.show(supportFragmentManager, "SeeAllTaskDialogFragment")
    }
}