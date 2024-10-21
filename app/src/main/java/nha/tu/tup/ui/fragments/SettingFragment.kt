package nha.tu.tup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentSettingBinding
import nha.tu.tup.ui.acitvities.MainActivity
import nha.tu.tup.viewmodels.UserViewModel

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = (activity as MainActivity).userViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListennerSetUp()
    }

    private fun onClickListennerSetUp(){
        binding.signOutBtn.setOnClickListener {
            userViewModel.signOut(requireContext(), requireActivity() as MainActivity)
        }
    }
}