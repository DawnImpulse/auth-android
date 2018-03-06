package com.dawnimpulse.authlibrary

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dawnimpulse.auth.services.AuthGoogle
import com.dawnimpulse.library.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*id1.text = Test.first(this).toString()*/

        val authGoogle = AuthGoogle(this);
        authGoogle.signIn({ error, response ->

        });
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("Test", "Here");
    }
}
