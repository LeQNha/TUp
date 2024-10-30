package nha.tu.tup.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nha.tu.tup.databinding.FragmentHomeBinding
import nha.tu.tup.models.User
import nha.tu.tup.ui.acitvities.MainActivity
import nha.tu.tup.ui.acitvities.task.TaskList
import nha.tu.tup.ui.acitvities.team.TeamList
import nha.tu.tup.viewmodels.UserViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel
    private var currentUser: User? =null
    private var test: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = (activity as MainActivity).userViewModel
        currentUser = (activity as MainActivity).currentUser
        println("___ CO NULL KO = ${currentUser?.username}")
        test = userViewModel.test

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

        userInfo()
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

    private fun userInfo(){
        binding.userNameTxt.text = currentUser?.username.toString()
        println("___set usernameeee")
//        binding.userNameTxt.text = userViewModel.test + " " + userViewModel.currentUser?.username
    }

}