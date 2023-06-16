package com.bangkitcapstone.grifind.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bangkitcapstone.grifind.databinding.ActivitySignupBinding
import com.bangkitcapstone.grifind.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener{
            val fullname = binding.editTextRegisterFullname.text.toString()
            val username = binding.editTextRegisterUsername.text.toString()
            val email = binding.editTextRegisterEmail.text.toString()
            val password = binding.editTextRegisterPassword.text.toString()

            if (fullname.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "No empty allowed", Toast.LENGTH_SHORT).show()
            }

        }


    }
}