package nha.tu.tup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import nha.tu.tup.databinding.ActivityAtuhenciateBinding
import nha.tu.tup.repository.UserRepository
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.viewmodels.UserViewModel

class AutthenciateScreen : BaseActivity() {

    private lateinit var binding: ActivityAtuhenciateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtuhenciateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}