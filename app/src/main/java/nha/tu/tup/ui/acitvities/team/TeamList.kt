package nha.tu.tup.ui.acitvities.team

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.databinding.ActivityTeamListBinding
import nha.tu.tup.adapters.TeamAdapter
import nha.tu.tup.ui.acitvities.MainActivity

open class TeamList : MainActivity() {

    private lateinit var binding: ActivityTeamListBinding
    lateinit var teamAdapter: TeamAdapter
    //    lateinit var teamViewModel: TeamViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teamRvSetUp()
        onClickListenerSetUp()
    }

    private fun onClickListenerSetUp() {
        binding.createNewTeamBtn.setOnClickListener {
            val intent = Intent(this, CreateNewTeam::class.java)
            startActivity(intent)
        }
    }

    fun teamRvSetUp() {

        teamAdapter = TeamAdapter()

        teamViewModel.getTeams()

        teamViewModel._teams.observe(this){ teamList -> //List<Team>!
            teamAdapter.differ.submitList(teamList)
        }

        binding.teamListRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = teamAdapter
        }

        teamAdapter.setOnItemClickListener {
            val intent = Intent(this, TeamScreen::class.java).apply {
                putExtra("team",it)
            }
            startActivity(intent)
        }

    }
}