package com.bangkitcapstone.grifind.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bangkitcapstone.grifind.R
import com.bangkitcapstone.grifind.databinding.ActivitySignupBinding
import com.bangkitcapstone.grifind.ui.auth.login.LoginActivity
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
        binding.apply {
            toSigninText.setOnClickListener{
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            }

            signupButton.setOnClickListener {
                val fullname = binding.editTextRegisterFullname.text.toString()
                val username = binding.editTextRegisterUsername.text.toString()
                val email = binding.editTextRegisterEmail.text.toString()
                val password = binding.editTextRegisterPassword.text.toString()
                val confirmpassword = binding.editTextRegisterConfirmpassword.text.toString()

                if (fullname.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()){
                    if (password == confirmpassword){

                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this@SignupActivity, getString(R.string.success_register), Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@SignupActivity, it.exception.toString(), Toast.LENGTH_SHORT).show()

                            }
                        }
                    } else {
                        Toast.makeText(this@SignupActivity, "Password is not matching", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignupActivity, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

                }
            }


        }


    }
}