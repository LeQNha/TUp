package nha.tu.tup.ui.fragments.friendFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.adapters.FriendRequestAdapter
import nha.tu.tup.databinding.FragmentFriendRequestsBinding
import nha.tu.tup.models.User
import nha.tu.tup.repository.UserRepository
import nha.tu.tup.ui.acitvities.MainActivity
import nha.tu.tup.viewmodels.UserViewModel

class FriendRequestsFragment: Fragment() {

    private lateinit var binding: FragmentFriendRequestsBinding
    private lateinit var userViewModel: UserViewModel
    private var currentUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        userViewModel = (activity as MainActivity).userViewModel
        userViewModel = (activity as MainActivity).userViewModel
        currentUser = (activity as MainActivity).currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendRequestsBinding.inflate(layoutInflater, container, false)
//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friendRequestsRvSetUp()
    }

    private fun friendRequestsRvSetUp(){
        println("___ALO")
        userViewModel.getFriendRequests()
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
    fun userViewModelSetUp(){
        val userRepository = UserRepository()
        val userViewModelProviderFactory = UserViewModel.UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this , userViewModelProviderFactory).get(UserViewModel::class.java)
    }
}