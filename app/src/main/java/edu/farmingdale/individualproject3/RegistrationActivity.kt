package edu.farmingdale.individualproject3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.farmingdale.individualproject3.database.User
import edu.farmingdale.individualproject3.databinding.ActivityRegistrationBinding
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val firstName = binding.etRegisterFirstName.text.toString()
            val lastName = binding.etRegisterLastName.text.toString()
            //Set binding for radiobutton for parent and kid
            val accountType = ""

            //Validate the input

            //Insert the user into the database
            val user = User(firstName = firstName, lastName = lastName, accountType = accountType)
            //Use NameRepository or database instance to add the user
            //Example: Name.Repository.addUser(user)

            //Navigate to login screen or dashboard
        }
    }
}