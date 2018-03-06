package com.dawnimpulse.authlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dawnimpulse.auth.providers.AuthGoogle
import com.dawnimpulse.library.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by DawnImpulse on 2018 03 06
 * Last Branch Update - master
 * Updates :
 * DawnImpulse - 2018 03 06 - master - Initial
 */

/**
 * The following activity is an example for the auth-library
 */
class MainActivity : AppCompatActivity() {
    private lateinit var authGoogle: AuthGoogle

    /**
     * On Create
     * @param savedInstanceState
     *
     * We will create an object of AuthGoogle
     * & hook up a button for signin & signout
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authGoogle = AuthGoogle(this);
        sign_in_button.setOnClickListener {
            if (authGoogle.isUserSignedIn()) {
                authGoogle.signOut({ error, resp ->
                    Toast.makeText(this, "$error $resp", Toast.LENGTH_SHORT).show()
                })
            } else {
                authGoogle.signIn({ error, resp ->
                    Toast.makeText(this, "$error $resp", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    /**
     * onResume
     *
     * Used to check if user is logged in or not
     */
    override fun onResume() {
        super.onResume()

        if (authGoogle.isUserSignedIn())
            sign_in_button.text = "SIGN OUT"
        else
            sign_in_button.text = "SIGN IN"
    }
}
