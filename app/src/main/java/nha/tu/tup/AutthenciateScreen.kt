package nha.tu.tup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nha.tu.tup.databinding.ActivityAtuhenciateBinding
import nha.tu.tup.ui.acitvities.Login
import nha.tu.tup.ui.acitvities.SignUp

class AutthenciateScreen : AppCompatActivity() {

    private lateinit var binding: ActivityAtuhenciateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtuhenciateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpButtons()
    }

    fun setUpButtons(){
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}