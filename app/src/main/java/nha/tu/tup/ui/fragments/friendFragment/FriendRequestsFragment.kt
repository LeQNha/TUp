package nha.tu.tup.ui.fragments.friendFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentFriendRequestsBinding
import nha.tu.tup.adapters.FriendRequestAdapter
import nha.tu.tup.models.User
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.ui.acitvities.MainActivity
import nha.tu.tup.viewmodels.UserViewModel

class FriendRequestsFragment : Fragment() {

    private var _binding : FragmentFriendRequestsBinding? = null
    private val binding get() = _binding!!

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
//        return inflater.inflate(R.layout.fragment_friend_requests, container, false)
        _binding = FragmentFriendRequestsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friendRequestsRvSetUp()
    }

    private fun friendRequestsRvSetUp(){

        val friendRequestAdapter = FriendRequestAdapter()
        userViewModel._friendRequestSenders.observe(viewLifecycleOwner, Observer {
            friendRequestAdapter.differ.submitList(it)
            binding.friendRequestNumberTxtView.text = it.size.toString()
        })
        binding.friendRequestRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = friendRequestAdapter
        }

        val friendRequestSenders = mutableListOf<User>()

        userViewModel._friendRequestSenders.observe(viewLifecycleOwner, Observer {
            friendRequestAdapter.differ.submitList(it)
        })
    }
}