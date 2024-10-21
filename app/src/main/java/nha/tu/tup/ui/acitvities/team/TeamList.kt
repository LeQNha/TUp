package nha.tu.tup.ui.acitvities.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import nha.tu.tup.databinding.ActivityTeamListBinding
import nha.tu.tup.models.Team
import nha.tu.tup.adapters.TeamAdapter
import nha.tu.tup.firebase.FirebaseInstance
import nha.tu.tup.repository.TeamRepository
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.viewmodels.TeamViewModel

class TeamList : BaseActivity() {

    private lateinit var binding: ActivityTeamListBinding
    lateinit var teamAdapter: TeamAdapter
    //    lateinit var teamViewModel: TeamViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val teamRepository = TeamRepository()
//        val teamViewModelProviderFactory = TeamViewModel.TeamViewModelFactory(teamRepository)
//        teamViewModel = ViewModelProvider(this, teamViewModelProviderFactory).get(TeamViewModel::class.java)

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