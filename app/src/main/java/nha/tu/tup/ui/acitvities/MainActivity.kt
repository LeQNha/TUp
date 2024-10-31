package nha.tu.tup.ui.acitvities

import android.os.Bundle
import androidx.fragment.app.Fragment
import nha.tu.tup.R
import nha.tu.tup.databinding.ActivityMainBinding
import nha.tu.tup.models.User
import nha.tu.tup.ui.fragments.FriendFragment
import nha.tu.tup.ui.fragments.HomeFragment
import nha.tu.tup.ui.fragments.ProfileFragment
import nha.tu.tup.ui.fragments.SettingFragment

open class MainActivity : BaseActivity() {

    //    private lateinit var bottomNavigationView: BottomNavigationView
//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var navController: NavController
    lateinit var mainBinding: ActivityMainBinding
    var currentUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

//        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
//        navController = findNavController(R.id.fragmentContainerView)
//        appBarConfiguration = AppBarConfiguration(setOf( R.id.homeFragment2, R.id.chatFragment2, R.id.historyFragment2, R.id.settingFragment2))
//        setupActionBarWithNavController(navController,appBarConfiguration)
//
//        bottomNavigationView.setupWithNavController(navController)
        getCurrentUser()
        bottomNavItemClickSetUp()
    }

    private fun getCurrentUser(){
       val parcelUser = intent.getParcelableExtra<User>("currentUser")
        parcelUser.let {
            currentUser = it
            println("___NHAN DC PARCEL = ${currentUser?.username}")

            //KHOI TAO để đảm bảo HomeFragment nhận được currentUser
            replaceFragment(HomeFragment())
        }
    }

    fun bottomNavItemClickSetUp() {
        mainBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.chat_nav -> {
                    replaceFragment(FriendFragment())
                    true
                }

                R.id.history_nav -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                R.id.setting_nav -> {
                    replaceFragment(SettingFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}