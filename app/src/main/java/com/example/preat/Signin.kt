package com.example.preat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.preat.Model.User
import com.example.preat.Utils.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_signin.*

class Signin : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val userTable: DatabaseReference = database.getReference("User")

        progressBar.visibility = View.INVISIBLE

        btn_signIn.setOnClickListener {

            setLayoutVisibility(View.VISIBLE, View.INVISIBLE)

            val valueEventListener = object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //Get User Information

                    setLayoutVisibility(View.INVISIBLE, View.VISIBLE)

                    if (dataSnapshot.child(et_phone_number.text.toString()).exists()) {


                        val user: User? = dataSnapshot.child(et_phone_number.text.toString()).getValue(User::class.java)
                        user?.phone = et_phone_number.text.toString()
                        if (user != null) {

                            if (user.password == et_password.text.toString()) {
                                "SignIn Successful.".toast(this@Signin)
                                val intent = Intent(this@Signin, homedrawer::class.java)
                                Common.currentUser = user
                                startActivity(intent)
                                finish()

                            } else {
                                "Wrong Password.".toast(this@Signin)
                            }
                        }
                    } else {
                        "User not exists.".toast(this@Signin)
                    }


                }

            }

            userTable.addValueEventListener(valueEventListener)
        }


    }
    fun setLayoutVisibility(progressBarVisibility: Int, otherVisibility: Int) {
        progressBar.visibility = progressBarVisibility
        et_password.visibility = otherVisibility
        et_phone_number.visibility = otherVisibility
        btn_signIn.visibility = otherVisibility
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    fun intentSignUp() {
        startActivity(Intent(this, register::class.java))
    }
}


