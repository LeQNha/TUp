package nha.tu.tup.ui.acitvities.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.ActivityLeaderTaskScreenBinding
import nha.tu.tup.adapters.TeamMembersAdapter
import nha.tu.tup.ui.fragments.dialogFragments.AssignMemberDialogFragment
import nha.tu.tup.ui.fragments.dialogFragments.SeeAllAssignedMembersDialogFragment

class LeaderTaskScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLeaderTaskScreenBinding
    private lateinit var teamMembersAdapter: TeamMembersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderTaskScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

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