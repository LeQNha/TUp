package nha.tu.tup.ui.acitvities.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.databinding.ActivityTeamListBinding
import nha.tu.tup.models.Team
import nha.tu.tup.adapters.TeamAdapter

class TeamList : AppCompatActivity() {

    private lateinit var binding: ActivityTeamListBinding
    lateinit var teamAdapter: TeamAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teamRvSetUp()
    }

    fun teamRvSetUp(){
        val projectList = listOf(
            Team("Out", "22/9/2024"),
            Team("Paracetamol", "12/7/2024"),
            Team("Huuudddd", "31/12/2024")
        )
        teamAdapter = TeamAdapter(projectList)
        binding.teamListRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = teamAdapter
        }
        teamAdapter.setOnItemClickListener {
            val intent = Intent(this, TeamScreen::class.java)
            startActivity(intent)
        }

    }
}