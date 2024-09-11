package nha.tu.tup.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nha.tu.tup.databinding.FragmentHomeBinding
import nha.tu.tup.ui.acitvities.task.TaskList
import nha.tu.tup.ui.acitvities.team.TeamList

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListenerSetUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickListenerSetUp(){
        binding.toTeamListBtn.setOnClickListener {
            val intent = Intent(requireContext(), TeamList::class.java)
            startActivity(intent)
        }
        binding.toTaskListBtn.setOnClickListener{
            val intent = Intent(requireContext(), TaskList::class.java)
            startActivity(intent)
        }
    }

}