package nha.tu.tup.ui.fragments.friendFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import nha.tu.tup.adapters.SearchFriendAdapter
import nha.tu.tup.databinding.FragmentFindFriendBinding
import nha.tu.tup.models.User
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.ui.acitvities.MainActivity
import nha.tu.tup.viewmodels.UserViewModel

class FindFriendFragment : Fragment() {

    private var _binding: FragmentFindFriendBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel
    val searchFriendAdapter = SearchFriendAdapter()

    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userViewModel = (activity as MainActivity).userViewModel
        currentUser = userViewModel.currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search_for_friend, container, false)
        _binding = FragmentFindFriendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFriendRvSetUp()
        findFriend()
    }

    private fun onClickListenerSetUp() {

    }

    private fun findFriend() {
        binding.searchFriendEditTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                userViewModel.findUsers(query.toString())
//                userViewModel._foundUsers.observe(viewLifecycleOwner, Observer {
//                    searchFriendAdapter.differ.submitList(it)
//                })
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun searchFriendRvSetUp() {
        val users = listOf<User>()

        val searchFriendLayoutManager = LinearLayoutManager(requireContext())
        searchFriendAdapter.differ.submitList(users)
        binding.searchFriendRv.apply {
            layoutManager = searchFriendLayoutManager
            adapter = searchFriendAdapter
        }
        onItemComponentsClickSetUp()
    }

    private fun onItemComponentsClickSetUp() {
        searchFriendAdapter.setOnAddFriendBtnClickListener {
            val requestReceiverId = it.userId

            userViewModel.sendFriendRequest(requestReceiverId, requireContext())
        }
    }
}