package nha.tu.tup.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import nha.tu.tup.ui.fragments.friendFragment.FriendListFragment
import nha.tu.tup.ui.fragments.friendFragment.FriendRequestsFragment
import nha.tu.tup.ui.fragments.friendFragment.SearchForFriendFragment

class FriendViewPagerAdapter(fragment: Fragment) :FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>(
        FriendListFragment(),
        SearchForFriendFragment(),
        FriendRequestsFragment()
    )
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}